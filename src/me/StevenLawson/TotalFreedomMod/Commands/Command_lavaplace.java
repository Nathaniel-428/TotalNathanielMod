package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.SUPER, source = SourceType.BOTH)
@CommandParameters(description = "Enable/disable lava placement.", usage = "/<command> <on | off>")
public class Command_lavaplace extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length != 1)
        {
            return false;
        }

        playerMsg("Lava placement is now " + (TFM_ConfigEntry.ALLOW_LAVA_PLACE.setBoolean(!args[0].equalsIgnoreCase("off")) ? "enabled" : "disabled") + ".");

        return true;
    }
}
