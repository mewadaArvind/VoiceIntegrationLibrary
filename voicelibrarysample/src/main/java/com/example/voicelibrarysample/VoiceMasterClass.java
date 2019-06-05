package com.example.voicelibrarysample;

import android.content.Context;
import com.example.voicelibrarysample.VoiceHelperMain.VoiceHelper;
import com.example.voicelibrarysample.UserSetUIInterface.MasterInterfaceVoice;


/**
* Created by Mewada Arvind on 1 June 2019
 * Voice Master class
 * handle voice integration
 * and handle network process here
 * */
public class VoiceMasterClass {

    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private VoiceHelper voiceHelper;
    public String eventStatus;

    public VoiceMasterClass() {

    }

    public enum EventStatus {
        PROCESSING, LISTENING, SPEAKING, REST; //ENUM
    }

    /**
     * voice master class initialisation
     * @Param Context
     * @Param MasterInterface
     * */
    public  VoiceMasterClass(Context context, MasterInterfaceVoice masterInterfaceVoice, String eventStatus) {
        this.context = context;
        this.eventStatus = eventStatus;
        this.masterInterfaceVoice = masterInterfaceVoice;
        this.voiceHelper = new VoiceHelper(context,masterInterfaceVoice, eventStatus);
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


    public String getEventStatus() {
        return eventStatus;
    }

    private void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }
}
