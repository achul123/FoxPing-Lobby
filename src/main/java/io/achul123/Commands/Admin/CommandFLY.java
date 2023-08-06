package io.achul123.Commands.Admin;

import io.achul123.Utils.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CommandFLY implements CommandExecutor {

    private final Set<UUID> flyingPlayers;

    public CommandFLY() {
        flyingPlayers = new HashSet<>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player) || args.length > 1) return false;

        Player player = (Player) sender;
        UUID playerUUID = player.getUniqueId();

        if (flyingPlayers.contains(playerUUID)) {
            player.setAllowFlight(false);
            player.setFlying(false);
            flyingPlayers.remove(playerUUID);
            player.sendMessage(Utilities.parsePlaceholders(player, Utilities.config.getString("fly-disable")));
        } else {
            player.setAllowFlight(true);
            player.setFlying(true);
            flyingPlayers.add(playerUUID);
            player.sendMessage(Utilities.parsePlaceholders(player, Utilities.config.getString("fly-enable")));
        }

        return true;
    }
}
