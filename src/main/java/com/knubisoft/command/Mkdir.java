package com.knubisoft.command;

import java.io.File;
import java.util.List;

public class Mkdir extends Command {

    public Mkdir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        if (args.isEmpty()) {
            return "Please add dir name as arg";
        }
        File path = context.getCurrentDirectory();
        if (args.get(0).matches("\\w*[\\/:*?\"<>|]+\\w*")) {
            return "Dir name includes invalid symbols";
        }
        File theDir = new File(path.getAbsolutePath(), args.get(0));
        if (theDir.mkdir()) {
            return theDir.getName() + " was successfully created in " + path.getAbsolutePath();
        } else {
            return theDir.getName() + " already exist in " + path.getAbsolutePath() + " directory";
        }
    }
}
