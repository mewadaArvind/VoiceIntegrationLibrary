package com.example.voicelibrarysample.UserSetUIInterface;

/**
 * Created by Mewada Arvind on 1 June 2019
 * network related process here
 * */
public interface Network {

   /**
   * all network process
   */
    void network();

    /**
     * received process action perform
     * */
    void received();

    /**
     * stop all listening process
     * */
    void stopedListening();
}
