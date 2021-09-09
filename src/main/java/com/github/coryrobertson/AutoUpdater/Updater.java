package com.github.coryrobertson.AutoUpdater;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Updater
{
    public static void main(String[] args){
        String fileName = "Datapacks.7z";
        String urlFile = "url.txt";

        try {
            FileReader fr = new FileReader(urlFile);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String outputName = br.readLine();
            fileName = outputName;
            System.out.println(line);
            URL url = new URL(line);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            File dir = new File("./output/");
            dir.mkdir();
            File output = new File("./output/" + fileName);
            FileOutputStream fos = new FileOutputStream(output);
            fos.getChannel().transferFrom(rbc,0,Long.MAX_VALUE);
        } catch (IOException e) {
            System.out.println("error trying to download file\n");
            e.printStackTrace();
        }


    }
}
