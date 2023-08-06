package io.achul123.Commands.Admin;

import io.achul123.Utils.Utilities;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGM implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            if (args[0] != null) {
                Player player = (Player) sender;

                player.sendMessage(Utilities.parsePlaceholders(player, Utilities.config.getString("gm-message")));
                if (args[0].equalsIgnoreCase("0")) {
                    player.setGameMode(GameMode.SURVIVAL);
                } else if (args[0].equalsIgnoreCase("1")) {
                    player.setGameMode(GameMode.CREATIVE);
                } else if (args[0].equalsIgnoreCase("2")) {
                    player.setGameMode(GameMode.ADVENTURE);
                } else if (args[0].equalsIgnoreCase("3")) {
                    player.setGameMode(GameMode.SPECTATOR);
                }
            }
        } return false;
    }
}
