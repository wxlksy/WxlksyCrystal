package com.example.wxlksy.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.example.wxlksy.Wxlksy;
import com.example.wxlksy.command.EnableOptimizerCommand;

import static com.example.wxlksy.Wxlksy.limitPackets;
import static com.example.wxlksy.Wxlksy.mc;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin
{

    @Inject(at = @At("HEAD"), method = "doItemUse", cancellable = true)
    private void onDoItemUse(CallbackInfo ci)
    {
        if (EnableOptimizerCommand.fastCrystal)
        {
            ItemStack mainHand = mc.player.getMainHandStack();
            if (mainHand.isOf(Items.END_CRYSTAL))
                if (Wxlksy.hitCount != limitPackets())
                    ci.cancel();
        }
    }
}