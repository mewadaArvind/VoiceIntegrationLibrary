package com.example.voicelibrarysample;

/**
 * Created by Mewada Arvind on 1 June 2019
 * master interface integration all voice
 * relented action and extend network process
 * staring listening interface
 * live text interface
 * final message interface
 * received final result interface
 * network interface extends all
 * */
public interface MasterInterfaceVoice extends StaringListeningMaster, LiveTextChangesMaster
        ,FinalResultMaster, NetworkCallAfterFinalMessageRecieved, ReceivedFinalAllMaster{
    /**
     * error if something wrong
     * */
    void errorShow();
}
