package me.ry4nn00b.modes.Utilities;

import me.dpohvar.powernbt.api.NBTCompound;
import me.dpohvar.powernbt.api.NBTManager;
import org.bukkit.entity.Player;

public class JinRyuuStats {

    public static void addStats(Player p, int STR, int DEX, int CON, int WIL, int SPI) {

        NBTManager manager = NBTManager.getInstance();
        NBTCompound ForgeData = NBTManager.getInstance().readForgeData(p);
        NBTCompound playerData = (NBTCompound) ForgeData.get("PlayerPersisted");

        int PlayerSTR = playerData.getInt("jrmcStrI");
        int PlayerDEX = playerData.getInt("jrmcDexI");
        int PlayerCON = playerData.getInt("jrmcCnsI");
        int PlayerWIL = playerData.getInt("jrmcWilI");
        int PlayerSPI = playerData.getInt("jrmcCncI");

        int STRFinal = PlayerSTR + STR;
        int DEXFinal = PlayerDEX + DEX;
        int CONFinal = PlayerCON + CON;
        int WILFinal = PlayerWIL + WIL;
        int SPIFinal = PlayerSPI + SPI;

        playerData.put("jrmcStrI", STRFinal);
        playerData.put("jrmcDexI", DEXFinal);
        playerData.put("jrmcCnsI", CONFinal);
        playerData.put("jrmcWilI", WILFinal);
        playerData.put("jrmcCncI", SPIFinal);

        ForgeData.put("PlayerPersisted", playerData);
        manager.writeForgeData(p, ForgeData);

    }

    public static void removeStats(Player p, int STR, int DEX, int CON, int WIL, int SPI) {

        NBTManager manager = NBTManager.getInstance();
        NBTCompound ForgeData = NBTManager.getInstance().readForgeData(p);
        NBTCompound playerData = (NBTCompound) ForgeData.get("PlayerPersisted");

        int PlayerSTR = playerData.getInt("jrmcStrI");
        int PlayerDEX = playerData.getInt("jrmcDexI");
        int PlayerCON = playerData.getInt("jrmcCnsI");
        int PlayerWIL = playerData.getInt("jrmcWilI");
        int PlayerSPI = playerData.getInt("jrmcCncI");

        int STRFinal = PlayerSTR - STR;
        int DEXFinal = PlayerDEX - DEX;
        int CONFinal = PlayerCON - CON;
        int WILFinal = PlayerWIL - WIL;
        int SPIFinal = PlayerSPI - SPI;

        playerData.put("jrmcStrI", STRFinal);
        playerData.put("jrmcDexI", DEXFinal);
        playerData.put("jrmcCnsI", CONFinal);
        playerData.put("jrmcWilI", WILFinal);
        playerData.put("jrmcCncI", SPIFinal);

        ForgeData.put("PlayerPersisted", playerData);
        manager.writeForgeData(p, ForgeData);

    }

}
