package net.jaeger.oldworldfantasy.client.keyMapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.apache.logging.log4j.util.Lazy;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {

    public static final String OLD_WORLD_FANTASY = "key.categories.oldworldfantasy";

    public static final KeyMapping KEY_MAPPING_TOGGLE_FLIGHT = new KeyMapping("key.oldworldfantasy.toggle_flight",
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_Z, OLD_WORLD_FANTASY);

    public static final Lazy<KeyMapping> PRESS_TOGGLE_FLIGHT = Lazy.lazy(() -> KEY_MAPPING_TOGGLE_FLIGHT);

    public static void init(){

    }
}

