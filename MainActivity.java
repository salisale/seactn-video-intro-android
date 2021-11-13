package moru.seactn.videointro;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import android.content.pm.ActivityInfo;

public class MainActivity extends AppCompatActivity {

    //create class reference
    VideoView vid;
    int stopPosition;

    @Override
    protected void onStart() {
        super.onStart();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            getSupportActionBar().hide(); //  hide app bar
        } catch (NullPointerException e){}

        vid = (VideoView)findViewById(R.id.videoView);

        playVideo(vid);
    }

    public void playVideo(View v) {

        MediaController m = new MediaController(this);
        vid.setMediaController(m);

        String path = "android.resource://moru.seactn.videointro/"+R.raw.intro_seactn_video;
        Uri u = Uri.parse(path);

        vid.setVideoURI(u);
        vid.start();

    }

    @Override
    public void onPause() {
        super.onPause();
        stopPosition = vid.getCurrentPosition(); //stopPosition is an int
        vid.pause();
    }
    @Override
    public void onResume() {
        super.onResume();
        vid.seekTo(stopPosition);
        vid.start(); //Or use resume() if it doesn't work
    }
}
