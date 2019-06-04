package com.example.voicelibrarysample;

import android.content.Context;

import com.example.voicelibrarysample.VoiceHelper.VoiceHelper;



/**
* Created by Mewada Arvind on 1 June 2019
 * Voice Master class
 * handle voice integration
 * and handle network process here
 * */
public class VoiceMasterClass {


    private stateType currentStateType;
    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private  VoiceHelper voiceHelper;
    private enum stateType {
        PROCESSING, LISTENING, SPEAKING, REST; //ENUM
    }
    /**
     * voice master class initialisation
     * @Param Context
     * @Param MasterInterface
     * */
    public  VoiceMasterClass(Context context, MasterInterfaceVoice masterInterfaceVoice) {
        this.context = context;
        this.masterInterfaceVoice = masterInterfaceVoice;
        this.voiceHelper = new VoiceHelper(context,this);
    }


    /**
     * start listening call inside method
     * */
    public   void startListening(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                voiceHelper.startListeningMain();
            }
        });

    }

    /**
     * stop listening call inside method
     * */
    public   void stopListening(){
        voiceHelper.stopListeningMain();
    }

    /**
     *  start Speaking call inside method
     * */
    public   void startSpeaking(String text){
        voiceHelper.startSpeakingMain(text,"utteranceId");
    }

    /**
     * destroy voice helper object
     * */
    public  void destroy(){
        voiceHelper.destroyMain();
    }

    /**
     * stop speaking call inside method
     * */
    public  void stopSpeaking(){
        this.voiceHelper.stopSpeakingMain();
    }

    public MasterInterfaceVoice getMasterInterfaceVoice() {
        return masterInterfaceVoice;
    }

    public stateType getCurrentStateType() {
        return currentStateType;
    }

    public void setCurrentStateType(stateType currentStateType) {
        this.currentStateType = currentStateType;
    }
}
