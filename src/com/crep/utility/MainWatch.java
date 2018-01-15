package com.crep.utility;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

public class MainWatch {
	
	public static Properties config;

	public static void watchDirectoryPath(Path path) throws IOException 
	{
		//Reading the config file to check the location to search the Path
		
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\ObjectRepository\\Configuration.properties");
		config= new Properties();
		config.load(ip);
		
		
		// Sanity check - Check if path is a folder
		try {
			Boolean isFolder = (Boolean) Files.getAttribute(path,
					"basic:isDirectory", NOFOLLOW_LINKS);
			if (!isFolder) {
				throw new IllegalArgumentException("Path: " + path + " is not a folder");
			}
		} catch (IOException ioe) {
			// Folder does not exists
			ioe.printStackTrace();
		}
		
		System.out.println("Watching path: " + path);
		
		// We obtain the file system of the Path
		FileSystem fs = path.getFileSystem ();
		
		// We create the new WatchService using the new try() block
		try(WatchService service = fs.newWatchService()) {
			
			// We register the path to the service
			// We watch for creation events
			path.register(service, ENTRY_CREATE);
			
			// Start the infinite polling loop
			WatchKey key = null;
			while(true) {
				key = service.take();
				
				// Dequeueing events
				Kind<?> kind = null;
				for(WatchEvent<?> watchEvent : key.pollEvents()) {
					// Get the type of the event
					kind = watchEvent.kind();
					if (OVERFLOW == kind) {
						continue; //loop
					} else if (ENTRY_CREATE == kind) {
						// A new Path was created 
						@SuppressWarnings("unchecked")
						Path newPath = ((WatchEvent<Path>) watchEvent).context();
						// Output
					
						// check whether added file is folder or file
						Path temp=Paths.get(config.getProperty("LocationPath")+"\\"+newPath);
						if(!(Boolean) Files.getAttribute(temp,"basic:isDirectory", NOFOLLOW_LINKS))
						{
							System.out.println("new File Found "+config.getProperty("LocationPath")+"\\"+newPath);
						}
					}
				}
				
				if(!key.reset())
				{
					break; //loop
				}
			}
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		} catch(InterruptedException ie) {
			ie.printStackTrace(); 
		}
		
	}

	public static void main(String[] args) throws IOException,InterruptedException 
	{
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\ObjectRepository\\Configuration.properties");
		config= new Properties();
		config.load(ip);
		// Folder we are going to watch
		Path folder = Paths.get(config.getProperty("LocationPath"));
		
		watchDirectoryPath(folder);
	}
}