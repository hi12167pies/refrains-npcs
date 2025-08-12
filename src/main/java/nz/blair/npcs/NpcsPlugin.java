package nz.blair.npcs;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main plugin class.
 * This is the entry point of the plugin.
 * This class is responsible for creating the API singleton and registering events.
 */
@SuppressWarnings("unused") // This class is used by other plugins
public final class NpcsPlugin extends JavaPlugin {
    private static NpcsProvider provider;

    @Override
    public void onEnable() {
        provider = new NpcsProvider(this);
    }

    @Override
    public void onDisable() {
        provider.disable();
    }

    /**
     * Get the API singleton.
     *
     * @deprecated Create your own {@link NpcsProvider} and get api from there
     * @return The API singleton
     */
    @Deprecated
    public static NpcsApi getApi() {
        return provider.getApi();
    }
}
