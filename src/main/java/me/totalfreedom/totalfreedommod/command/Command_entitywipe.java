package me.totalfreedom.totalfreedommod.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import me.totalfreedom.totalfreedommod.util.Groups;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

@CommandPermissions(level = Rank.SUPER_ADMIN, source = SourceType.BOTH)
@CommandParameters(description = "Admins use this command too much", usage = "/<command> [name | -a]", aliases = "ew,rd")
public class Command_entitywipe extends FreedomCommand
{

    @Override
    public boolean run(CommandSender sender, Player playerSender, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        EntityType type = null;
        String entityName = null;
        boolean bypassBlacklist = false;
        if (args.length > 0)
        {
            if (args[0].equals("-a"))
            {
                bypassBlacklist = true;
            }
            else
            {
                try
                {
                    type = EntityType.valueOf(args[0].toUpperCase());
                }
                catch (Exception e)
                {
                    msg(args[0] + " is not a valid entity type. Put real entities and not some fairytale creatures", ChatColor.RED);
                    return true;
                }

                if (!getAllEntities().contains(type))
                {
                    msg(FUtil.formatName(type.name()) + " is an entity, however: it is a mob. So, that means you're screwed.", ChatColor.RED);
                    return true;
                }
            }
        }

        if (type != null)
        {
            entityName = FUtil.formatName(type.name());
        }

        FUtil.adminAction(sender.getName(), "Purging all " + (type != null ? entityName + "s" : "entities") + " I use this command too much....", true);
        int count;
        if (type != null)
        {
            count = plugin.ew.wipeEntities(type);
        }
        else
        {
            count = plugin.ew.wipeEntities(bypassBlacklist);
        }
        msg(count + " " + (type != null ? entityName : "entities") + FUtil.showS(count) + " removed. Why not just kill them with a sword?");
        return true;
    }

    public static List<EntityType> getAllEntities()
    {
        List<EntityType> entityTypes = new ArrayList<>();
        for (EntityType entityType : EntityType.values())
        {
            if (!Groups.MOB_TYPES.contains(entityType))
            {
                entityTypes.add(entityType);
            }
        }
        return entityTypes;
    }

    public static List<String> getAllEntityNames()
    {
        List<String> names = new ArrayList<>();
        for (EntityType entityType : getAllEntities())
        {
            names.add(entityType.name());
        }
        return names;
    }

    @Override
    public List<String> getTabCompleteOptions(CommandSender sender, Command command, String alias, String[] args)
    {
        List<String> names = getAllEntityNames();
        names.add("-a");
        if (args.length == 1)
        {
            return names;
        }

        return Collections.emptyList();
    }
}
