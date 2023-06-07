package io.achul123;

import io.achul123.Events.Listeners;
import io.achul123.Utils.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    public static List<String> worlds;

    @Override
    public void onEnable() {
        Utilities.defaultConfigSave();
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        loadWorlds();

    }

    @Override
    public void onDisable() {

    }

    public void loadWorlds() {
        worlds = new ArrayList<>();
        for (String world : getConfig().getStringList("worlds")) {
            if (Bukkit.getWorld(world) == null) {
                getLogger().info("Invalid world in config: " + world);
            } else {
                worlds.add(world);
            }
        }
    }
}
