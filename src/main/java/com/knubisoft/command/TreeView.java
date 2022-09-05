package com.knubisoft.command;



import java.io.File;
import java.util.List;

public class TreeView extends Command  {

    public TreeView(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        File directory = context.getCurrentDirectory();
        int indent = 0;
        StringBuilder sb = new StringBuilder();
        printDirectoryTree(args, directory, indent, sb, 0);
        return sb.toString();
    }

    private static void printDirectoryTree(List<String> args, File directory, int indent,
                                           StringBuilder sb, int depth) {
        int askedDepth = Integer.parseInt(args.get(0));
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(directory.getName());
        sb.append("/");
        sb.append("\n");
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                if(++depth<askedDepth) {
                    printDirectoryTree(args, file, indent + 1, sb, depth);
                }
                else {
                    printFile(file, indent + 1, sb);
                }
            } else {
                printFile(file, indent + 1, sb);
            }
        }

    }

    private static void printFile(File file, int indent, StringBuilder sb) {
        sb.append(getIndentString(indent));
        sb.append("+--");
        sb.append(file.getName());
        sb.append("\n");
    }

    private static String getIndentString(int indent) {
        return "|  ".repeat(Math.max(0, indent));
    }
}