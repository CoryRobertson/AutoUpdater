package com.github.coryrobertson.AutoUpdater;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

class UpdaterTest {

    @Test
    @DisplayName("Preset args test")
    /*
      Pre-set argument test.
     */
    void mainPreSetTest()
    {
        //follows format: url, filename to save as, destination to output to.
        String[] args = {"https://github.com/CoryRobertson/Datapacks/releases/latest/download/Datapacks.7z", "d.7z", "./output/"};
        Updater.main(args);

        //remove file after test and delete it if possible
        File file = new File("./output/");
        boolean deleted;
        try {
            FileUtils.deleteDirectory(file);
            deleted = true;
            System.out.println("File successfully deleted.");

        } catch (IOException e) {
            e.printStackTrace();
            deleted = false;
            System.out.println("File failed to be deleted.");

        }
        Assertions.assertTrue(deleted);
    }

    @Test
    @DisplayName("File acquired args test")
    /*
      Test using a file that is present with needed info to run program.
     */
    void mainTextFileTest()
    {
        //follows format: url, filename to save as, destination to output to.
        String[] args = new String[0];
        Updater.main(args);

        //remove file after test and delete it if possible
        File file = new File("./output/");
        boolean deleted;
        try {
            FileUtils.deleteDirectory(file);
            deleted = true;
            System.out.println("File successfully deleted.");
        } catch (IOException e) {
            e.printStackTrace();
            deleted = false;
            System.out.println("File failed to be deleted.");
        }
        Assertions.assertTrue(deleted);
    }
}