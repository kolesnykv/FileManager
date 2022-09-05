package com.knubisoft;

import com.knubisoft.command.Command;
import com.knubisoft.command.Context;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Context context = new Context(null, new File(Paths.get(Main.class.getPackageName()).toAbsolutePath().toUri()));
        Map<String, Command> availableCommands = getCommands(context);
        context.setCommandMap(availableCommands);
        performCommands(context, availableCommands);
    }

    private static void performCommands(Context context, Map<String, Command> availableCommands) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (StringUtils.isBlank(line)) {
                continue;
            }
            List<String> commandWithArgs = Arrays.asList(line.split(" "));
            String commandName = commandWithArgs.get(0);
            if (commandName.equals("q") || commandName.equals("quit")) {
                break;
            }
            Command command = availableCommands.getOrDefault(commandName, new Command(context) {
                @Override
                public String execute(List<String> args) {
                    return "Unknown command " + commandName;
                }
            });
            List<String> args = commandWithArgs.subList(1, commandWithArgs.size());
            System.out.println(command.execute(args));
        }
    }

    @SneakyThrows
    private static Map<String, Command> getCommands(Context context) {
        Reflections reflection = new Reflections("com.knubisoft.command", Scanners.SubTypes);
        Set<Class<? extends Command>> allExtendingClasses = reflection.getSubTypesOf(Command.class);
        Map<String, Command> commandNameToFunc = new LinkedHashMap<>();
        for (Class<? extends Command> cls : allExtendingClasses) {
            Command instance = cls.getDeclaredConstructor(Context.class).newInstance(context);
            commandNameToFunc.put(cls.getSimpleName().toLowerCase(), instance);
        }
        return commandNameToFunc;
    }
}
