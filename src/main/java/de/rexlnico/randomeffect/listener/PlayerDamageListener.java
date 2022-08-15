package de.rexlnico.randomeffect.listener;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.concurrent.atomic.AtomicInteger;

public class PlayerDamageListener implements Listener {

    @Getter
    private static AtomicInteger counter = new AtomicInteger(0);


    @EventHandler
    public void on(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player eventPlayer) {
            if (event.getCause() != EntityDamageEvent.DamageCause.CUSTOM) {
                final double finalDamage = event.getCause() != EntityDamageEvent.DamageCause.MAGIC ? event.getFinalDamage() :
                        Math.min(event.getFinalDamage(), 19.5);
                event.setDamage(finalDamage);
                Bukkit.getOnlinePlayers().stream().filter(player -> !player.getName().equals(eventPlayer.getName()))
                        .forEach(player -> player.damage(finalDamage));
            }
        }
    }

    @EventHandler
    public void on(EntityRegainHealthEvent event) {
        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            if (counter.get() % Bukkit.getOnlinePlayers().size() == 0) {
                Bukkit.getOnlinePlayers().forEach(player -> player.setHealth(Math.min(Math.max(0.0, player.getHealth() + event.getAmount()), 20.0)));
            }
            counter.addAndGet(1);
        }
    }

    @EventHandler
    public void on(PlayerDeathEvent event) {
        Bukkit.getOnlinePlayers()
                .stream().filter(player -> !player.getName().equals(event.getPlayer().getName()))
                .filter(player -> player.getHealth() > 0.0)
                .forEach(player -> player.setHealth(0));
    }

}
