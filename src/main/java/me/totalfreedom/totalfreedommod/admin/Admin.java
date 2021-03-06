package me.totalfreedom.totalfreedommod.admin;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;
import me.totalfreedom.totalfreedommod.LogViewer.LogsRegistrationMode;
import me.totalfreedom.totalfreedommod.TotalFreedomMod;
import me.totalfreedom.totalfreedommod.rank.Rank;
import me.totalfreedom.totalfreedommod.util.FUtil;
import net.pravian.aero.util.Ips;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Admin
{

    @Getter
    @Setter
    private String name;
    @Getter
    private boolean active = true;
    @Getter
    @Setter
    private Rank rank = Rank.SUPER_ADMIN;
    @Getter
    private final List<String> ips = Lists.newArrayList();
    private final List<String> backupCodes = Lists.newArrayList();
    @Getter
    @Setter
    private Date lastLogin = new Date();
    @Getter
    @Setter
    private String loginMessage = null;
    @Getter
    @Setter
    private String discordID = null;
    @Getter
    @Setter
    private String tag = null;
    @Getter
    @Setter
    private Boolean commandSpy = false;
    @Getter
    @Setter
    private Boolean potionSpy = false;
    @Getter
    @Setter
    private String acFormat = null;
    @Getter
    @Setter
    private Boolean oldTags = false;
    @Getter
    @Setter
    private Boolean logStick = false;

    public static final String CONFIG_FILENAME = "admins.yml";

    public Admin(Player player)
    {
        this.name = player.getName();
        this.ips.add(Ips.getIp(player));
    }

    public Admin(String username, List<String> ips, Rank rank, Boolean active, Date lastLogin, String loginMessage, String tag, String discordID, List<String> backupCodes, Boolean commandSpy, Boolean potionSpy, String acFormat, Boolean oldTags, Boolean logStick)
    {
        this.name = username;
        this.active = active;
        this.rank = rank;
        this.ips.clear();
        this.ips.addAll(ips);
        this.lastLogin = lastLogin;
        this.loginMessage = loginMessage;
        this.tag = tag;
        this.discordID = discordID;
        this.backupCodes.clear();
        this.backupCodes.addAll(backupCodes);
        this.commandSpy = commandSpy;
        this.potionSpy = potionSpy;
        this.acFormat = acFormat;
        this.oldTags = oldTags;
        this.logStick = logStick;
    }

    @Override
    public String toString()
    {
        final StringBuilder output = new StringBuilder();

        output.append("Admin: ").append(name).append("\n")
                .append("- IPs: ").append(StringUtils.join(ips, ", ")).append("\n")
                .append("- Last Login: ").append(FUtil.dateToString(lastLogin)).append("\n")
                .append("- Custom Login Message: ").append(loginMessage).append("\n")
                .append("- Rank: ").append(rank.getName()).append("\n")
                .append("- Is Active: ").append(active).append("\n")
                .append("- Discord ID: ").append(discordID).append("\n")
                .append("- Tag: ").append(tag).append("\n").append(ChatColor.GRAY)
                .append("- Potion Spy: ").append(potionSpy).append("\n")
                .append("- Admin Chat Format: ").append(acFormat).append("\n")
                .append("- Old Tags: ").append(oldTags).append("\n")
                .append("- Log Stick: ").append(logStick).append("\n")
                .append("- Backup Codes: ").append(backupCodes.size()).append("/10");

        return output.toString();
    }

    public void loadFrom(Player player)
    {
        name = player.getName();
        ips.clear();
        ips.add(Ips.getIp(player));
    }

    public Map<String, Object> toSQLStorable()
    {
        Map<String, Object> map = new HashMap<String, Object>()
        {{
            put("username", name);
            put("active", active);
            put("rank", rank.toString());
            put("ips", FUtil.listToString(ips));
            put("backup_codes", FUtil.listToString(backupCodes));
            put("last_login", lastLogin.getTime());
            put("login_message", loginMessage);
            put("discord_id", discordID);
            put("tag", tag);
            put("command_spy", commandSpy);
            put("potion_spy", potionSpy);
            put("ac_format", acFormat);
            put("old_tags", oldTags);
            put("log_stick", logStick);
        }};
        return map;
    }

    public boolean isAtLeast(Rank pRank)
    {
        return rank.isAtLeast(pRank);
    }

    public boolean hasLoginMessage()
    {
        return loginMessage != null && !loginMessage.isEmpty();
    }

    // Util IP methods
    public void addIp(String ip)
    {
        if (!ips.contains(ip))
        {
            ips.add(ip);
        }
    }

    public void addIps(List<String> ips)
    {
        for (String ip : ips)
        {
            addIp(ip);
        }
    }

    public void removeIp(String ip)
    {
        if (ips.contains(ip))
        {
            ips.remove(ip);
        }
    }

    public void clearIPs()
    {
        ips.clear();
    }

    public List<String> getBackupCodes()
    {
        return Collections.unmodifiableList(backupCodes);
    }

    public void setBackupCodes(List<String> codes)
    {
        backupCodes.clear();
        backupCodes.addAll(codes);
    }

    public void removeBackupCode(String code)
    {
        backupCodes.remove(code);
    }

    public void setActive(boolean active)
    {
        this.active = active;

        final TotalFreedomMod plugin = TotalFreedomMod.plugin();

        if (!active)
        {
            if (getRank().isAtLeast(Rank.TELNET_ADMIN))
            {
                if (plugin.btb != null)
                {
                    plugin.btb.killTelnetSessions(getName());
                }
            }

            plugin.lv.updateLogsRegistration(null, getName(), LogsRegistrationMode.DELETE);
        }
    }

    public boolean isValid()
    {
        return name != null
                && rank != null
                && !ips.isEmpty()
                && lastLogin != null;
    }
}
