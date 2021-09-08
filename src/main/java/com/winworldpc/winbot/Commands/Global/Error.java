package com.winworldpc.winbot.Commands.Global;

import java.util.List;

public class Error {

    public Error(){

    }

    public String Missing_Argument(){

        return "Missing Arguments";
    }
    public String Missing_Argument(String args){

        return "Mising Argument of " + args;
    }
    public String Missing_Argument(List<String> args){
        StringBuilder strBuilder = new StringBuilder();
        for(String str : args){
            strBuilder.append(str).append(",");
        }

        return "Missing Arguments" + strBuilder.toString();
    }

}
