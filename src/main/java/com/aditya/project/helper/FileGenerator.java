package com.aditya.project.helper;

import com.aditya.project.model.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileGenerator {

    private List<User> users = new ArrayList<>();

    public void generateFile() throws IOException {
        users.add(new User("name", "Aditya"));
        users.add(new User("new_name", "Aditya"));
        users.add(new User("biggest_name", "Aditya"));

        File file = new File("folder//filename.ext");
        FileWriter fileWriter = new FileWriter(file);
        for(User user: users) {
            fileWriter.write(user.getName());
            for(int i=user.getName().length(); i<=20; i++) {
                fileWriter.write(" ");
            }
            fileWriter.write("= " + user.getValue());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
    }
}
