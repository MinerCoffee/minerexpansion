package me.minercoffee.minerexpansion.silktouchspawners;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SpawnerBreakEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    Player breaker;
    Block spawner;

    public SpawnerBreakEvent(Player breaker, Block spawner){
        this.breaker = breaker;
        this.spawner = spawner;
    }

    public Block getSpawner() {
        return spawner;
    }

    public Player getBreaker() {
        return breaker;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }
}
