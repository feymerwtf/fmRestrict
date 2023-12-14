package ru.feymer.fmrestrict.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.feymer.fmrestrict.FmRestrict;
import ru.feymer.fmrestrict.utils.Utils;

public class FmRestrictCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                if (sender.hasPermission("fmrestrict.reload")) {
                    FmRestrict.getInstance().reloadConfig();
                    Utils.sendMessage(sender, Utils.getString("messages.commands.reload"));
                } else {
                    Utils.sendMessage(sender, Utils.getString("messages.commands.no-permission"));
                }
                return true;
            } else {
                Utils.sendMessage(sender, Utils.getString("messages.commands.no-args"));

            }
            return true;
        } else {
            Utils.sendMessage(sender, Utils.getString("messages.commands.no-args"));
        }
        return false;
    }
}
