package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class RmDir extends Command {

    public RmDir(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Please add dir name as arg";
        }
        File currentDirectory = context.getCurrentDirectory();
        File dirToRemove = new File(currentDirectory, args.get(0));
        if (!dirToRemove.exists()) {
            return "No such directory";
        }
        if (!dirToRemove.isDirectory()) {
            return dirToRemove + " is not a dir";
        }
        while (true) {
            if(dirToRemove.listFiles().length!=0) {
                System.out.println("R u sure u wanna delete " + dirToRemove.getName() + " directory and all files inside?"
                        + "\nPress y to continue or n to abort:");
            } else {System.out.println("R u sure u wanna delete " + dirToRemove.getName() + " directory?" + "\nPress y to continue or n to abort:");}
            Scanner sc = new Scanner(System.in);
            String conformation = sc.nextLine().trim();
            switch (conformation.toLowerCase()) {
                case "y" -> {
                    FileUtils.deleteDirectory(dirToRemove);
                    return "Directory " + dirToRemove.getName() + " was successfully removed";
                }
                case "n" -> {
                    return "Operations was successfully aborted";
                }
                case default -> {}
            }
        }
    }
}

