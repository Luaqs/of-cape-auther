package dev.luaq.capechange.cmd;

import com.mojang.authlib.exceptions.AuthenticationException;
import dev.luaq.capechange.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.awt.*;
import java.math.BigInteger;
import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChangeCapeCmd implements ICommand {
    @Override
    public String getCommandName() {
        return "changecape";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return null;
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("optifinecape", "capechanger", "capechange", "cc");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        Minecraft mc = Minecraft.getMinecraft();

        // gather session details
        String ign = mc.getSession().getUsername();
        String uuid = mc.getSession().getPlayerID().replace("-", "");
        String token = mc.getSession().getToken();

        // generate a random ID for the server
        Random random = new Random();
        Random random1 = new Random(System.identityHashCode(new Object()));

        BigInteger bigInt = new BigInteger(128, random);
        BigInteger bigInt2 = new BigInteger(128, random1);

        BigInteger serverBi = bigInt.xor(bigInt2);
        String serverId = serverBi.toString();

        try {
            // authenticate with the server
            mc.getSessionService().joinServer(mc.getSession().getProfile(), token, serverId);

            // open the URL passing the UUID, IGN, and server ID that was authed to in the params
            // optifine will check the success of connecting to the server with that ID
            boolean isSuccess = Utils.openURL(String.format("https://optifine.net/capeChange?u=%s&n=%s&s=%s", uuid, ign, serverId));

            if (!isSuccess) {
                Utils.sendMessage("&cFailed to open the OptiFine link.");
                return;
            }
        } catch (AuthenticationException e) {
            Utils.sendMessage("&cFailed to authenticate with Mojang. Invalid session (most likely).");
            return;
        }

        Utils.sendMessage("&aSuccess! &eThanks for using this mod.");
        Utils.sendMessage("&eAdding in-game reloading of cape soon.\n&d- luaq.");
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0;
    }
}
