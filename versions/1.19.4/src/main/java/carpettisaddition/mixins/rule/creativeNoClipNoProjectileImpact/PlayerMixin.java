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
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    //#if MC >= 26.1
    //$$ @Override
    //$$ public boolean canBeHitByProjectile() {
    //$$     return super.canBeHitByProjectile() && !(CreativeNoClipHelper.isNoClipPlayer(this) && CarpetTISAdditionSettings.creativeNoClipNoProjectileImpact) ;
    //$$ }
    //#else
    @Inject(method = "canBeHitByProjectile", at=@At("RETURN"), cancellable = true)
    public void checkCreativeNoClipNoProjectileImpact(CallbackInfoReturnable<Boolean> cir) {
        if (CreativeNoClipHelper.isNoClipPlayer(this) && CarpetTISAdditionSettings.creativeNoClipNoProjectileImpact) {
            cir.setReturnValue(false);
        }
    }
    //#endif
}
