package com.example.musicservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyService extends Service {
    //creating a mediaplayer object
    private MediaPlayer player;
    public static final String ACTION_START_MUSIC = "com.example.musicservice.action_start_music";
    public static final String ACTION_STOP_MUSIC = "com.example.musicservice.action_stop";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getAction() != null){
            //getting systems default ringtone
            player = MediaPlayer.create(this,
                    Settings.System.DEFAULT_RINGTONE_URI);
            //setting loop play to true
            //this will make the ringtone continuously playing
            player.setLooping(true);
            switch (intent.getAction()){
                case ACTION_START_MUSIC :
                    if(!player.isPlaying()){
                        player.start();
                    }
                    break;
                case ACTION_STOP_MUSIC :
                        player.stop();
                    break;
                default: break;
            }
        }
        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        player.release();
    }
}
