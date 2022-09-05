package com.knubisoft.command;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

public class Ls extends Command {

    public Ls(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        String leftAlignFormat = "| %-15s | %-13d | %-9s |%n";
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        StringBuilder result = new StringBuilder();
        if (allFiles != null && allFiles.length > 0) {
            result.append("+-----------------+---------------+-----------+").append("\n")
                    .append("|    File name    |     Size      | Extension |").append("\n")
                    .append("+-----------------+---------------+-----------+").append("\n");
            for (File f : allFiles) {
                Object extension = FilenameUtils.getExtension(f.getName());
                String fileName = f.getName();
                result.append(String.format(leftAlignFormat, fileName, f.getTotalSpace(), extension));
            }
            result.append("+-----------------+---------------+-----------+");
            return result.toString();
        }
        {
            return "No files in current directory";
        }
    }
}
