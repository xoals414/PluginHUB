package org.gumang.sdplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.gumang.sdplugin.Command.mainCommand;

public final class sdPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginCommand("sd").setExecutor(new mainCommand());
    }
    
// 그냥 진짜 서버 상태 확인하는거임 :: 테스트 안해봤음 되지 않을까

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
