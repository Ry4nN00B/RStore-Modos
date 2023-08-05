package me.ry4nn00b.modes.Managers;

import me.ry4nn00b.modes.Main;
import me.ry4nn00b.modes.SQLite.Constructs;
import me.ry4nn00b.modes.Utilities.JinRyuuStats;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import static me.ry4nn00b.modes.Commands.Mode.modeActive;

public class CooldownsManager {

    public static int disableMode;
    public static int delayMode;

    public static int ActiveMode(Player p, int mode_activeTime, int mode_delayTime, String mode, String mode_name, int mode_STR, int mode_DEX, int mode_CON, int mode_WIL, int mode_SPI){

        //Variables_____________________________________________________________________________________________________
        Main plugin = Main.plugin;
        BukkitScheduler scheduler = Main.scheduler;
        int removeActiveModes = Integer.parseInt(Constructs.getActiveModes(p)) - 1;

        //Messages______________________________________________________________________________________________________
        String prefix = FilesManager.messages.getString("Mensagens.Prefix").replace("&", "§");
        String disabledMode = FilesManager.messages.getString("Mensagens.DisabledMode").replace("&", "§");

        //Scheduler_____________________________________________________________________________________________________

        disableMode = scheduler.scheduleSyncRepeatingTask(plugin, new Runnable() {

            int timer = mode_activeTime;

            @Override
            public void run() {

                timer--;

                if(timer == 0){

                    if(modeActive.contains(p.getName() + "_" + mode_name)) {

                        p.sendMessage(prefix + disabledMode.replace("{modo}", mode_name));
                        Constructs.setPlayerModo(p, Constructs.getPlayerModo(p).replace(mode + "_active", mode + "_delay"));
                        Constructs.setActiveModes(p, String.valueOf(removeActiveModes));
                        JinRyuuStats.removeStats(p, mode_STR, mode_DEX, mode_CON, mode_WIL, mode_SPI);
                        modeActive.remove(p.getName() + "_" + mode_name);
                        for (String command : FilesManager.modes.getStringList("Modos." + mode + ".CommandsOff")) {
                            if(command.equalsIgnoreCase(" ")){

                            }else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                        }

                        delayMode(p, mode, mode_name, mode_delayTime);

                    }else return;

                }

            }
        }, 20L, 20L);

        return disableMode;

    }

    public static int delayMode(Player p, String mode, String mode_name, int mode_delayTime){

        //Variables_____________________________________________________________________________________________________
        Main plugin = Main.plugin;
        BukkitScheduler scheduler = Main.scheduler;

        //Messages______________________________________________________________________________________________________
        String prefix = FilesManager.messages.getString("Mensagens.Prefix").replace("&", "§");
        String finishDelay = FilesManager.messages.getString("Mensagens.FinishDelay").replace("&", "§");

        delayMode = scheduler.scheduleSyncDelayedTask(plugin, () -> {

            Constructs.setPlayerModo(p, Constructs.getPlayerModo(p).replace(mode + "_delay", ""));
            p.sendMessage(prefix + finishDelay.replace("{modo}", mode_name));

        }, mode_delayTime * 20L);

        return delayMode;

    }

}
