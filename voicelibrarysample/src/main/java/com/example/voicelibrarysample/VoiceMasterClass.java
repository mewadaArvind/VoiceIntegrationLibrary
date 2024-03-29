package com.example.voicelibrarysample;

import android.Manifest;
import android.app.Activity;
import android.content.Context;

import com.example.voicelibrarysample.UIStatusInterface.VoiceStatusInterface;
import com.example.voicelibrarysample.VoiceHelperMain.VoiceHelper;
import com.example.voicelibrarysample.UserSetUIInterface.MasterInterfaceVoice;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;


/**
* Created by Mewada Arvind on 1 June 2019
 * Voice Master class
 * handle voice integration
 * and handle network process here
 * */
public class VoiceMasterClass implements VoiceStatusInterface {

    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private VoiceHelper voiceHelper;
    public EventStatus eventStatus;
    public static enum EventStatus {
        PROCESSING, LISTENING, SPEAKING, REST; //ENUM
    }

    public VoiceMasterClass() {

    }

    /**
     * voice master class initialisation
     * @Param Context
     * @Param MasterInterface
     * */
    public  VoiceMasterClass(Context context, MasterInterfaceVoice masterInterfaceVoice) {
        this.context = context;
        this.setEventStatus(EventStatus.REST);
        this.masterInterfaceVoice = masterInterfaceVoice;
        this.voiceHelper = new VoiceHelper(context,masterInterfaceVoice, this);
    }

    @Override
    public void VoiceStatusListening() {
        setEventStatus(EventStatus.LISTENING);
    }

    @Override
    public void VoiceStatusSpeaking() {
        setEventStatus(EventStatus.SPEAKING);
    }

    @Override
    public void VoiceStatusProcessing() {
        setEventStatus(EventStatus.PROCESSING);
    }

    @Override
    public void VoiceStatusResting() {
        setEventStatus(EventStatus.REST);
    }

    /**
     * start listening call inside method
     * */
    public   void startListening(Activity activity) {
        Dexter.withActivity(activity)
                .withPermission(Manifest.permission.RECORD_AUDIO)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {/* ... */
                        voiceHelper.startListeningMain();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {/* ... */}

                    @Override
                    public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permission, PermissionToken token) {


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


    public EventStatus getEventStatus() {
        return eventStatus;
    }

    private void setEventStatus(EventStatus eventStatus) {
        this.eventStatus = eventStatus;
    }
}
