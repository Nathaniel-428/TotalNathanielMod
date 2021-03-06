package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.BOTH)
@CommandParameters(description = "Place a cool attack on someone", usage = "/<command> <playername>")
public class Command_forcekill extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (!plugin.al.isAdmin(sender) && !senderIsConsole)
        {
            FUtil.adminAction(sender.getName(), "Placing a cool MLG attack on " + playerSender.getName(), false);
            playerSender.getWorld().strikeLightningEffect(playerSender.getLocation());
            playerSender.setHealth(0);
            return true;
        }

        if (args.length < 1)
        {
            return false;
        }

        final Player player = getPlayer(args[0]);
        if (player == null)
        {
            msg(PLAYER_NOT_FOUND);
            return true;
        }

        player.setHealth(0);

        return true;
    }
}
