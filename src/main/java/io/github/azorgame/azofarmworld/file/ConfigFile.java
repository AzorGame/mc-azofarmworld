package io.github.azorgame.azofarmworld.file;

import io.github.azorgame.azofarmworld.AzoFarmWorld;

import java.util.HashMap;

public class ConfigFile extends PluginFile {

    public ConfigFile(AzoFarmWorld instance) {
        super(instance, "config.yml");
    }

    protected HashMap<String, Object> generateDefaultValues() {
        HashMap<String, Object> defaults = new HashMap<String, Object>();
        defaults.put("prefix","&7[&6AzoFarmWorld&7]&r ");
        defaults.put("command.message.noPlayer","&cYou need to be a player to perform this action.");
        defaults.put("command.message.noPermission","&7You have &cno permission&7 to perform the command &c/%command%&7.");
        defaults.put("command.message.wrongUsage","&7You used the command &c/%command% &7the wrong way. Just try &c/%usage%&7.");


        return defaults;
    }
}
