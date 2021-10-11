package github.pitbox46.scarytwigsnap;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Registration {
    static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, "scarytwigsnap");

    public static void init() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        SOUND_EVENTS.register(modEventBus);
    }

    public static final RegistryObject<SoundEvent> SCARY_FOREST = createAmbientSound("ambient.scary.forest");

    private static RegistryObject<SoundEvent> createAmbientSound(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation("scarytwigsnap", name)));
    }
}
