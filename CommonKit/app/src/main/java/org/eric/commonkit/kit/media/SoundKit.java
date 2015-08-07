package org.eric.commonkit.kit.media;

import android.media.MediaPlayer;

import org.eric.commonkit.CKApplication;


public class SoundKit {

    public static void playSound(int resId, float volume) {
        MediaPlayer player = MediaPlayer.create(CKApplication.context, resId);
        player.setVolume(volume, volume);
        player.start();

    }

}
