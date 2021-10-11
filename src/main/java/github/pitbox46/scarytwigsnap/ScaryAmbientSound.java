package github.pitbox46.scarytwigsnap;

import net.minecraft.client.resources.sounds.AbstractSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class ScaryAmbientSound extends AbstractSoundInstance {
    protected ScaryAmbientSound(BlockPos playerPos, SoundEvent soundEvent) {
        super(soundEvent, SoundSource.AMBIENT);
        this.x = playerPos.getX() + ScaryTwigSnap.RANDOM.nextInt(100) - 50;
        this.y = playerPos.getY();
        this.z = playerPos.getZ() + ScaryTwigSnap.RANDOM.nextInt(100) - 50;
        this.volume = 0.2F;
        this.relative = false;
    }
}
