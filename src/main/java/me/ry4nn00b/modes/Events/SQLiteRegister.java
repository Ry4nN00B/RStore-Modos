package me.ry4nn00b.modes.Events;

import me.ry4nn00b.modes.SQLite.Constructs;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SQLiteRegister implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player p = e.getPlayer();

        if(!Constructs.hasPlayerTable(p)){

            Constructs.addPlayerTable(p);

        }

    }

}
