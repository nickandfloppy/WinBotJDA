package com.winworldpc.winbot;

import me.duncte123.botcommons.web.WebUtils;
import net.dv8tion.jda.api.JDABuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.security.auth.login.LoginException;

public class Bot {


    private static final Logger LOGGER = LogManager.getLogger(Listener.class);
    private Bot() throws LoginException {
       LOGGER.info(new JDABuilder().setToken(Config.TOKEN).addEventListeners(new Listener()).build());
        WebUtils.setUserAgent("Mozilla/5.0 WinBot/floppydisk#0590");
    }



    public static void main(String[] args)throws LoginException{
        new Bot();
    }
}
