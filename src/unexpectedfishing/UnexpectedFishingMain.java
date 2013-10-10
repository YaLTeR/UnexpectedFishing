package unexpectedfishing;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class UnexpectedFishingMain extends JavaPlugin {
    
    private static Logger logger = Logger.getLogger("UnexpectedFishing");
    protected UnexpectedFishingConfig config;
    protected boolean mcpc;
    protected boolean anvilEnabled = false;
    
    @Override
    public void onEnable() {
        config = new UnexpectedFishingConfig("plugins/UnexpectedFishing/config.yml");
        
        mcpc = UnexpectedFishingReflection.doesClassExist("net.minecraftforge.common.ForgeHooks");
        
        if ( UnexpectedFishingReflection.reflectCraftFallingSand() ) {
            if (mcpc){
                log("MCPC+ detected!");
                anvilEnabled = UnexpectedFishingReflection.reflectForgeEntityFallingSand();
            } else {
                anvilEnabled = UnexpectedFishingReflection.reflectEntityFallingBlock();
            }
        }
        
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new UnexpectedFishingListener(this), this);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length <= 0) {
            return false;
        }
        
        if (args[0].equalsIgnoreCase("debug-mode")) {
            if (args.length == 2) {
                boolean newDebugMode = Boolean.parseBoolean(args[1]);
                config.debugMode = newDebugMode;
                config.saveConfig();
                
                if (newDebugMode) {
                    sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": Debug mode is now " + ChatColor.GOLD + "enabled" + ChatColor.WHITE + ".");
                } else {
                    sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": Debug mode is now " + ChatColor.GOLD + "disabled" + ChatColor.WHITE + ".");
                }
            } else {
                if (config.debugMode) {
                    sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": Debug mode is " + ChatColor.GOLD + "enabled" + ChatColor.WHITE + ".");
                } else {
                    sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": Debug mode is " + ChatColor.GOLD + "disabled" + ChatColor.WHITE + ".");
                }
            }
        } else if (args[0].equalsIgnoreCase("debug-action")) {
            if (args.length == 2) {
                int newActionId = Integer.parseInt(args[1]);
                config.actionId = newActionId;
                config.saveConfig();
                
                sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": The debug action ID is now " + ChatColor.GOLD + newActionId + ChatColor.WHITE + ".");
            } else {
                sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": The debug action ID is " + ChatColor.GOLD + config.actionId + ChatColor.WHITE + ".");
            }
        } else if (args[0].equalsIgnoreCase("reload-config")) {
            config.reloadConfig();
            
            sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": The configuration was reloaded.");
        } else {
            sender.sendMessage(ChatColor.YELLOW + "UnexpectedFishing" + ChatColor.WHITE + ": Incorrect command.");
        }
        
        return true;
    }
    
    public static void log(String text) {
        logger.info("[UnexpectedFishing] " + text);
    }
    
    public void scheduleSyncDelayedTask(Runnable task, long delay) {
        getServer().getScheduler().scheduleSyncDelayedTask(this, task, delay);
    }
    
}
