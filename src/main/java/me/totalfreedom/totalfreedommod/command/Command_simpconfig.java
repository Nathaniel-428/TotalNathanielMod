package me.totalfreedom.totalfreedommod.command;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_simpconfig extends FreedomCommand
{
    @Override
    protected boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        msg("TODO: Add actual functionality for this command.");
        return false;
    }

    @Override
    protected List<String> getTabCompleteOptions(CommandSender sender, Command command, String alias, String[] args)
    {
        return super.getTabCompleteOptions(sender, command, alias, args);
    }
}
