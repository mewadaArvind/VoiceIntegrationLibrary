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

    private enum state {
        PROCESSING, LISTENING, SPEAKING, REST; //ENUM
    }
    private int stateType;
    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private static VoiceHelper voiceHelper;

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

    public VoiceMasterClass() {
    }

    /**
     * start listening call inside method
     * */
    public  static void startListening(){
        voiceHelper.startListeningMain();
    }

    /**
     * stop listening call inside method
     * */
    public  static void stopListening(){
        voiceHelper.stopListeningMain();
    }

    /**
     *  start Speaking call inside method
     * */
    public  static void startSpeaking(String text){
        voiceHelper.startSpeakingMain(text,"utteranceId");
    }

    /**
     * destroy voice helper object
     * */
    public static void destroy(){
        voiceHelper.destroyMain();
    }

    /**
     * stop speaking call inside method
     * */
    public static void stopSpeaking(){
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
