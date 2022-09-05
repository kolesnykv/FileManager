package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class AddToFile extends Command {

    public AddToFile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File fileToOpen = new File(currentDirectory, args.get(0));
        if (!fileToOpen.exists()) {
            return "No such file to write into";
        } else {
            System.out.println("Input info u wanna add to end of the file:");
            Scanner sc = new Scanner(System.in);
            String stringToAdd = sc.nextLine();
            String content = FileUtils.readFileToString(fileToOpen, StandardCharsets.UTF_8);
            FileUtils.writeStringToFile(fileToOpen, content + stringToAdd, StandardCharsets.UTF_8);
            return "Info was added successfully";
        }

    }
}
