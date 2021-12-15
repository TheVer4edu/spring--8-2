package com.example.spring82;

import java.io.PrintStream;

public interface IModuleFile {
    boolean isSupported(String path);
    String getDescription();
    void performAction(String path, PrintStream stream);
}
