package me.ry4nn00b.modes.Events;

import me.ry4nn00b.modes.Main;
import me.ry4nn00b.modes.Managers.CooldownsManager;
import me.ry4nn00b.modes.Managers.FilesManager;
import me.ry4nn00b.modes.SQLite.Constructs;
import me.ry4nn00b.modes.Utilities.JinRyuuStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitScheduler;

import static me.ry4nn00b.modes.Commands.Mode.modeActive;

public class TimerMode implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        //Variables_____________________________________________________________________________________________________
        Player p = e.getPlayer();
        BukkitScheduler scheduler = Main.scheduler;

        //Event_________________________________________________________________________________________________________
        for(String mode : FilesManager.modes.getConfigurationSection("Modos").getKeys(false)) {

            //Mode_Information______________________________________________________________________________________
            String mode_name = FilesManager.modes.getString("Modos." + mode + ".Name");
            int mode_delayTime = FilesManager.modes.getInt("Modos." + mode + ".Delay_Time");
            int mode_STR = FilesManager.modes.getInt("Modos." + mode + ".Stats.STR");
            int mode_DEX = FilesManager.modes.getInt("Modos." + mode + ".Stats.DEX");
            int mode_CON = FilesManager.modes.getInt("Modos." + mode + ".Stats.CON");
            int mode_WIL = FilesManager.modes.getInt("Modos." + mode + ".Stats.WIL");
            int mode_SPI = FilesManager.modes.getInt("Modos." + mode + ".Stats.SPI");

            //Event_____________________________________________________________________________________________________
            if(Constructs.getPlayerModo(p).contains(mode + "_active")){

                scheduler.cancelTask(CooldownsManager.disableMode);

                Constructs.setPlayerModo(p, Constructs.getPlayerModo(p).replace(mode + "_active", mode + "_delay"));
                Constructs.setActiveModes(p, "0");
                modeActive.remove(p.getName() + "_" + mode_name);
                JinRyuuStats.removeStats(p, mode_STR, mode_DEX, mode_CON, mode_WIL, mode_SPI);
                for (String command : FilesManager.modes.getStringList("Modos." + mode + ".CommandsOff")) {
                    if(command.equalsIgnoreCase(" ")){

                    }else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                }
                CooldownsManager.delayMode(p, mode, mode_name, mode_delayTime);

            }

        }

    }

}
