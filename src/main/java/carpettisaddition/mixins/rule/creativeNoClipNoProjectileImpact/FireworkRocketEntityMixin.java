/*
 * This file is part of the Carpet TIS Addition project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  Fallen_Breath and contributors
 *
 * Carpet TIS Addition is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Carpet TIS Addition is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Carpet TIS Addition.  If not, see <https://www.gnu.org/licenses/>.
 */

package carpettisaddition.mixins.rule.creativeNoClipNoProjectileImpact;
import org.spongepowered.asm.mixin.Mixin;

//#if MC >= 1.16.5
//$$ import carpettisaddition.utils.compat.DummyClass;
//$$ @Mixin(DummyClass.class)
//#else
import carpettisaddition.CarpetTISAdditionSettings;
import carpettisaddition.helpers.carpet.tweaks.rule.creativeNoClip.CreativeNoClipHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireworkRocketEntity.class)
//#endif
public class FireworkRocketEntityMixin {
    //#if MC < 1.16.5
    @Inject(method = "method_18073", at = @At("HEAD"), cancellable = true)
    private static void checkCreativeNoClipNoProjectileImpact(Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (CreativeNoClipHelper.isNoClipPlayer(entity) && CarpetTISAdditionSettings.creativeNoClipNoProjectileImpact) {
            cir.setReturnValue(false);
        }
    }
    //#endif
}
