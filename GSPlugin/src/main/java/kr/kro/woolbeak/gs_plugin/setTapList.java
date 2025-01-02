package kr.kro.woolbeak.gs_plugin;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;
import java.util.Map;

public class setTapList {

    public static Map<UUID, String> TAPLIST = new HashMap<>();

    public static void setTaplistName(Player p, String str) {

        p.setPlayerListName(str);
        TAPLIST.put(p.getUniqueId(), str);


    }
}
