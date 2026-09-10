package net.jaeger.oldworldfantasy.client.keyMapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinds {

    public static final String KEY_CATEGORY = "key.categories.oldworldfantasy";

    public static final KeyMapping TOGGLE_FLIGHT = new KeyMapping("key.oldworldfantasy.toggle_flight",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, "key.categories.oldworldfantasy");
}

