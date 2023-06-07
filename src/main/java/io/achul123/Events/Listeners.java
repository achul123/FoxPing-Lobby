package io.achul123.Events;

import io.achul123.Utils.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static io.achul123.Main.worlds;

public class Listeners implements Listener {


    @EventHandler(priority = EventPriority.HIGHEST)
    public void hungerChangeEvent(FoodLevelChangeEvent event) {
        if(worlds.isEmpty()) return;
        if(event.getEntityType() != EntityType.PLAYER) return;
        Player player = (Player)event.getEntity();
        if(worlds.contains(player.getWorld().getName())) {
            player.setFoodLevel(20);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // message join
        YamlConfiguration config = Utilities.configGet();
        boolean onJoinEnabled = config.getBoolean("join-quit-message.enabled");
        if (onJoinEnabled) {
            String message = config.getString("join-quit-message.join-message");
            if (message == null) {
                Bukkit.getLogger().severe("No join message was set in the config !");
            }
            message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);
            message = message.replace("&", "§");
            event.setJoinMessage(message);
            Player player = event.getPlayer();
            // spawn
            Location spawnLocation = new Location(player.getWorld(), -9.650, 32, -136.420, 90.0f, -1.0f );
            player.teleport(spawnLocation);
            // clear inventory
            player.getInventory().clear();

        }
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean onLeaveEnabled = config.getBoolean("join-quit-message.enabled");
        if (onLeaveEnabled) {
            String message = config.getString("join-quit-message.leave-message");
            if (message == null) {
                Bukkit.getLogger().severe("No leave message was set in the config !");
                return;
            }
            message = PlaceholderAPI.setPlaceholders(event.getPlayer(), message);
            message = message.replace("&", "§");
            event.setQuitMessage(message);
        }
    }
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        YamlConfiguration config = Utilities.configGet();
        boolean chatFormatEnabled = config.getBoolean("chat-message.enabled");
        if (chatFormatEnabled) {
            String tosend = config.getString("chat-message.player-chat");
            if (tosend == null) {
                Bukkit.getLogger().severe("No chat message was set in the config !!!!!!! DumbASS");
                return;
            }
            tosend = PlaceholderAPI.setPlaceholders(event.getPlayer(), tosend);
            tosend = tosend.replace("&", "§").replace("%message%", event.getMessage());
            tosend = tosend.replaceAll("\\%", "$0$0");
            event.setFormat(tosend);
        }
    }

    @EventHandler
    public void onPlayerBreak(BlockBreakEvent event){
        event.setCancelled(true);
    }
}
