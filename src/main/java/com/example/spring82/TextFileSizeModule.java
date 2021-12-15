package com.example.spring82;

import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class TextFileSizeModule implements IModuleFile {


    @Override
    public boolean isSupported(String path) {
        return path != null && FilenameUtils.getExtension(path).equals("txt");
    }

    @Override
    public String getDescription() {
        return "Считает размер файла";
    }

    @Override
    public void performAction(String path, PrintStream stream) {
        try {
            if(!isSupported(path)) throw new UnsupportedOperationException();
            stream.println("File size " + Files.size(Paths.get(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
