package com.winworldpc.winbot.Interfaces;

import com.winworldpc.winbot.Commands.CommandContext;

import java.util.Arrays;
import java.util.List;

public interface ICommand{
    void handle(CommandContext ctx);

    String getName();

    String getCategory();

    String getHelp();


    default List<String> getAliases(){
        return Arrays.asList();
    }
}
