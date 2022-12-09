package flysystem.hj320;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class cache {
    private static FileConfiguration saveuserdata;
    public static void save_user_data(UUID uuid, String item, String item2) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin(HJ320.getInstance().getName()).getDataFolder(), "userdata/" + uuid + ".yml");
        if (!file.exists()) {try {file.createNewFile();} catch (IOException e) {}}
        saveuserdata = YamlConfiguration.loadConfiguration(file);
        saveuserdata.addDefault(uuid + "." + item, item2);
        saveuserdata.set(uuid + "." + item, item2);
        saveuserdata.options().copyDefaults(true);
        HJ320.getInstance().saveConfig();
        try {saveuserdata.save(file);} catch (IOException e) {Bukkit.getServer().getConsoleSender().sendMessage("Couldn't save file" + file);}
    }
    public static String get_user_data(UUID uuid, String item) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin(HJ320.getInstance().getName()).getDataFolder(), "userdata/" + uuid + ".yml");
        if (!file.exists()) return null;

        return YamlConfiguration.loadConfiguration(file).getString(uuid + "." + item);
    }
    public static boolean get_user_data_exists(UUID uuid) {
        File file = new File(Bukkit.getServer().getPluginManager().getPlugin(HJ320.getInstance().getName()).getDataFolder(), "userdata/" + uuid + ".yml");
        if (!file.exists()) return false;
        return true;
    }
}
