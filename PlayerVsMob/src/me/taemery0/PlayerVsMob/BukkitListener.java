package me.taemery0.PlayerVsMob;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitListener implements Listener {

	// public static Bukkit plugin;
	//
	// public BukkitListener(Bukkit instance) {
	// plugin = instance;
	// }

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (Vars.isInGame.containsKey(event.getEntity())) {
			if (Vars.isInGame.get(event.getEntity())) {
				scoreboard.resetPlayerScore(event.getEntity());
				game.exit(event.getEntity());
				event.getDrops().clear();
				event.setDroppedExp(0);
				
				event.setDeathMessage(ChatColor.BLUE
						+ "[PVM] "
						+ ChatColor.RED
						+ event.getEntity().getName()
						+ " Died At The Hands Of "
						+ getKiller(event).getType().getName() );
			}
		}
	}
	
	private static Entity getKiller(EntityDeathEvent event) {
		EntityDamageEvent entityDamageEvent = event.getEntity().getLastDamageCause();
		if ((entityDamageEvent != null) && !entityDamageEvent.isCancelled() && (entityDamageEvent instanceof EntityDamageByEntityEvent)) {
			Entity damager = ((EntityDamageByEntityEvent) entityDamageEvent).getDamager();
	 
			if (damager instanceof Projectile) {
				@SuppressWarnings("deprecation")
				LivingEntity shooter = ((Projectile) damager).getShooter();
				if (shooter != null) return shooter;
			}
	 
			return damager;
		}
	 
		return null;
	}

	// Start Block Protection
	@EventHandler
	public void blockProtection(BlockBreakEvent event) {
		if (!event.getPlayer().hasPermission("PVM.editworld")
				&& !event.getPlayer().isOp()) {
			event.setCancelled(true);
		}
	}

	// End Block Protection
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		game.exit(event.getPlayer());
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage("Welcome to the PVM Server!");
		if (PlayerVsMob.plugin.getConfig().getString("lobby.teleport-on-join") != "true") {
			//
		} else {
			event.getPlayer().teleport(Vars.lobby);
		}
		scoreboard.statScoreboard(event.getPlayer());
	}

	@EventHandler
	public void noFall(EntityDamageEvent event) {
		if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}

	@EventHandler
	public void onMobDrop(EntityDeathEvent event) {
		event.getDrops().clear();
		event.setDroppedExp(0);
		// scoreboard.mobKill(event.getEntity().getKiller());
	}

	@EventHandler
	public void onCreeperBlockBreak(EntityExplodeEvent event) {
		event.setCancelled(true);
	}
}
