package com.knubisoft.command;


import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Stream;

public class Ls extends Command {


    public Ls(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File file = context.getCurrentDirectory();
        File[] allFiles = file.listFiles();
        if (allFiles.length == 0) {
            return "Dir " + file + " is empty";
        }
        return buildTable(allFiles, args);
    }

    private String buildTable(File[] files, List<String> args) {
        StringBuilder sb = new StringBuilder();
        String format = "| %-9s ";
        String sizeFormat = "| %-" + findLongestElement(files, "size") + "s ";
        String nameFormat = "| %-" + findLongestElement(files, "name") + "s ";
        sb.append(buildHeader(args, format, sizeFormat, nameFormat));
        sb.append(buildFooter(files, args, format, sizeFormat, nameFormat));
        return sb.toString();
    }

    public String buildHeader(List<String> args, String format, String sizeFormat, String nameFormat) {
        StringBuilder sb = new StringBuilder();
        if (args.isEmpty()) {
            sb.append(String.format(nameFormat, "File Name"))
                    .append(String.format(sizeFormat, "Size"))
                    .append(String.format(format, "Readable"))
                    .append(String.format(format, "Writable"))
                    .append(String.format(format, "Extension"))
                    .append("|\n");
        } else {
            char[] flags = args.get(0).replace("-", "").toCharArray();
            sb.append(String.format(nameFormat, "File Name"));
            for (char f : flags) {
                switch (f) {
                    case 's' -> sb.append(String.format(sizeFormat, "Size"));
                    case 'r' -> sb.append(String.format(format, "Readable"));
                    case 'w' -> sb.append(String.format(format, "Writable"));
                    case 'e' -> sb.append(String.format(format, "Extension"));
                }
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    private int findLongestElement(File[] files, String mapping) {
        OptionalInt max = OptionalInt.of(20);
        if (mapping.equalsIgnoreCase("name")) {
            max = Stream.of(files).mapToInt(f -> f.getName().length()).max();
        }
        if (mapping.equalsIgnoreCase("size")) {
            max = Stream.of(files).mapToInt(f -> String.valueOf(f.getUsableSpace()).length()).max();
        }
        return max.getAsInt();
    }

    public String buildFooter(File[] files, List<String> args, String format, String sizeFormat, String nameFormat) {
        StringBuilder sb = new StringBuilder();
        if (args.isEmpty()) {
            for (File f : files) {
                sb.append(String.format(nameFormat, f.getName()))
                        .append(String.format(sizeFormat, f.getTotalSpace()))
                        .append(String.format(format, f.canRead()))
                        .append(String.format(format, f.canWrite()))
                        .append(String.format(format, FilenameUtils.getExtension(f.getName())))
                        .append("|\n");
            }

        } else {
            char[] flags = args.get(0).replace("-", "").toCharArray();
            for (File f : files) {
                sb.append(String.format(nameFormat, f.getName()));
                for (char fl : flags) {
                    switch (fl) {
                        case 's' -> sb.append(String.format(sizeFormat, f.getTotalSpace()));
                        case 'r' -> sb.append(String.format(format, f.canRead()));
                        case 'w' -> sb.append(String.format(format, f.canWrite()));
                        case 'e' -> sb.append(String.format(format, FilenameUtils.getExtension(f.getName())));
                    }
                }
                sb.append("|\n");
            }
        }
        return sb.toString();

    }
}
