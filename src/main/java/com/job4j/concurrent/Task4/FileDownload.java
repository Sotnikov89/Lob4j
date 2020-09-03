package com.job4j.concurrent.Task4;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class FileDownload {


    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        int maxKBPerSecond = 200;

        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {

            byte[] dataBuffer = new byte[1024];
            int bytesRead;

            long timeStart = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);

                long timePassed = (System.currentTimeMillis()-timeStart)/1000;
                int bytesDownloaded = ++bytesRead;

                if (bytesDownloaded/1000>maxKBPerSecond && timePassed<1){

                 //   Thread.sleep();
                }
                //Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

