package flysystem.hj320;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class toggles implements CommandExecutor {
    @Override
    public boolean onCommand (CommandSender sender, Command cmd, String label, String[]args){
        if (cmd.getName().equalsIgnoreCase("fly") || cmd.getName().equalsIgnoreCase("reqtofly")) {
            if (!sender.hasPermission("hj320.fly")) {
                sender.sendMessage("You don't have permission to do this.");
                return true;
            } else {
                UUID uuid = Bukkit.getPlayer(sender.getName()).getUniqueId();

                String fly = cache.get_user_data(uuid, "fly_enabled");
                if(fly != null){
                    if(fly.equals("1")){
                        cache.save_user_data(uuid, "fly_enabled", "0");
                        Bukkit.getPlayer(sender.getName()).setAllowFlight(false);
                        sender.sendMessage(ChatColor.RED +"Fly Disabled");
                        fly_system.isflyshitoncache.remove(uuid);
                    }else{
                        Bukkit.getPlayer(sender.getName()).setAllowFlight(true);
                        cache.save_user_data(uuid, "fly_enabled", "1");
                        sender.sendMessage(ChatColor.GREEN +"Fly Enabled");
                        fly_system.isflyshitoncache.put(uuid, "1");
                    }
                }
            }
        }
        if (cmd.getName().equalsIgnoreCase("flyparticles")||cmd.getName().equalsIgnoreCase("reqtoflyparticles")) {
            if (!sender.hasPermission("hj320.particles")) {
                sender.sendMessage("You don't have permission to do this.");
                return true;
            } else {
                UUID uuid = Bukkit.getPlayer(sender.getName()).getUniqueId();
                String particles = cache.get_user_data(uuid, "particles_enabled");
                if(particles != null){
                    if(particles.equals("1")){
                        cache.save_user_data(uuid, "particles_enabled", "0");
                        sender.sendMessage(ChatColor.RED +"Particles Disabled");
                        fly_system.isparticlesshitoncache.remove(uuid);
                    }else{
                        cache.save_user_data(uuid, "particles_enabled", "1");
                        sender.sendMessage(ChatColor.GREEN +"Particles Enabled");
                        fly_system.isparticlesshitoncache.put(uuid, "1");
                    }
                }
            }
        }
        return false;
    }
}
