package com.knubisoft.command;

import org.apache.commons.lang3.text.StrBuilder;

import java.io.File;
import java.util.List;

public class Ls extends Command{

    public Ls(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        StringBuilder result = new StringBuilder();
        for (File f: allFiles) {
            result.append(f.getName()).append("\n");
        }
        return result.toString();
    }
}
