package flysystem.hj320;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class config {
    private static File serverinfo;
    private static FileConfiguration config_set_hours;
    public static void setup(){
        serverinfo = Bukkit.getServer().getPluginManager().getPlugin(HJ320.getInstance().getName()).getDataFolder();
        setupconfigspawn();
    }
    private static void setupconfigspawn(){
        File file = new File(serverinfo, "config.yml");
        if (!file.exists()){ try{ file.createNewFile(); }catch (IOException e){ } }
        config_set_hours = YamlConfiguration.loadConfiguration(file);
        config_set_hours.addDefault("time.mins", 3000); // 50 hours
        config_set_hours.options().copyDefaults(true);
        HJ320.getInstance().saveConfig();
        try{ config_set_hours.save(file); }catch (IOException e){ Bukkit.getServer().getConsoleSender().sendMessage("Couldn't save file" + file); }
    }
    public static FileConfiguration gethoursconfig(){ return config_set_hours; }
}
