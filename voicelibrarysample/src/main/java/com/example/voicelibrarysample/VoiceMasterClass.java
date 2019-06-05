package com.example.voicelibrarysample;

import android.content.Context;
import android.widget.TextView;

import com.example.voicelibrarysample.VoiceHelper.VoiceHelper;



/**
* Created by Mewada Arvind on 1 June 2019
 * Voice Master class
 * handle voice integration
 * and handle network process here
 * */
public class VoiceMasterClass {


    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private  VoiceHelper voiceHelper;
    private int value;

    private enum EventStatus {
        PROCESSING, LISTENING, SPEAKING, REST; //ENUM
    }


    /**
     * voice master class initialisation
     * @Param Context
     * @Param MasterInterface
     * */
    public  VoiceMasterClass(Context context, MasterInterfaceVoice masterInterfaceVoice,int value) {
        this.context = context;
        this.value = value;
        this.masterInterfaceVoice = masterInterfaceVoice;
        this.voiceHelper = new VoiceHelper(context,this);
    }


    /**
     * start listening call inside method
     * */
    public   void startListening(){
        voiceHelper.startListeningMain();
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

    public int getValue() {
        return value;
    }
    //Just for testing from some SO answers, but no use
    public void setValue(int value) {
        this.value = value;
    }

}
