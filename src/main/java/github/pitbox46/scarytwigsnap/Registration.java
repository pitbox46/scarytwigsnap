package github.pitbox46.scarytwigsnap;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "eventz");

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        SOUND_EVENTS.register(modEventBus);
    }

    public static final RegistryObject<SoundEvent> BONE_BREAK = createAmbientSound("bone_break");
    public static final RegistryObject<SoundEvent> TWIG_SNAP = createAmbientSound("twig_snap");
    public static final RegistryObject<SoundEvent> BRANCH_BREAK = createAmbientSound("branch_break");

    private static RegistryObject<SoundEvent> createAmbientSound(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation("scarytwigsnap:ambient." + name)));
    }
}
