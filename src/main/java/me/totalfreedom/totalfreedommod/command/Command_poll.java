package me.totalfreedom.totalfreedommod.command;

import java.util.List;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.NON_OP, source = SourceType.BOTH)
@CommandParameters(description = "Vote or create a poll", usage = "/<command> <vote <option>|create <Title|>|stop>")

public class Command_poll extends FreedomCommand
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
