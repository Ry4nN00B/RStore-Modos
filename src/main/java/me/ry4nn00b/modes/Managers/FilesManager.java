package me.ry4nn00b.modes.Managers;

import me.ry4nn00b.modes.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class FilesManager {

    public static File messagesFile;
    public static File modesFile;
    public static FileConfiguration messages;
    public static FileConfiguration modes;

    public static void getFiles(Main plugin){

        messagesFile = new File(plugin.getDataFolder(), "Config.yml");
        modesFile = new File(plugin.getDataFolder(), "Modos.yml");

        plugin.saveResource("Config.yml", false);
        plugin.saveResource("Modos.yml", false);

        modes = YamlConfiguration.loadConfiguration(modesFile);
        messages = YamlConfiguration.loadConfiguration(messagesFile);

    }

}
