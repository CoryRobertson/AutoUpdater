package com.github.coryrobertson.AutoUpdater;

import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater
{
    public static void main(String[] args){

        //Variables to download the file and where to put it
        String fileName = "Datapacks.7z";
        String urlFile = "../lib/url.txt";
        String altUrlFile = "url.txt";
        String destination = "";
        String line = null;
        boolean argsSet = false;

        //This try catch block will decide if we use cmd args or a file of args
        try
        {
            line = args[0];
            fileName = args[1];
            destination = args[2]; // e.g. "./output/"
            System.out.println("Running with command line arguments...");
            argsSet = true;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Running with default params...");
        }

        try {
            if(!argsSet) 
            {

                //This try catch block will help us find the url.txt file, we can look in two places just in case as well.
                FileReader fr;
                try {
                    fr = new FileReader(urlFile);//reads the url file
                } catch (FileNotFoundException e) {
                    try {
                        fr = new FileReader(altUrlFile);
                    }catch (FileNotFoundException ee)
                    {
                        System.out.println("Error no url file found.");
                        fr = null;
                        System.exit(1);
                    }
                }
                //After finding the file we read it line by line to get the args
                BufferedReader br = new BufferedReader(fr);//reads it line by line
                line = br.readLine();//first line is url
                fileName = br.readLine();//second line is file name to output
                destination = br.readLine();

            }
            //This block  will go to the url and begin downloading the file in a directory specified by the args, and then save the file.
            System.out.println(line);
            URL url = new URL(line);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            File dir = new File(destination);//create the directory just in case it's not there
            dir.mkdir();
            File output = new File(destination + fileName);//prep file for output
            FileOutputStream fos = new FileOutputStream(output);
            fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);//save the file to the file output stream
            fos.close();

            //This section will take in a 7z file and then decompress it.
            SevenZFile sevenZFile = new SevenZFile(output);//begin opening the newly downloaded 7z file
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null){//loop through each entry in the 7z file
                if (entry.isDirectory()){//check if it is a file, if not skip it
                    continue;
                }
                File curfile = new File(new File(destination), entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {//if the currently selected file has a parent directory we create the directories to get there.
                    parent.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(curfile);
                byte[] content = new byte[(int) entry.getSize()];
                sevenZFile.read(content, 0, content.length);//read and put the content data of the file into an array of bytes
                out.write(content);//write the bytes into a file output stream
                out.close();
            }
            output.delete();//delete the archive after decompressing it.
        }
        catch (IOException e)
        {
            System.out.println("error trying to download file\n");
            e.printStackTrace();
        }
    }
}
