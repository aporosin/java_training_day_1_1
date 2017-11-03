package com.aptitude.training.day1;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesDemo {
    public static void main(String[] args) {
        Properties p = new Properties();
        p.put("a", "klucz a");
        p.put("aa", "klucz aa");
        p.put("aaa", "klucz aaa");

        // jvmpoland slack => register

        Path path = Paths.get("test.txt");
        try(OutputStream stream = Files.newOutputStream(path)) {
            p.store(stream, "Hello this is comment 123");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Properties readProperties = new Properties();
        try {
            readProperties.load(Files.newInputStream(path));
            // wypysuje wszystkie
            readProperties.list(System.out);
            System.out.println(readProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Files.write(Paths.get("properties.txt"), );
    }
}
