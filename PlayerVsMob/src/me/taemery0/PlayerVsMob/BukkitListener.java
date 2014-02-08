package me.taemery0.PlayerVsMob;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
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
	/* When Player Dies:
	 * Exit The Game
	 * Clear Item Drops
	 * Brand Player Death Message
	 */
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		if (Vars.isInGame.containsKey(event.getEntity())) {
			if (Vars.isInGame.get(event.getEntity())) {
				game.exit(event.getEntity());
				event.getDrops().clear();
				event.setDroppedExp(0);
				event.setDeathMessage(ChatColor.BLUE
						+ "[PVM] "
						+ ChatColor.RED
						+ event.getEntity().getName()
						+ " Died At The Hands Of "
						+ WordUtils.capitalizeFully(getKiller(event).getType().name()));
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
	/* End Player Death Handler */
	
	/* When Block Placed Or Breaked:
	 * Check If Player Has Permission: PVM.editworld
	 * Check If Player Is OP
	 * If Both False: Cancel Place or Break
	 */
	@EventHandler
	public void blockProtection(BlockBreakEvent event) {
		if (!event.getPlayer().hasPermission("PVM.editworld")
				&& !event.getPlayer().isOp()) {
			event.setCancelled(true);
		}
	}
	@EventHandler
	public void placeBlockProtection(BlockPlaceEvent event){
		if (!event.getPlayer().hasPermission("PVM.editworld")
				&& !event.getPlayer().isOp()) {
			event.setCancelled(true);
		}
	}
	/* End Block Place And Break Handler */
	
	/* When Player Quits:
	 * Exit Their Game
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		game.exit(event.getPlayer());
	}
	/* End Player Quit Handler */

	/* When Player Joins:
	 * Teleport To Lobby If Config Set True
	 * Setup Their Stat Scoreboard
	 */
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		event.getPlayer().sendMessage("Welcome to the PVM Server!");
		if (PlayerVsMob.plugin.getConfig().getString("lobby.teleport-on-join") == "true") {
			event.getPlayer().teleport(Vars.lobby);
		}
		scoreboard.statScoreboard(event.getPlayer());
	}
	/* End Player Join Handler */

	/* When Player Takes Damage:
	 * Check If Fall Damage
	 * Cancel If True
	 */
	@EventHandler
	public void noFall(EntityDamageEvent event) {
		if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
			event.setCancelled(true);
		}
	}
	/* End Entity Damage Handler */

	/* When Player Picks Up Item:
	 * Cancel
	 */
	@EventHandler
	public void onItemPickup(PlayerPickupItemEvent event) {
		event.setCancelled(true);
	}
	/* End Player Pickup Item Handler */

	/* When Player Drops Item:
	 * Cancel
	 */
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent event) {
		event.setCancelled(true);
	}
	/* End Player Drop Item Handler */
	
	/* When Entity Dies
	 * Clear Drops
	 * Clear EXP
	 * Give Points To Killer Player
	 */
	@EventHandler
	public void onMobKill(EntityDeathEvent event) {
		event.getDrops().clear();
		event.setDroppedExp(0);
		if(event.getEntity().getKiller() instanceof Player){
			scoreboard.mobKill(event.getEntity().getKiller());
		}
	}

	/* When Entity Explodes
	 * Cancel
	 */
	@EventHandler
	public void onCreeperBlockBreak(EntityExplodeEvent event) {
		event.setCancelled(true);
	}
	/* End Entity Explode Event */
}
