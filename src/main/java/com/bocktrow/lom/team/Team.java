package com.bocktrow.lom.team;

import org.bukkit.ChatColor;

public enum  Team {

    RED(ChatColor.RED), BLUE(ChatColor.BLUE);

    private Object chatColor;

    Team(Object chatColor) {
        this.chatColor = chatColor;
    }

    public Object getChatColor() {
        return chatColor;
    }
}
