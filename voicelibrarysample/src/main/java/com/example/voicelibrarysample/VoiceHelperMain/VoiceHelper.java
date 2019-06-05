package com.example.voicelibrarysample.VoiceHelperMain;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;


import com.example.voicelibrarysample.UIStatusInterface.VoiceStatusInterface;
import com.example.voicelibrarysample.UserSetUIInterface.MasterInterfaceVoice;


import java.util.List;
import static android.speech.SpeechRecognizer.createSpeechRecognizer;


public class VoiceHelper  {

    private SpeechRecognizer speechRecognizer = null;
    private TextToSpeech tts = null;
    private boolean isSpeechRecognizerRunning;
    private Context context;
    private MasterInterfaceVoice masterInterfaceVoice;
    private VoiceStatusInterface voiceStatusInterface;


    public VoiceHelper(Context context, MasterInterfaceVoice masterInterfaceVoice, VoiceStatusInterface voiceStatusInterface) {
        this.masterInterfaceVoice = masterInterfaceVoice;
        this.context = context;
        this.voiceStatusInterface = voiceStatusInterface;
        speechRecognizer = createSpeechRecognizer(context);
    }


    public boolean isCurrentlySpeakingMain() {
        if (tts != null && tts.isSpeaking()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCurrentlyListeningMain() {
        return isSpeechRecognizerRunning;
    }

    public void startSpeakingMain(String msg, String utteranceID) {
        tts.speak(msg, TextToSpeech.QUEUE_FLUSH, null, utteranceID);
        voiceStatusInterface.VoiceStatusSpeaking();
        masterInterfaceVoice.startedListening();
    }

    public void stopSpeakingMain() {
        isSpeechRecognizerRunning = false;
        if (tts != null && tts.isSpeaking()) {
            tts.stop();
            masterInterfaceVoice.stopedListening();
            voiceStatusInterface.VoiceStatusResting();
        }

    }

    public void stopListeningMain() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();
        }
        masterInterfaceVoice.stopedListening();
        voiceStatusInterface.VoiceStatusResting();
    }

    public void startListeningMain() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {
                    isSpeechRecognizerRunning = true;
                }

                @Override
                public void onBeginningOfSpeech() {

                }

                @Override
                public void onRmsChanged(float v) {
                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {
                    masterInterfaceVoice.startedListening();
                    isSpeechRecognizerRunning = false;

                }

                @Override
                public void onError(int i) {
                    masterInterfaceVoice.stopedListening();
                    isSpeechRecognizerRunning = false;
                }

                @Override
                public void onResults(Bundle bundle) {
                    float[] confArr = (float[]) bundle.get("confidence_scores");
                    List<String> arr = (List<String>) bundle.get("results_recognition");
                    int i = 0;
                    float maxConf = 0;
                    String msg = "";
                    for (String a : arr) {
                        if (maxConf == 0 || maxConf < confArr[i]) {
                            maxConf = confArr[i];
                            msg = a;
                        }
                        Log.d("VOICE_TAG", a + ": " + confArr[i]);
                        i++;
                    }
                    Log.e("Final...",msg);
                    masterInterfaceVoice.finalResultShow(msg);
                    voiceStatusInterface.VoiceStatusProcessing();
                    masterInterfaceVoice.network();
                    masterInterfaceVoice.received();
                    voiceStatusInterface.VoiceStatusResting();
                }

                @Override
                public void onPartialResults(Bundle bundle) {
                    List<String> arrayList = (List<String>) bundle.get("results_recognition");
                    String msg = arrayList.get(0);
                    masterInterfaceVoice.liveTextChangesShow(msg);
                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });

            final Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            recognizerIntent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-IN");
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, (context).getPackageName());
            speechRecognizer.startListening(recognizerIntent);
            masterInterfaceVoice.startedListening();
            voiceStatusInterface.VoiceStatusListening();
        }
    }

    public void destroyMain() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        masterInterfaceVoice.destroy();
        voiceStatusInterface.VoiceStatusResting();
    }

}
