package com.example.voicelibrarysample;

import android.content.Context;

import com.example.voicelibrarysample.VoiceHelper.VoiceHelper;

import java.util.Date;

/**
* Created by Mewada Arvind on 1 June 2019
 * Voice Master class
 * handle voice integration
 * and handle network process here
 * */
public class VoiceMasterClass {

    private enum state {
        PROCESSING, LISTENING, SPEAKING, REST; //ENUM
    }
    private int stateType;
    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private VoiceHelper voiceHelper;

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
    public  void startListening(){
        voiceHelper.startListeningMain();
    }

    /**
     * stop listening call inside method
     * */
    public  void stopListening(){
        voiceHelper.stopListeningMain();
    }

    /**
     *  start Speaking call inside method
     * */
    public  void startSpeaking(String text){
        voiceHelper.startSpeakingMain(text,"utteranceId");
    }

    /**
     * destroy voice helper object
     * */
    public void destroy(){
        this.voiceHelper.destroyMain();
    }

    /**
     * stop speaking call inside method
     * */
    public  void stopSpeaking(){
        voiceHelper.stopSpeakingMain();
    }

    public MasterInterfaceVoice getMasterInterfaceVoice() {
        return masterInterfaceVoice;
    }

    public int getStateType() {
        return stateType;
    }

    public void setStateType(int stateType) {
        this.stateType = stateType;
    }

}
