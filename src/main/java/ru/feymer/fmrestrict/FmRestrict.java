package ru.feymer.fmrestrict;

import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import ru.feymer.fmrestrict.commands.FmRestrictCommand;
import ru.feymer.fmrestrict.listeners.EventListener;
import ru.feymer.fmrestrict.utils.Config;
import ru.feymer.fmrestrict.utils.Hex;
import ru.feymer.fmrestrict.utils.Updater;

public final class FmRestrict extends JavaPlugin {

    public static FmRestrict instance;

    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&c» &fПлагин &c" + getPlugin(FmRestrict.class).getName() + " &fвключился&f!"));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&c» &fВерсия: &cv" + getPlugin(FmRestrict.class).getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
        Config.loadYamlFile(this);
        this.getCommand("fmrestrict").setExecutor(new FmRestrictCommand());
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        Updater updater = new Updater(this);
        updater.start();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&c» &fПлагин &c" + getPlugin(FmRestrict.class).getName() + " &fвыключился&f!"));
        Bukkit.getConsoleSender().sendMessage(Hex.color("&c» &fВерсия: &cv" + getPlugin(FmRestrict.class).getDescription().getVersion()));
        Bukkit.getConsoleSender().sendMessage(Hex.color(""));
    }

    public static FmRestrict getInstance() {
        return instance;
    }
}
