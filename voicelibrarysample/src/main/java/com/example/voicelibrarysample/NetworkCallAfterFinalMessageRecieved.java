package com.example.voicelibrarysample;

/**
 * Created by Mewada Arvind on 1 June 2019
 * network related process here
 * */
public interface NetworkCallAfterFinalMessageRecieved {

   /**
   * all network process
   */
    void doNetwokingProcess();

    /**
     * received process action perform
     * */
    void recievedProcess();

    /**
     * stop all listening process
     * */
    void stopedListening();
}
