package com.knubisoft.command;

import java.util.List;
import java.util.Map;

public class Help extends Command{

    public Help(Context context) {
        super(context);
    }

    @Override
    public String execute(List<String> args) {
        Map<String, Command> commands = context.getCommandMap();
        return "Avaliable commands:\n" + String.join("\n", commands.keySet());
    }
}
