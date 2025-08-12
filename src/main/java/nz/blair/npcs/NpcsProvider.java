package nz.blair.npcs;

import nz.blair.npcs.listeners.PacketInboundListener;
import nz.blair.npcs.listeners.PlayerListener;
import nz.blair.npcs.npcs.Npc;
import nz.blair.npcs.npcs.NpcFactory;
import nz.blair.npcs.npcs.NpcManager;
import nz.blair.npcs.packets.PacketHandlerInjector;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NpcsProvider {
    private JavaPlugin plugin;

    private NpcsApi npcsApi;
    private NpcManager npcManager;

    public NpcsProvider(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private void register() {
        npcManager = new NpcManager();
        NpcFactory npcFactory = new NpcFactory(plugin);
        npcsApi = new NpcsApi(npcManager, npcFactory);

        PacketInboundListener packetInboundListener = new PacketInboundListener(npcManager);
        PacketHandlerInjector packetHandlerInjector = new PacketHandlerInjector(packetInboundListener);

        PluginManager pluginManager = plugin.getServer().getPluginManager();
        pluginManager.registerEvents(new PlayerListener(packetHandlerInjector, npcManager, plugin), plugin);
    }

    public void disable() {
        npcManager.getNpcs().forEach(Npc::removeConnections);
    }

    public NpcsApi getApi() {
        return npcsApi;
    }
}
