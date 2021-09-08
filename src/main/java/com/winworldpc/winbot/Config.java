package com.winworldpc.winbot;

import io.github.cdimascio.dotenv.Dotenv;


public class Config {
    private static final Dotenv dotenv = Dotenv.load();
    public static final int EMBED_COLOR = 0xFFD700;
    public static final String TOKEN = "TOKEN";
    public static final String PREFIX = ".";
    public static final String OWNER_ID = "437970062922612737";
}
