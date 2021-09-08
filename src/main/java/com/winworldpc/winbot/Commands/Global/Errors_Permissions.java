package com.winworldpc.winbot.Commands.Global;

public class Errors_Permissions {

    public Errors_Permissions(){

    }
    public String No_Permissions(String perms){
        return "You do not have permission to do this. You must have `" + perms + "` permissions.";
    }

    public String Self_No_Permissions(String perms){
        return "I do not have " + perms + "Please make sure I have this permissions.";
    }

}
