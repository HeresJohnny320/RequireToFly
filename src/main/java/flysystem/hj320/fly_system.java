package flysystem.hj320;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class fly_system implements Listener {
    public static Map<UUID, String> isflyshitoncache = new HashMap<UUID, String>();
    public static Map<UUID, String> isparticlesshitoncache = new HashMap<UUID, String>();
    public static Map<UUID, String> storeplayermins = new HashMap<UUID, String>();
    public static Map<String, String> getconfigloadinram = new HashMap<String, String>();
    public fly_system(HJ320 hj320) {
        hj320.getCommand("fly").setExecutor(new toggles());
        hj320.getCommand("reqtofly").setExecutor(new toggles());
        hj320.getCommand("flyparticles").setExecutor(new toggles());
        hj320.getCommand("reqtoflyparticles").setExecutor(new toggles());
        hj320.getCommand("playtime").setExecutor(new toggles());
        hj320.getCommand("reqtoflyplaytime").setExecutor(new toggles());

        Bukkit.getScheduler().runTaskTimer(hj320.getInstance(), new Runnable() { //run loop
            @Override
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                if(player.isOnline() == true){
                        String mode = player.getGameMode().toString();
                        if (mode == "CREATIVE" || mode == "SPECTATOR") {player.setAllowFlight(true);} else {
                            if(isplayerinflyon(player) == true && player.hasPermission("hj320.fly")||isplayerinflyon(player) == true && player.hasPermission("hj320.fly.req")){
                                if(getplayersmins(player) == null)return;
                                if(geconfigram() == null)return;
                                if (Integer.parseInt(getplayersmins(player))  <= Integer.parseInt(geconfigram())) {player.setAllowFlight(false);} else {
                                        if (Integer.parseInt(getplayersmins(player)) >= Integer.parseInt(geconfigram())) {player.setAllowFlight(true);}
                                        if (player.isFlying()|| player.isGliding()) {
                                        if(isplayerinparton(player) == true && player.hasPermission("hj320.particles")){
                                            player.getWorld().spawnParticle(Particle.SPIT, player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(), 4, 0, 0, 0, 0);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }, 1L, 1L);

        Bukkit.getScheduler().runTaskTimer(hj320.getInstance(), new Runnable() { //run loop
            @Override
            public void run() {
                for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                    if(player.isOnline() == true){
                        int up5 = Integer.parseInt(storeplayermins.get(player.getUniqueId()))+5;

//                        System.out.println("player = "+player.getName());
//                        System.out.println("up5 set to "+up5);
//                        System.out.println("is player fly on = "+isplayerinflyon(player));
//                        System.out.println("is player part on = "+isplayerinparton(player));
//                        System.out.println("get config = "+geconfigram());
//                        System.out.println("get players mins = "+getplayershours(player));
//                        System.out.println("player has hj320.fly = "+player.hasPermission("hj320.fly"));
//                        System.out.println("player has hj320.particles = "+player.hasPermission("hj320.particles"));

                        storeplayermins.replace(player.getUniqueId(), up5+"");
                        cache.save_user_data(player.getUniqueId(), "mins_user_has_played", up5+"");
                    }
                }
            }
        }, 6000L, 6000L);
    }

    public static boolean isplayerinflyon(Player p) {
        if(isflyshitoncache.get(p.getUniqueId()) == null)return false;
        if(isflyshitoncache.get(p.getUniqueId()).equals("1"))return true;
        return false;
    }
    public static boolean isplayerinparton(Player p) {
        if(isparticlesshitoncache.get(p.getUniqueId()) == null)return false;
        if(isparticlesshitoncache.get(p.getUniqueId()).equals("1"))return true;
        return false;
    }
    public static String geconfigram() {
        if(getconfigloadinram.get("hj320hourconfig") == null)return "0";
        return getconfigloadinram.get("hj320hourconfig");
    }
    public static String getplayersmins(Player p) {
        if(storeplayermins.get(p.getUniqueId()) == null)return "0";
        return storeplayermins.get(p.getUniqueId());
    }
    public static String convert_time(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        return ChatColor.AQUA+"Time Played "+String.format("%02d:%02d", hours, minutes);
    }

}
