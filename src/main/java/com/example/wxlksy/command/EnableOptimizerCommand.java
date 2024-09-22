package com.example.wxlksy.command;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;



public class EnableOptimizerCommand
{

    public static boolean Crystal = true;

    public void initializeToggleCommands()
    {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(ClientCommandManager.literal("wxlksycrystal").executes(context -> {
                    if (Crystal)
                    {
                        Crystal = false;
                        displayMessage("disabled!");
                    }

                    else if (!Crystal)
                    {
                        Crystal = true;
                        displayMessage("enabled");
                    }
                    return 1;
                }
                )));
    }


    public static void displayMessage(String message)
    {
        if (!inGame()) return;

        MinecraftClient client = MinecraftClient.getInstance();
        ChatHud chatHud = client.inGameHud.getChatHud();

        chatHud.addMessage(Text.of(message));
    }


    public static Boolean inGame()
    {
        MinecraftClient client = MinecraftClient.getInstance();
        return client.player != null && client.getNetworkHandler() != null;
    }
}