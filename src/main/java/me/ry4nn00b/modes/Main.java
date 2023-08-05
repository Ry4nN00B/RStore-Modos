package me.ry4nn00b.modes;

import me.ry4nn00b.modes.Managers.CommandsManager;
import me.ry4nn00b.modes.Managers.EventsManager;
import me.ry4nn00b.modes.Managers.FilesManager;
import me.ry4nn00b.modes.SQLite.SQLiteConnect;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.IOException;

public final class Main extends JavaPlugin {

    public static Main plugin;
    public static BukkitScheduler scheduler;

    @Override
    public void onEnable() {

        if(!getServer().getPluginManager().isPluginEnabled("PowerNBT")){
            Bukkit.getConsoleSender().sendMessage("§b§l[RStore-Modos] §fNão foi encontrado a dependência do PowerNBT, desativando plugin.");
            getPluginLoader().disablePlugin(plugin);
        }

        scheduler = Bukkit.getScheduler();
        plugin = this;

        //Initialization________________________________________________________________________________________________
        Bukkit.getConsoleSender().sendMessage("§b§l======================");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§f§lNome: §bRStore-Modos");
        Bukkit.getConsoleSender().sendMessage("§f§lCriador: §bRy4nN00B");
        Bukkit.getConsoleSender().sendMessage("§f§lVersão: §b1.1");
        Bukkit.getConsoleSender().sendMessage("§f§lEstado: §aIniciando");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§b§l======================");

        SQLiteConnect.open();

        //Managers______________________________________________________________________________________________________
        CommandsManager.getCommands();
        EventsManager.getEvents(plugin);
        FilesManager.getFiles(plugin);

    }

    @Override
    public void onDisable() {

        //Closure_______________________________________________________________________________________________________
        Bukkit.getConsoleSender().sendMessage("§b§l======================");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§f§lNome: §bRStore-Modos");
        Bukkit.getConsoleSender().sendMessage("§f§lCriador: §bRy4nN00B");
        Bukkit.getConsoleSender().sendMessage("§f§lVersão: §b1.1");
        Bukkit.getConsoleSender().sendMessage("§f§lEstado: §cDesligando");
        Bukkit.getConsoleSender().sendMessage(" ");
        Bukkit.getConsoleSender().sendMessage("§b§l======================");

        SQLiteConnect.close();

    }
}
