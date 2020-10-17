package com.aditya.project.initializer;

import com.aditya.project.helper.FileGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationInitializer implements CommandLineRunner {

    @Autowired
    private FileGenerator fileGenerator;

    @Override
    public void run(String... args) throws Exception {
        fileGenerator.generateFile();
    }
}
