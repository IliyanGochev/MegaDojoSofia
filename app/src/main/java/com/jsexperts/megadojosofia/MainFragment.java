package com.jsexperts.megadojosofia;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;

/**
 * Created by Iliyan on 4.1.2018 г..
 */

public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getName();

    OnLearnButtonPressed mCallback;

    public interface OnLearnButtonPressed {
        public void onButtonPressed();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState){

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        VideoView vv = (VideoView) view.findViewById(R.id.videoView);
        Button button = (Button) view.findViewById(R.id.codeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallback.onButtonPressed();
            }
        });
        String filepath = "android.resource://" + BuildConfig.APPLICATION_ID + "/" + R.raw.background;
        vv.setVideoPath(filepath);
        Log.e(TAG, filepath);
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
                mediaPlayer.start();
            }
        });
        vv.start();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnLearnButtonPressed) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}
