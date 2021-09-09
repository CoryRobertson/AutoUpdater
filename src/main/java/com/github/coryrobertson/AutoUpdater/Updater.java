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
        String urlFile = "url.txt";
        String outputName;
        String line;

        try {
            FileReader fr = new FileReader(urlFile);//reads the url file
            BufferedReader br = new BufferedReader(fr);//reads it line by line
            line = br.readLine();//first line is url
            outputName = br.readLine();//second line is file name to output
            fileName = outputName;
            System.out.println(line);
            URL url = new URL(line);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            File dir = new File("./output/");//create the dirrectory just incase its not there
            dir.mkdir();
            File output = new File("./output/" + fileName);//prep file for output
            FileOutputStream fos = new FileOutputStream(output);
            fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);//save the file to the file output stream
            fos.close();

            SevenZFile sevenZFile = new SevenZFile(new File("./output/" + fileName));//begin opeing the newly downloaded 7z file
            SevenZArchiveEntry entry;
            while ((entry = sevenZFile.getNextEntry()) != null){
                if (entry.isDirectory()){
                    continue;
                }
                File curfile = new File(new File("./output/Datapacks"), entry.getName());
                File parent = curfile.getParentFile();
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                FileOutputStream out = new FileOutputStream(curfile);
                byte[] content = new byte[(int) entry.getSize()];
                sevenZFile.read(content, 0, content.length);
                out.write(content);
                out.close();
            }
            output.delete();


        } catch (IOException e) {
            System.out.println("error trying to download file\n");
            e.printStackTrace();
        }



    }
}
