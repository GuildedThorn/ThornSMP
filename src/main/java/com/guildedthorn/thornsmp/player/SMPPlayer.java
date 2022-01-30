package com.guildedthorn.thornsmp.player;

import com.guildedthorn.thornsmp.ThornSMP;
import com.kingrainbow44.crafttools.CraftPlayer;
import com.lunarclient.bukkitapi.object.LCWaypoint;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public final class SMPPlayer extends CraftPlayer {
    private LCWaypoint waypoint;
    
    public void setWaypoint(LCWaypoint waypoint) {
        this.waypoint = waypoint;
    }
    
    @Nullable public LCWaypoint getWaypoint() {
        return this.waypoint;
    }
    
    @Override
    public void onRegister(Player player) {
        ThornSMP.getInstance().getBossBar().addPlayer(player);
    }

    @Override
    public void onUnregister(Player player) {
        ThornSMP.getInstance().getBossBar().removePlayer(player);
    }
}
