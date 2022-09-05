package com.knubisoft.command;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class RmFile extends Command {

    public RmFile(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File fileToRemove = new File(currentDirectory, args.get(0));
        if (!fileToRemove.exists()) {
            return "No such file";
        } else {
            while (true) {
                System.out.println("R u sure u wanna delete " + fileToRemove.getName() + " file?" + "\nPress y to continue or n to abort:");
                Scanner sc = new Scanner(System.in);
                String conformation = sc.nextLine().trim();
                switch (conformation.toLowerCase()) {
                    case "y" -> {
                        fileToRemove.delete();
                        return "File " + fileToRemove.getName() + " was successfully removed";
                    }
                    case "n" -> {
                        return "Operations was successfully aborted";
                    }
                    case default -> {
                        System.out.println("Please press y to continue or n to abort");
                    }
                }
            }
        }
    }
}
