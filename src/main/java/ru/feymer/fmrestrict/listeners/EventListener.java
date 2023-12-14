package ru.feymer.fmrestrict.listeners;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import ru.feymer.fmrestrict.utils.Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EventListener implements Listener {

    private final Essentials essentialsPlugin = (Essentials) Bukkit.getServer().getPluginManager().getPlugin("Essentials");
    private final Map<Player, Long> lastMessageTimeMap = new HashMap<>();

    @EventHandler
    public void onPickupItems(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        if (this.cancelPickupItems(player)) {
            e.setCancelled(true);
        }
    }

    public boolean cancelPickupItems(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long)this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.pickup-items.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.pickup-items")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.pickup-items"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.pickup-items.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.pickup-items")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.pickup-items"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.pickup-items.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.pickup-items")) {
            this.sendDelayedMessage(player, Utils.getString("messages.vanish.pickup-items"));
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (this.cancelBlockPlace(player)) {
            e.setCancelled(true);
        }
    }

    public boolean cancelBlockPlace(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long)this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = this.essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.block-place.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.block-place")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.block-place"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.block-place.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.block-place")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.block-place"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.block-place.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.block-place")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.block-place"));
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (this.cancelBlockBreak(player)) {
            e.setCancelled(true);
        }
    }

    public boolean cancelBlockBreak(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long)this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = this.essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.block-break.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.block-break")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.block-break"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.block-break.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.block-break")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.block-break"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.block-break.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.block-break")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.block-break"));
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        Bukkit.getConsoleSender().sendMessage(message);
        for (String commands : Utils.getStringList("settings.block-commands")) {
            if (message.equalsIgnoreCase(commands)) {
                if (this.cancelUseCommand(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    public boolean cancelUseCommand(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long) this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = this.essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.block-commands.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.block-commands")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.block-commands"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.block-commands.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.block-commands")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.block-commands"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.block-commands.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.block-commands")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.block-commands"));
            return true;
        } else {
            return false;
        }
    }

    @EventHandler
    public void interactEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block clickedBlock = event.getClickedBlock();
        if (clickedBlock != null && clickedBlock.getType() == Material.CHEST) {
            if (this.cancelChestOpen(player)) {
                event.setCancelled(true);
                return;
            }
        }
        if (clickedBlock != null && clickedBlock.getType() == Material.ENDER_CHEST) {
            if (this.cancelEnderChestOpen(player)) {
                event.setCancelled(true);
                return;
            }
        }
        if (clickedBlock != null && clickedBlock.getType() == Material.SHULKER_BOX) {
            if (this.cancelShulkerOpen(player)) {
                event.setCancelled(true);
            }
        }
    }

    public boolean cancelChestOpen(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long)this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = this.essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.chest-open.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.chest-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.chest-open"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.chest-open.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.chest-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.chest-open"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.chest-open.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.chest-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.chest-open"));
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelEnderChestOpen(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long)this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = this.essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.enderchest-open.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.enderchest-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.enderchest-open"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.enderchest-open.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.enderchest-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.enderchest-open"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.enderchest-open.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.enderchest-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.enderchest-open"));
            return true;
        } else {
            return false;
        }
    }

    public boolean cancelShulkerOpen(Player player) {
        long currentTime = System.currentTimeMillis();
        if (this.lastMessageTimeMap.containsKey(player)) {
            long lastMessageTime = (Long)this.lastMessageTimeMap.get(player);
            if (currentTime - lastMessageTime < Utils.getLong("settings.cooldown-message") * 1000) {
                return true;
            }
        }

        User user = this.essentialsPlugin.getUser(player);
        if (!player.hasPermission("fmrestrict.bypass.shulker-open.flight-mode") && user.getBase().getAllowFlight() && Utils.getBoolean("settings.flight-mode.shulker-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.flight-mode.shulker-open"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.shulker-open.god-mode") && user.isGodModeEnabled() && Utils.getBoolean("settings.god-mode.shulker-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.shulker-open"));
            return true;
        } else if (!player.hasPermission("fmrestrict.bypass.shulker-open.vanish") && user.isVanished() && Utils.getBoolean("settings.vanish.shulker-open")) {
            this.sendDelayedMessage(player, Utils.getString("messages.god-mode.shulker-open"));
            return true;
        } else {
            return false;
        }
    }

    private void sendDelayedMessage(Player player, String message) {
        Utils.sendMessage(player, message);
        this.lastMessageTimeMap.put(player, System.currentTimeMillis());
    }
}
