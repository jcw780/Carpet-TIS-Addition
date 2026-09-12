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

import carpettisaddition.CarpetTISAdditionSettings;
import carpettisaddition.helpers.carpet.tweaks.rule.creativeNoClip.CreativeNoClipHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Projectile.class)
public abstract class ProjectileMixin {
    //#if MC < 1.19.4
    @Inject(method = "canHitEntity", at= @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/Projectile;getOwner()Lnet/minecraft/world/entity/Entity;"), cancellable = true)
    private void checkCreativeNoClip(Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (CreativeNoClipHelper.isNoClipPlayer(target) && CarpetTISAdditionSettings.creativeNoClipNoProjectileImpact) {
            cir.setReturnValue(false);
        }
    }
    //#endif
}
