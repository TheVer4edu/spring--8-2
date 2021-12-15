package com.example.spring82;

import org.springframework.stereotype.Component;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

@Component
public class TextFileCountRowsModule implements IModuleFile {

    @Override
    public boolean isSupported(String path) {
        return path != null && FilenameUtils.getExtension(path).equals("txt");
    }

    @Override
    public String getDescription() {
        return "Считает количество строк в файле";
    }

    @Override
    public void performAction(String path, PrintStream stream) {
        try {
            if(!isSupported(path)) throw new UnsupportedOperationException();

            Stream<String> stringStream = Files.lines(Paths.get(path));
            Scanner scanner = new Scanner(new File(path));

            int lines = 0;
            while(scanner.hasNextLine() && (scanner.nextLine() != null)) {
                lines++;
            }

            stream.println("File has " + lines + " lines");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
