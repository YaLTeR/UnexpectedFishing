/*
 *  Copyright 2013 Ivan Molodetskikh.
 *  
 *  This file is part of UnexpectedFishing.
 *
 *  UnexpectedFishing is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  UnexpectedFishing is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with UnexpectedFishing.  If not, see <http://www.gnu.org/licenses/>.
 */
package unexpectedfishing;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;

public class UnexpectedFishingConfig {
    
    public boolean enabled;
    
    public boolean debugMode;
    public int actionId;
    
    public int chance;
    public int chanceZombies;
    public int chanceKittyCannon;
    public int chanceSquid;
    public int chancePlayerthrow;
    public int chanceTNT;
    public int chanceTrash;
    public int chanceGeyser;
    public int chanceAnvil;
    
    private String fileName;
    private YamlConfiguration config;
    
    public UnexpectedFishingConfig(String fileName) {
        this.fileName = fileName;
        config = YamlConfiguration.loadConfiguration(new File(this.fileName));
        
        readConfig();
        saveConfig();
    }
    
    public void reloadConfig() {
        if (fileName != null) {
            config = YamlConfiguration.loadConfiguration(new File(this.fileName));
            
            readConfig();
            saveConfig();
        }
    }
    
    public void readConfig() {
        enabled = config.getBoolean("enabled", true);
        
        debugMode = config.getBoolean("debug.debug-mode", false);
        actionId = config.getInt("debug.action-id", 0);
        
        chance = config.getInt("chance", 10);
        chanceZombies = config.getInt("chances.zombies", 20);
        chanceKittyCannon = config.getInt("chances.kittycannon", 10);
        chanceSquid = config.getInt("chances.squid", 19);
        chancePlayerthrow = config.getInt("chances.playerthrow", 15);
        chanceTNT = config.getInt("chances.tnt", 10);
        chanceTrash = config.getInt("chances.trash", 20);
        chanceGeyser = config.getInt("chances.geyser", 1);
        chanceAnvil = config.getInt("chances.anvil", 5);
    }
    
    public void saveConfig() {
        config.set("enabled", enabled);
        config.set("debug.debug-mode", debugMode);
        config.set("debug.action-id", actionId);
        
        config.set("chance", chance);
        config.set("chances.zombies", chanceZombies);
        config.set("chances.kittycannon", chanceKittyCannon);
        config.set("chances.squid", chanceSquid);
        config.set("chances.playerthrow", chancePlayerthrow);
        config.set("chances.tnt", chanceTNT);
        config.set("chances.trash", chanceTrash);
        config.set("chances.geyser", chanceGeyser);
        config.set("chances.anvil", chanceAnvil);
        
        try {
            config.save(fileName);
        } catch (IOException e) {
            UnexpectedFishingMain.log("Failed to save the configuration file!");
            e.printStackTrace();
        }
    }
    
}
