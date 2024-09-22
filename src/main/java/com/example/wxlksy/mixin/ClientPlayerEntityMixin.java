package com.example.wxlksy.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.example.wxlksy.Wxlksy;
import com.example.wxlksy.command.EnableOptimizerCommand;

import static com.example.wxlksy.Wxlksy.mc;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin
{

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/AbstractClientPlayerEntity;tick()V", ordinal = 0), method = "tick()V")
    private void useOwnTicks(CallbackInfo ci)
    {
        if (EnableOptimizerCommand.fastCrystal)
        {
            Wxlksy.useOwnTicks();
        }
    }
}