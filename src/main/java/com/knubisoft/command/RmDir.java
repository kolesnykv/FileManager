package com.knubisoft.command;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class RmDir extends Command {

    public RmDir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        File dirToRemove = new File(currentDirectory, args.get(0));
        if (!dirToRemove.exists()) {
            return "No such directory";
        } else {
            while (true) {
                System.out.println("R u sure u wanna delete " + dirToRemove.getName() + " directory?" + "\nPress y to continue or n to abort:");
                Scanner sc = new Scanner(System.in);
                String conformation = sc.nextLine().trim();
                switch (conformation.toLowerCase()) {
                    case "y" -> {
                        dirToRemove.delete();
                        return "Directory " + dirToRemove.getName() + " was successfully removed";
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
