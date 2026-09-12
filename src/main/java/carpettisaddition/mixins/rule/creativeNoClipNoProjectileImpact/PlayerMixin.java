package carpettisaddition.mixins.rule.creativeNoClipNoProjectileImpact;

import carpettisaddition.utils.compat.DummyClass;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DummyClass.class)
public class PlayerMixin {
    // impl in 1.19.4
    // Note: You could make this work in earlier versions, but the functions are rearranged a lot in earlier versions.
}
