package me.ry4nn00b.modes.Commands;

import me.ry4nn00b.modes.Managers.CooldownsManager;
import me.ry4nn00b.modes.Managers.FilesManager;
import me.ry4nn00b.modes.SQLite.Constructs;
import me.ry4nn00b.modes.Utilities.JinRyuuStats;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Mode implements CommandExecutor {

    public static List<String> modeActive = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        //Variables_____________________________________________________________________________________________________
        Player p = (Player) sender;
        String modeLimit = FilesManager.messages.getString("Modos.ModeLimit");
        int addActiveModes = Integer.parseInt(Constructs.getActiveModes(p)) + 1;

        //Messages______________________________________________________________________________________________________
        String prefix = FilesManager.messages.getString("Mensagens.Prefix").replace("&", "§");
        String withoutMode = FilesManager.messages.getString("Mensagens.WithoutMode").replace("&", "§");
        String modeNotFound = FilesManager.messages.getString("Mensagens.ModeNotFound").replace("&", "§");
        String delayedMode = FilesManager.messages.getString("Mensagens.DelayedMode").replace("&", "§");
        String activatedMode = FilesManager.messages.getString("Mensagens.ActivatedMode").replace("&", "§");
        String maxModes = FilesManager.messages.getString("Mensagens.MaxModes").replace("&", "§");
        String activeMode = FilesManager.messages.getString("Mensagens.ActiveMode").replace("&", "§");

        //Command_______________________________________________________________________________________________________
        if(cmd.getName().equalsIgnoreCase("modo")){
            if(args.length == 0){

                p.sendMessage(prefix + "Use: /modo <modo>");
                return true;

            }

            for(String mode : FilesManager.modes.getConfigurationSection("Modos").getKeys(false)){

                //Mode_Information______________________________________________________________________________________
                String mode_name = FilesManager.modes.getString("Modos." + mode + ".Name");
                String mode_permission = FilesManager.modes.getString("Modos." + mode + ".Permission");
                int mode_activeTime = FilesManager.modes.getInt("Modos." + mode + ".Active_Time");
                int mode_delayTime = FilesManager.modes.getInt("Modos." + mode + ".Delay_Time");
                int mode_STR = FilesManager.modes.getInt("Modos." + mode + ".Stats.STR");
                int mode_DEX = FilesManager.modes.getInt("Modos." + mode + ".Stats.DEX");
                int mode_CON = FilesManager.modes.getInt("Modos." + mode + ".Stats.CON");
                int mode_WIL = FilesManager.modes.getInt("Modos." + mode + ".Stats.WIL");
                int mode_SPI = FilesManager.modes.getInt("Modos." + mode + ".Stats.SPI");

                //Command_______________________________________________________________________________________________
                if(args[0].equals(mode)){
                    if(p.hasPermission(mode_permission)){

                        if(Constructs.getPlayerModo(p).contains(mode + "_delay")){

                            p.sendMessage(prefix + delayedMode.replace("{modo}", mode_name));
                            return true;

                        }else if(Constructs.getPlayerModo(p).contains(mode + "_active")){

                            p.sendMessage(prefix + activatedMode.replace("{modo}", mode_name));
                            return true;

                        }

                        if(Constructs.getActiveModes(p).equals(modeLimit)){

                            p.sendMessage(prefix + maxModes);
                            return true;

                        }

                        CooldownsManager.ActiveMode(p,
                                mode_activeTime, mode_delayTime, mode, mode_name,
                                mode_STR, mode_DEX, mode_CON, mode_WIL, mode_SPI);

                        p.sendMessage(prefix + activeMode.replace("{modo}", mode_name));
                        JinRyuuStats.addStats(p, mode_STR, mode_DEX, mode_CON, mode_WIL, mode_SPI);
                        modeActive.add(p.getName() + "_" + mode_name);
                        for (String command : FilesManager.modes.getStringList("Modos." + mode + ".CommandsOn")) {
                            if(command.equalsIgnoreCase(" ")){

                            }else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                        }

                        if(Constructs.getPlayerModo(p).equals("Nenhum")){

                            Constructs.setActiveModes(p, String.valueOf(addActiveModes));
                            Constructs.setPlayerModo(p, mode + "_active");
                            return false;

                        }else {

                            Constructs.setActiveModes(p, String.valueOf(addActiveModes));
                            Constructs.setPlayerModo(p, Constructs.getPlayerModo(p) + ", " + mode + "_active");
                            return false;

                        }

                    }else p.sendMessage(prefix + withoutMode);
                }

            }

        }

        return false;
    }
}
