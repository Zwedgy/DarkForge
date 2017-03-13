package uk.co.hexeption.mcwrapper.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.Timer;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import uk.co.hexeption.darkforge.DarkForge;
import uk.co.hexeption.darkforge.event.events.other.EventKeyboard;
import uk.co.hexeption.mcwrapper.MCWrapper;
import uk.co.hexeption.mcwrapper.base.MinecraftClient;
import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;

/**
 * Created by Hexeption on 12/03/2017.
 */
@Mixin(net.minecraft.client.Minecraft.class)
public abstract class MixinMinecraft implements MinecraftClient {

    @Shadow
    public PlayerControllerMP playerController;

    @Shadow
    @Final
    private Timer timer;

    @Shadow
    private static int debugFPS;

    @Inject(method = "run()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;init()V", shift = At.Shift.AFTER))
    public void init(CallbackInfo callbackInfo) {

        MCWrapper.setMinecraft((Minecraft)(Object)this);
        DarkForge.INSTANCE.start();
    }

    @Inject(method = "runTickKeyboard()V", at = @At(value = "INVOKE_ASSIGN", target = "Lorg/lwjgl/input/Keyboard;getEventKeyState()Z"))
    public void onKeyPressed(CallbackInfo callbackInfo) {

        if (Keyboard.getEventKeyState()) {
            EventKeyboard eventKeyboard = new EventKeyboard(Keyboard.getEventKey());
            eventKeyboard.call();
        }
    }

    @Override
    public Controller getController() {

        return ((Controller) playerController);
    }

    @Override
    public float getTimerSpeed() {

        return timer.timerSpeed;
    }

    @Override
    public void setTimerSpeed(float timerSpeed) {

        timer.timerSpeed = timerSpeed;
    }

    @Override
    public int getFPS() {

        return debugFPS;
    }
}