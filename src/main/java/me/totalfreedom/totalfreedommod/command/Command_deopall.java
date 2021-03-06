package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH, blockHostConsole = true)
@CommandParameters(description = "Welcome to TotalNathaniel, the free DE-op server", usage = "/<command>")
public class Command_deopall extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        FUtil.adminAction(sender.getName(), "Welcome to TotalNathaniel, free op server, more like free DE-op.", true);

        for (Player player : server.getOnlinePlayers())
        {
            player.setOp(false);
            player.sendMessage(FreedomCommand.YOU_ARE_NOT_OP);
        }

        return true;
    }
}
