package github.pitbox46.scarytwigsnap;

import it.unimi.dsi.fastutil.longs.Long2ByteArrayMap;
import it.unimi.dsi.fastutil.longs.Long2IntArrayMap;
import it.unimi.dsi.fastutil.longs.Long2LongArrayMap;
import it.unimi.dsi.fastutil.longs.Long2LongSortedMaps;
import it.unimi.dsi.fastutil.objects.Object2FloatArrayMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.sounds.SoundManager;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Mod("scarytwigsnap")
public class ScaryTwigSnap {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final Random RANDOM = new Random();
    private static long tick = 0;
    private static final Long2ByteArrayMap tickMap = new Long2ByteArrayMap();
    private static final Object2FloatMap<SoundSource> soundMap = new Object2FloatOpenHashMap<>();

    public ScaryTwigSnap() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(ClientEvents.class);
        Registration.init();
    }

    public static class ClientEvents {
        @SubscribeEvent
        public static void onClientPlayerTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.START && Minecraft.getInstance().player != null && !Minecraft.getInstance().isPaused()) {
                LocalPlayer localPlayer = Minecraft.getInstance().player;
                boolean flag1 = localPlayer.level.getBiome(localPlayer.blockPosition()).getBiomeCategory() == Biome.BiomeCategory.FOREST;
                if (flag1 && localPlayer.blockPosition().getY() > 50) {
                    int randInt = RANDOM.nextInt(100);
                    Options options = Minecraft.getInstance().options;
                    SoundManager soundManager = Minecraft.getInstance().getSoundManager();
                    if (tickMap.get(tick) == 1) {
                        for (SoundSource source : SoundSource.values()) {
                            if (!(source == SoundSource.AMBIENT || source == SoundSource.HOSTILE || source == SoundSource.MASTER)) {
                                soundMap.put(source, options.getSoundSourceVolume(source));
                                options.setSoundCategoryVolume(source, 0.0F);
                            }
                        }
                    }
                    if (tickMap.get(tick) == 2) {
                        soundManager.play(new ScaryAmbientSound(localPlayer.blockPosition(), Registration.SCARY_FOREST.get()));
                    }
                    if (tickMap.get(tick) == 3) {
                        for (SoundSource source : SoundSource.values()) {
                            if (!(source == SoundSource.AMBIENT || source == SoundSource.HOSTILE || source == SoundSource.MASTER)) {
                                options.setSoundCategoryVolume(source, soundMap.apply(source));
                                soundMap.removeFloat(source);
                            }
                        }
                    }
                    if (randInt == 0 && tickMap.isEmpty()) {
                        //Quites sounds
                        tickMap.put(tick + 20, (byte) 1);
                        //Play scary sound
                        tickMap.put(tick + 40, (byte) 2);
                        //Loundens sounds
                        tickMap.put(tick + 60, (byte) 3);
                    }
                }
                tickMap.remove(tick);
                tick++;
            }
        }
    }
}
