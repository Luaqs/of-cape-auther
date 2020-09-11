package dev.luaq.capechange;

import dev.luaq.capechange.cmd.ChangeCapeCmd;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = CapeChanger.MODID, version = CapeChanger.VERSION)
public class CapeChanger {
    public static final String MODID = "capechanger";
    public static final String VERSION = "1.0";

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // register a command to open the changer
        ClientCommandHandler.instance.registerCommand(new ChangeCapeCmd());
    }
}
