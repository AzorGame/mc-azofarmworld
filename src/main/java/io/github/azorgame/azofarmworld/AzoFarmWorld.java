package io.github.azorgame.azofarmworld;

import io.github.azorgame.azofarmworld.command.CFarmWorld;
import io.github.azorgame.azofarmworld.file.ConfigFile;
import io.github.azorgame.azofarmworld.utils.MessageHandler;
import io.github.azorgame.azofarmworld.world.FarmWorldManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AzoFarmWorld extends JavaPlugin {

    private ConfigFile configFile;
    private MessageHandler messageHandler;
    private FarmWorldManager farmWorldManager;

    @Override
    public void onEnable() {
        super.onEnable();
        configFile = new ConfigFile(this);
        messageHandler = new MessageHandler(configFile);
        farmWorldManager = new FarmWorldManager();

        registerCommands();
        registerListener();
    }

    private void registerCommands(){
        CFarmWorld cFarmWorld = new CFarmWorld(this);
        getCommand("farmworld").setExecutor(cFarmWorld);
        getCommand("farmworld").setTabCompleter(cFarmWorld);
    }

    private void registerListener(){

    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

    public FarmWorldManager getFarmWorldManager() {
        return farmWorldManager;
    }
}
