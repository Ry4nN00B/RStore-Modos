package me.ry4nn00b.modes.Managers;

import me.ry4nn00b.modes.Events.TimerMode;
import me.ry4nn00b.modes.Events.SQLiteRegister;
import me.ry4nn00b.modes.Main;
import org.bukkit.Bukkit;

public class EventsManager {

    public static void getEvents(Main plugin){

        Bukkit.getPluginManager().registerEvents(new SQLiteRegister(), plugin);
        Bukkit.getPluginManager().registerEvents(new TimerMode(), plugin);

    }

}
