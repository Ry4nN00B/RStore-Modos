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

import static me.ry4nn00b.modes.Commands.Mode.modeActive;

public class DMode implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        //Variables_____________________________________________________________________________________________________
        Player p = (Player) sender;
        int delActiveModes = Integer.parseInt(Constructs.getActiveModes(p)) - 1;

        //Messages______________________________________________________________________________________________________
        String prefix = FilesManager.messages.getString("Mensagens.Prefix").replace("&", "§");
        String disableMode = FilesManager.messages.getString("Mensagens.DisableMode").replace("&", "§");
        String noActiveModes = FilesManager.messages.getString("Mensagens.NoActiveModes").replace("&", "§");

        //Command_______________________________________________________________________________________________________
        if(cmd.getName().equalsIgnoreCase("dmodo")){
            if(args.length == 0){

                p.sendMessage(prefix + "Use: /dmodo <modo>");
                return true;

            }
            if(args.length == 1) {

                for (String mode : FilesManager.modes.getConfigurationSection("Modos").getKeys(false)) {

                    //Mode_Information______________________________________________________________________________________
                    String mode_name = FilesManager.modes.getString("Modos." + mode + ".Name");
                    int mode_delayTime = FilesManager.modes.getInt("Modos." + mode + ".Delay_Time");
                    int mode_STR = FilesManager.modes.getInt("Modos." + mode + ".Stats.STR");
                    int mode_DEX = FilesManager.modes.getInt("Modos." + mode + ".Stats.DEX");
                    int mode_CON = FilesManager.modes.getInt("Modos." + mode + ".Stats.CON");
                    int mode_WIL = FilesManager.modes.getInt("Modos." + mode + ".Stats.WIL");
                    int mode_SPI = FilesManager.modes.getInt("Modos." + mode + ".Stats.SPI");

                    //Command_______________________________________________________________________________________________
                    if(args[0].equalsIgnoreCase(mode)){

                        if (Constructs.getActiveModes(p).equals("0")) {
                            p.sendMessage(prefix + noActiveModes);
                            return true;
                        }

                        if(Constructs.getPlayerModo(p).contains(mode + "_delay")){
                            p.sendMessage(prefix + "Este modo já está desativado.");
                            return true;
                        }

                        if(!Constructs.getPlayerModo(p).contains(mode + "_active") || Constructs.getPlayerModo(p).contains(mode + "_delay")){
                            p.sendMessage(prefix + "Você não está com este modo ativo!");
                            return true;
                        }

                        if(Constructs.getPlayerModo(p).contains(mode + "_active")){

                            Constructs.setPlayerModo(p, Constructs.getPlayerModo(p).replace(mode + "_active", mode + "_delay"));
                            Constructs.setActiveModes(p, String.valueOf(delActiveModes));
                            JinRyuuStats.removeStats(p, mode_STR, mode_DEX, mode_CON, mode_WIL, mode_SPI);
                            p.sendMessage(prefix + disableMode.replace("{modo}", mode_name));
                            modeActive.remove(p.getName() + "_" + mode_name);
                            for (String command : FilesManager.modes.getStringList("Modos." + mode + ".CommandsOff")) {
                                if(command.equalsIgnoreCase(" ")){

                                }else Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player}", p.getName()));
                            }

                            CooldownsManager.delayMode(p, mode, mode_name, mode_delayTime);

                        }
                    }

                }

            }

        }

        return false;
    }
}
