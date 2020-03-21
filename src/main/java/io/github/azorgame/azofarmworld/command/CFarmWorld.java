package io.github.azorgame.azofarmworld.command;

import io.github.azorgame.azofarmworld.AzoFarmWorld;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CFarmWorld extends PluginCommand {


    public CFarmWorld(AzoFarmWorld instance) {
        super(instance);
    }

    public boolean onCommand(CommandSender sender, Command command,
                             String label, String[] args) {
        if (!(sender instanceof Player)) {
            messageHandler.sendNoPlayer(sender);
        }
        if(!(sender.hasPermission("azofarmworld.command.farmworld"))){
            messageHandler.sendNoPermission(sender,command);
        }
        //TODO make actuall command
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command,
                                      String alias, String[] args) {
        return null;
    }
}
