package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.admin.Admin;
import me.totalfreedom.totalfreedommod.rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "Be a creep and spy on commands", usage = "/<command>", aliases = "commandspy")
public class Command_cmdspy extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {

        Admin admin = plugin.al.getAdmin(playerSender);
        admin.setCommandSpy(!admin.getCommandSpy());
        plugin.al.save(admin);
        plugin.al.updateTables();
        msg("Creep mode " + (admin.getCommandSpy() ? "enabled." : "disabled."));

        return true;
    }
}
