package com.legacy.aether.mixin.client;

import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.legacy.aether.blocks.BlocksAether;
import com.legacy.aether.client.rendering.color.AetherColor;

@Mixin(ItemColors.class)
public class MixinItemColors 
{

    @Inject(method = "init", at = @At("RETURN"), cancellable = true)
	private static void colorInit(BlockColors blockColors, CallbackInfoReturnable<ItemColors> info)
	{
    	ItemColors colors = info.getReturnValue();

    	colors.func_199877_a(new AetherColor(), BlocksAether.blue_aercloud, BlocksAether.golden_aercloud);

    	info.setReturnValue(colors);
	}

}