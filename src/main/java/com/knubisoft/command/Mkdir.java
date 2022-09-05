package com.knubisoft.command;

import java.io.File;
import java.util.List;

public class Mkdir extends Command {

    public Mkdir(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File path = context.getCurrentDirectory();
        File theDir = new File(path.getAbsolutePath() + "//" + args.get(0));
        if (!theDir.exists()) {
            theDir.mkdir();
            return theDir.getName() + " was successfully created in " + path.getAbsolutePath();
        } else {
            return theDir.getName() + " already exist in " + path.getAbsolutePath() + " directory";
        }
    }
}
