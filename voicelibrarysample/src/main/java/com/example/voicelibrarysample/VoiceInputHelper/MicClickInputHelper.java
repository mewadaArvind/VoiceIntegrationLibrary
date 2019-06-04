package com.example.voicelibrarysample.VoiceInputHelper;

import android.app.Activity;
import android.view.View;
import android.widget.Button;


public class MicClickInputHelper {

    private Button startSpeakingBtn;
    private VoiceInputHelper voiceInputHelper;
    private Activity activity;

    public MicClickInputHelper(Activity activity, Button startSpeakingBtn
            , final VoiceInputHelper voiceInputHelper){
        this.voiceInputHelper = voiceInputHelper;
        this.startSpeakingBtn = startSpeakingBtn;
        this.activity = activity;
    }

    public void init(){
        startSpeakingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voiceInputHelper.startListening();
                if(voiceInputHelper.isCurrentlySpeaking() || voiceInputHelper.isCurrentlyListening()){
                    voiceInputHelper.stopSpeaking();
                    voiceInputHelper.stopListening();
                }
            }
        });
    }
}
