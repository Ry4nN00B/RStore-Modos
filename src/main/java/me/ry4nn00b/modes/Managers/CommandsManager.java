package me.ry4nn00b.modes.Managers;

import me.ry4nn00b.modes.Commands.DMode;
import me.ry4nn00b.modes.Commands.Mode;
import me.ry4nn00b.modes.Commands.Reload;
import org.bukkit.Bukkit;

public class CommandsManager {

    public static void getCommands(){

        Bukkit.getPluginCommand("modo").setExecutor(new Mode());
        Bukkit.getPluginCommand("dmodo").setExecutor(new DMode());
        Bukkit.getPluginCommand("mreload").setExecutor(new Reload());

    }

}
