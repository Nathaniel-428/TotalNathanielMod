package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.config.ConfigEntry;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.ONLY_CONSOLE)
@CommandParameters(description = "For telnet pussies", usage = "/<command> <message>", aliases = "csay")
public class Command_consolesay extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 0)
        {
            return false;
        }

        String message = StringUtils.join(args, " ");
        FUtil.bcastMsg(String.format("§7[TELNET PUSSY] §c%s §8\u00BB §f%s", sender.getName(), StringUtils.join(args, " ")));
        plugin.dc.messageChatChannel("[TELNET PUSSY] " + plugin.dc.deformat(sender.getName()) + " \u00BB " + ChatColor.stripColor(message));
        return true;
    }
}
