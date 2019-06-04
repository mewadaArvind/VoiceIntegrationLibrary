package com.example.voicelibrarysample;

import android.content.Context;
/**
* Created by Mewada Arvind on 1 June 2019
 * Voice Master class
 * handle voice integration
 * and handle network process here
 * */
public class VoiceMasterClass {

    private int  PROCESSING, LISTENING, SPEAKING, REST;

    /**
     * voice master class initialisation
     * @Param Context
     * @Param MasterInterface
     * */
    public  VoiceMasterClass(Context context,MasterInterfaceVoice masterInterfaceVoice) { }


    /**
     * start listening call inside method
     * */
    public  void startListening(){

    }

    /**
     * stop listening call inside method
     * */
    public  void stopListening(){

    }

    /**
     *  start Speaking call inside method
     * */
    public  void startSpeaking(){

    }

    /**
     * destroyedAll call inside method
     * */
    public  void destroyed(){

    }

    /**
     * stop speaking call inside method
     * */
    public  void stopSpeaking(){

    }

    /**
     * Getter Processing
     * return int
     * */
    public int getPROCESSING() {
        return PROCESSING;
    }

    /**
     * Setter Processing
     * return int
     * */
    private void setPROCESSING(int PROCESSING) {
        this.PROCESSING = PROCESSING;
    }

    /**
     * Getter listening
     * return int
     * */
    public int getLISTENING() {
        return LISTENING;
    }

    /**
     * Setter listening
     * return int
     * */
    private void setLISTENING(int LISTENING) {
        this.LISTENING = LISTENING;
    }

    /**
     * Getter Speaking
     * return int
     * */
    public int getSPEAKING() {
        return SPEAKING;
    }

    /**
     * Setter Speaking
     * return int
     * */
    private void setSPEAKING(int SPEAKING) {
        this.SPEAKING = SPEAKING;
    }

    /**
     * Getter Rest
     * return int
     * */
    public int getREST() {
        return REST;
    }

    /**
     * Setter  rest
     * return int
     * */
    private void setREST(int REST) {
        this.REST = REST;
    }
}
