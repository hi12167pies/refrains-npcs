# Purpose of this fork
Improve upon the base plugin, as well as making this plugin shadable for easy api use.

This fork still allows use of old method as a plugin but is not recommended and will now be designed to be shaded.

# NPCs

This is a plugin that enables you to create lightweight NPCs.

## Usage

1. Publish the plugin to your local maven repo using `gradle publish`
2. Install the plugin to your Spigot server.
3. Add the plugin as a dependency to your Spigot plugin.

## Goals

- The aim of this plugin is to provide a lightweght NPC solution.
- This plugin will NOT support AI logic, it is up to you to implement.
- In the future, newer versions may be supported.

## Example

```java
// Get the NPC API
public class YourPlugin extends JavaPlugin {
    private NpcsProvider npcsProvider = new NpcsProvider(plugin);
    
    public void onEnable() {
        npcsProvider = new NpcsProvider(this);
        
        NpcsApi npcsApi = npcsProvider.getApi();

        // Create a global NPC
        Npc npc = npcsApi.createNpc("Test", location, true);

        // Run an asynchronous task to prevent blocking the main thread 
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            // Get a skin from the Mojang API
            Skin skin = SkinUtil.getSkin("Notch");

            // Set the NPC's skin
            npc.setSkin(skin);
        });
    }
    
    public void onDisable() {
        npcsProvider.disable();
    }
}
```
