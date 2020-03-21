package io.github.azorgame.azofarmworld.command;

import io.github.azorgame.azofarmworld.AzoFarmWorld;
import io.github.azorgame.azofarmworld.utils.MessageHandler;
import io.github.azorgame.azofarmworld.world.FarmWorldManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;

public abstract class PluginCommand implements CommandExecutor, TabCompleter {

    protected MessageHandler messageHandler;
    protected FarmWorldManager farmWorldManager;

    public PluginCommand(AzoFarmWorld instance){
        messageHandler = instance.getMessageHandler();
        farmWorldManager = instance.getFarmWorldManager();
    }

}
