package flysystem.hj320;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public final class HJ320 extends JavaPlugin {
    public static HJ320 getInstance() {return plugin; }
    private static HJ320 plugin;
    @Override
    public void onEnable() {
        plugin = this;
        config.setup();
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-----------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Plugin:"+plugin.getName()+" Loaded]");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "-----------------------------------");
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new fly_system(this), this);
        pm.registerEvents(new onjoin(), this);
//        pm.registerEvents(new toggles(this), this);
//        pm.registerEvents(new config(this), this);
        fly_system.getconfigloadinram.put("hj320hourconfig", config.gethoursconfig().getString("time.mins"));

    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "-----------------------------------");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "[Plugin:"+plugin.getName()+" Un Loaded]");
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.RED + "-----------------------------------");
        fly_system.storeplayerhours.clear();
        fly_system.isflyshitoncache.clear();
        fly_system.isparticlesshitoncache.clear();
        fly_system.getconfigloadinram.clear();
    }
}
