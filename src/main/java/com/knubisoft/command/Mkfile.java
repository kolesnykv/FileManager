package com.knubisoft.command;

import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class Mkfile extends Command {

    public Mkfile(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File path = context.getCurrentDirectory();
        File fileToCreate = new File(path.getAbsolutePath() + "//" + args.get(0));
        boolean result = fileToCreate.createNewFile();
        if (result) {
            return "file successfully created " + fileToCreate.getAbsolutePath();
        } else {
            return "Whoopsy File already exist at location: " + fileToCreate.getAbsolutePath();
        }
    }
}
