package com.example.voicelibrarysample;

/**
 * master interface integration all voice
 * relented action and extend network process
 * */
public interface MasterInterfaceVoice extends NetworkCallAfterFinalMessageRecieved{
//   start listening process
    void startedListening();
//    live text change show
    void liveTextChangesShow();
//  final test show if success
    void finalResultShow();
//    error if something wrong
    void errorShow();
//    network processing
    void processingNetwork();
//  received process action perform
    void recievedProcess();
//    stop all listening process
    void stopAll();
}
