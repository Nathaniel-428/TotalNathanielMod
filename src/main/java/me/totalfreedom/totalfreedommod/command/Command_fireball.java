package me.totalfreedom.totalfreedommod.command;

import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.shop.ShopItem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.OP, source = SourceType.ONLY_IN_GAME)
@CommandParameters(description = "I'm a fireball", usage = "/<command>")
public class Command_fireball extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (plugin.sh.getData(playerSender).hasItem(ShopItem.FIRE_BALL))
        {
            playerSender.getInventory().addItem(plugin.sh.getFireBall());
            msg("You have acquired one F I R E B A L L", ChatColor.GREEN);
        }
        else
        {
            msg("You're poor as fuck, and you don't own a fireball.", ChatColor.RED);
        }
        return true;
    }
}
