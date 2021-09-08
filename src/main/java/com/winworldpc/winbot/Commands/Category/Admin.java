package com.winworldpc.winbot.Commands.Category;

import com.winworldpc.winbot.Commands.commands.Admin.SetPrefixCommand;
import com.winworldpc.winbot.Interfaces.ICategory;


public class Admin implements ICategory {
    public Admin(){
        commands.addCommand(new SetPrefixCommand());
    }
}
