package com.knubisoft.command;

import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Open extends Command {

    public Open(Context context) {
        super(context);
    }

    @SneakyThrows
    @Override
    public String execute(List<String> args) {
        File currentDirectory = context.getCurrentDirectory();
        if (args.isEmpty()) {
            return "Please add file as arg";
        }
        File fileToOpen = new File(currentDirectory, args.get(0));
        if (!fileToOpen.exists()) {
            return "No such file to open";
        }
        if(!fileToOpen.isFile()) {
            return "To move through directories use \"cd\" command";
        }
        return "Content of the file " +
                fileToOpen.getName() + ":\n" +
                FileUtils.readFileToString(fileToOpen, StandardCharsets.UTF_8);
    }
}
