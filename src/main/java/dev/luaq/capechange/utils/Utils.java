package dev.luaq.capechange.utils;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

import java.awt.*;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final Pattern COLOR_REGEX = Pattern.compile("&([0-9A-FK-OR])", Pattern.CASE_INSENSITIVE);

    public static void sendMessage(String message) {
        String toPrint = message;

        Matcher matcher = COLOR_REGEX.matcher(message);
        while (matcher.find()) {
            // the color char
            char match = matcher.group(1).charAt(0);
            toPrint = toPrint.replace(matcher.group(0), ChatFormatting.getByChar(match).toString());
        }

        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage(new ChatComponentText(toPrint));
    }

    public static boolean openURL(String url) {
        Desktop desktop = Desktop.getDesktop();
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI(url));
                return true;
            } catch (Exception ignored) {
            }
        }

        return false;
    }
}
