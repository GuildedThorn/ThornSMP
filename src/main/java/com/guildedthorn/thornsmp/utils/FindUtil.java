package com.guildedthorn.thornsmp.utils;

import com.guildedthorn.thornsmp.player.SMPPlayer;
import com.kingrainbow44.crafttools.player.CraftPlayerManager;
import com.lunarclient.bukkitapi.LunarClientAPI;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import static com.kingrainbow44.crafttools.utils.CraftUtils.colorize;

public final class FindUtil {
    public static void showLocation(Player showTo, Player showFrom) {
        Location location = showFrom.getLocation();
        
        LunarClientAPI lunarApi = LunarClientAPI.getInstance();
        if(lunarApi.isRunningLunarClient(showTo)) {
            LCWaypoint waypoint = new LCWaypoint(
                    showFrom.getDisplayName() + "'s Location",
                    location, Color.RED.asRGB(),
                    false, true
            );
            
            lunarApi.sendWaypoint(showTo, waypoint);
            
            SMPPlayer smpShowTo = CraftPlayerManager.self().getCraftPlayer(showTo, SMPPlayer.class);
            if(smpShowTo.getWaypoint() != null) {
                lunarApi.removeWaypoint(showTo, smpShowTo.getWaypoint());
            } smpShowTo.setWaypoint(waypoint);
        } else {
            showTo.sendMessage(colorize("&a" + showFrom.getDisplayName() + "'s Location:"));
            showTo.sendMessage(colorize("&aX: &b" + location.getX()));
            showTo.sendMessage(colorize("&aY: &b" + location.getY()));
            showTo.sendMessage(colorize("&aZ: &b" + location.getZ()));
        }
    }
}
