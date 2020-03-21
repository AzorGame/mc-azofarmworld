package io.github.azorgame.azofarmworld.file;

import io.github.azorgame.azofarmworld.AzoFarmWorld;
import io.github.azorgame.azofarmworld.utils.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PluginFile {

    private static final char ALT_COLOR_CHAR = '&';

    private File file;
    FileConfiguration cfg;
    private AzoFarmWorld instance;

    PluginFile(AzoFarmWorld instance, String filename) {
        this.instance = instance;
        this.file = new File(instance.getDataFolder(),filename);
        this.cfg = YamlConfiguration.loadConfiguration(file);
        copyDefaults();
        saveFile();
    }

    private void copyDefaults(){
        cfg.options().copyDefaults(true);
        cfg.addDefaults(generateDefaultValues());
    }

    protected abstract HashMap<String, Object> generateDefaultValues();

    public String getString(String path){
        return cfg.getString(path,path);
    }

    public String getColorTranslatedString(String path){
        if (cfg.isSet(path))
            return ChatColor.translateAlternateColorCodes(ALT_COLOR_CHAR,cfg.getString(path));
        return path;
    }

    public List<String> getColorTranslatedStringList(String path){
        List<String> returns = new ArrayList<String>();
        if(cfg.isSet(path)){
            for (String string :
                    cfg.getStringList(path)) {
                returns.add(ChatColor
                        .translateAlternateColorCodes(ALT_COLOR_CHAR,string));
            }
        }
        if(returns.size() == 0)
            returns.add(path);
        return returns;
    }

    public int getInt(String path){
        return cfg.getInt(path,-1);
    }

    public double getDouble(String path){
        return cfg.getDouble(path,-1.1);
    }

    public Material getMaterial(String path){
        return Material.valueOf(cfg.getString(path, "AIR").toUpperCase());
    }

    public Map<Enchantment, Integer> getEnchantments(String path){
        Map<Enchantment,Integer> enchantments = new HashMap<Enchantment, Integer>();
        if(cfg.getConfigurationSection(path) == null)
            return enchantments;
        Map<String,Object> section = cfg.getConfigurationSection(path).getValues(false);
        for (String key :
                section.keySet()) {
            try {
                enchantments.put(Enchantment.getByKey(NamespacedKey.minecraft(key)),(Integer) section.get(key));
            } catch (Exception e) {
                instance.getLogger().warning("Enchantments could not be loaded. An invalid level value was asigned. " +
                        "Please use numbers there. File["+file.getAbsolutePath()+"] Path["+path+"]");
            }
        }
        return enchantments;
    }

    public ItemStack getItemStack(String path){ // perk.nofly.item.<here>
        return new ItemBuilder(getMaterial(path+".material"))
                .setDisplayName(getColorTranslatedString(path+".displayname"))
                .addEnchantments(getEnchantments(path+".enchantments"))
                .setLore(getColorTranslatedStringList(path+".lore"))
                .build();
    }

    public boolean isSet(String path){
        return cfg.isSet(path);
    }

    /**
     * Saves this {@link PluginFile} to the disk at the plugin datafolder location.
     */
    public void saveFile(){
        try {
            this.cfg.save(file);
        } catch (IOException e) {
            instance.getLogger().warning("The file "+file.getAbsolutePath()+" could not be saved.");
            e.printStackTrace();
        }
    }
}
