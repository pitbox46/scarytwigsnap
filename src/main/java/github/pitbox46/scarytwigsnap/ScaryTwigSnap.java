package github.pitbox46.scarytwigsnap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod("scarytwigsnap")
public class ScaryTwigSnap {
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final Random RANDOM = new Random();

    public ScaryTwigSnap() {
        MinecraftForge.EVENT_BUS.register(this);
        Registration.init();
    }

    @SubscribeEvent
    public void onClientPlayerTick(TickEvent.ClientTickEvent tick) {
        if(tick.phase == TickEvent.Phase.START && Minecraft.getInstance().player != null) {
            LocalPlayer localPlayer = Minecraft.getInstance().player;
            boolean flag1 = localPlayer.level.getBiome(localPlayer.blockPosition()).getBiomeCategory() == Biome.BiomeCategory.FOREST;
            if(flag1 && localPlayer.blockPosition().getY() > 50) {
                int randInt = RANDOM.nextInt(100);
                if(randInt < 3) {
                    SoundManager soundManager = Minecraft.getInstance().getSoundManager();
                    if(randInt < 1) {
                        soundManager.play(new ScaryAmbientSound(localPlayer.blockPosition(), Registration.TWIG_SNAP.get()));
                    }
                    else if (randInt < 2) {
                        soundManager.play(new ScaryAmbientSound(localPlayer.blockPosition(), Registration.BONE_BREAK.get()));
                    }
                    else {
                        soundManager.play(new ScaryAmbientSound(localPlayer.blockPosition(), Registration.BRANCH_BREAK.get()));
                    }
                }
            }
        }
    }
}
