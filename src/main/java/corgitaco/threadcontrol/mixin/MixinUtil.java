package corgitaco.threadcontrol.mixin;

import corgitaco.threadcontrol.ThreadControl;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Util.class)
public class MixinUtil {

    @ModifyConstant(method = "makeExecutor", constant = @Constant(intValue = 7), remap = false)
    private static int maxThreads(int arg0) {
        return ThreadControl.MAX_THREAD_COUNT;
    }
}
