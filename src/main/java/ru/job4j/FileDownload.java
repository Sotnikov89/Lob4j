package ru.job4j;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

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

                long timePassed = (System.currentTimeMillis() - timeStart);  // how long we downloading file
                long bytesDownloaded = ++bytesRead;  // how many bytes we downloaded

                if (bytesDownloaded / timePassed > maxKBPerSecond) {
                    long requiredTime = bytesDownloaded / maxKBPerSecond; // how long we mast download "bytesDownloaded" for speed "maxKBPerSecond"
                    Thread.sleep(requiredTime - timePassed);
                }
            }
        }
    }
}

