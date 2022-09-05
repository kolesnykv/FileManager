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
        if (args.isEmpty()) {
            return "Please add file name as arg";
        }
        File path = context.getCurrentDirectory();
        File fileToCreate = new File(path.getAbsolutePath(), args.get(0));
        boolean result = fileToCreate.createNewFile();
        if (result) {
            return "file " + fileToCreate.getName() + " successfully created";
        } else {
            return "Whoopsy File" + fileToCreate.getName() + " already exist at location: " + fileToCreate.getAbsolutePath();
        }
    }
}
