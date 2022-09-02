package com.knubisoft.command;

import java.io.File;
import java.util.List;

public class Cd extends Command {

    public Cd(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Incorrect args. Use `..` to navigate to parent directory or choose right child directory";
        }
        String directory = args.get(0);
        File currentDirectory = context.getCurrentDirectory();
        if (directory.equals("..")) {
            context.setCurrentDirectory(currentDirectory.getParentFile());
        } else {
            File childDirectory = new File(currentDirectory, directory);
            if (!childDirectory.exists()) {
                return "No such child directory " + childDirectory;
            } else {
                context.setCurrentDirectory(childDirectory);
            }
        }
        return "Directory has successfully changed to " + context.getCurrentDirectory().getAbsolutePath();
    }
}
