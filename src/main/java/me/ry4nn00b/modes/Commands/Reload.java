package me.ry4nn00b.modes.Commands;

import me.ry4nn00b.modes.Main;
import me.ry4nn00b.modes.Managers.FilesManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Reload implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        //Variables_____________________________________________________________________________________________________
        Main plugin = Main.plugin;
        String prefix = FilesManager.messages.getString("Mensagens.Prefix").replace("&", "§");


        //Command_______________________________________________________________________________________________________
        if(cmd.getName().equalsIgnoreCase("mreload")){
            if(sender.hasPermission("rstore.reload")){

                YamlConfiguration.loadConfiguration(FilesManager.modesFile);
                YamlConfiguration.loadConfiguration(FilesManager.messagesFile);
                try {
                    FilesManager.modes.load(FilesManager.modesFile);
                    FilesManager.messages.load(FilesManager.messagesFile);
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
                plugin.reloadConfig();

                sender.sendMessage(prefix + "Config recarregada com sucesso!");

            }else sender.sendMessage(prefix + "Você não possui permissão!");
        }

        return false;
    }
}
