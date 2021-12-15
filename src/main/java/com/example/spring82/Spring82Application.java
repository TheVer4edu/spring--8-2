package com.example.spring82;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.InputStream;
import java.io.NotActiveException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Spring82Application {

    private static Spring82Application application = new Spring82Application();
    private static ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
        //SpringApplication.run(Spring82Application.class, args);
        args = new String[] { new Scanner(System.in).nextLine() };
        if(args == null || args.length == 0) {
            System.out.println("No arguments");
            return;
        }
        PrintStream stream = System.out;
        InputStream reader = System.in;
        String path = args[0];
        List<IModuleFile> modules = application
                .getContext()
                .getBean("engine", ModuleEngine.class)
                .getSupportedModules(path);
        moduleActionsPerform(stream, reader, modules, path);
    }

    public ApplicationContext getContext() {
        return context;
    }

    public static void moduleActionsPerform(PrintStream output, InputStream input, List<IModuleFile> modules, String path) {
        if(modules.size() == 0) {
            output.println("No modules found");
            return;
        }
        output.println("Found: " + modules.size() + " modules for you <3");
        for (IModuleFile module : modules) {
            output.println(module.getClass().getSimpleName());
        }
        int i = -1;
        while (i < 0) {
            output.println("Please, choose the module");
            try {
                int opt = new Scanner(input).nextInt();
                i = opt > 0 && opt <= modules.size() ? opt - 1 : i;
            } catch (Exception e) {
                e.printStackTrace();
            }
            IModuleFile module = modules.get(i);
            output.println(module.getDescription());
            module.performAction(path, output);
        }
    }
}
