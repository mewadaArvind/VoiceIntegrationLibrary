package com.example.voicelibrarysample.VoiceHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.support.annotation.Nullable;
import android.util.Log;



import com.example.voicelibrarysample.VoiceMasterClass;

import java.util.List;


import static android.speech.SpeechRecognizer.createSpeechRecognizer;

public class VoiceHelper  {

    private SpeechRecognizer speechRecognizer = null;
    private TextToSpeech tts = null;
    private UtteranceProgressListener utteranceProgressListener;
    private boolean isSpeechRecognizerRunning;
    private Context context;
    private VoiceMasterClass voiceMasterClass;

    public VoiceHelper(Context context, VoiceMasterClass voiceMasterClass){
        this.voiceMasterClass = voiceMasterClass;
        this.context = context;
        speechRecognizer = createSpeechRecognizer(context);
    }


    public boolean isCurrentlySpeakingMain(){
        if(tts != null && tts.isSpeaking()){
            return true;
        }else{
            return false;
        }
    }

    public boolean isCurrentlyListeningMain(){
        return isSpeechRecognizerRunning;
    }

    public void startSpeakingMain(String msg, String utteranceID){
        tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null,utteranceID);
    }

    public void stopSpeakingMain(){
        isSpeechRecognizerRunning = false;
        if(tts != null && tts.isSpeaking()){
            tts.stop();
        }
    }

    public void stopListeningMain(){
        if(speechRecognizer != null){
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();
        }
        /*stop close listening*/
        new StopListening() {
            @Override
            public void close() {
//                .close()
            }
        };
        voiceMasterClass.getMasterInterfaceVoice().stopListening();
    }

    public void startListeningMain(){
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();
            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {
                    isSpeechRecognizerRunning  = true;
                    new Listening() {
                        @Override
                        public void data(@Nullable String partialResult, @Nullable String fullResult) {

                        }
                    };
                }

                @Override
                public void onBeginningOfSpeech() {
                    new Listening() {
                        @Override
                        public void data(@Nullable String partialResult, @Nullable String fullResult) {

                        }
                    };
                }

                @Override
                public void onRmsChanged(float v) {
                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {
                   new StopListening() {
                       @Override
                       public void close() {

                       }
                   };
                    isSpeechRecognizerRunning = false;
                }

                @Override
                public void onError(int i) {
                    voiceMasterClass.stopListening();
                    new StopListening() {
                        @Override
                        public void close() {

                        }
                    };
                    isSpeechRecognizerRunning = false;
                }

                @Override
                public void onResults(Bundle bundle) {
                    float[] confArr = (float []) bundle.get("confidence_scores");
                    List<String> arr = (List<String>) bundle.get("results_recognition");
                    int i = 0;
                    float maxConf = 0;
                    String msg = "";
                    for(String a: arr){
                        if(maxConf == 0 || maxConf < confArr[i]){
                            maxConf = confArr[i];
                            msg = a;
                        }
                        Log.d("VOICE_TAG",a+": "+confArr[i]);
                        i++;
                    }

                    new Listening() {
                        @Override
                        public void data(@Nullable String partialResult, @Nullable String fullResult) {
                            Log.e("Final...",""+fullResult);
//                                    .data(null, fullResult);
                         new Processing() {
                             @Override
                             public void run(String finalMessage) {
//                                    .run(finalMessage);
                             }
                         };
                        }
                    };

                }

                @Override
                public void onPartialResults(Bundle bundle) {
                    List<String> arrayList = (List<String>) bundle.get("results_recognition");
                    String msg = arrayList.get(0);
                    Log.e("Partial..."," "+msg);
                     new Listening() {
                         @Override
                         public void data(@Nullable String partialResult, @Nullable String fullResult) {
//                            .data(partialResult,null);
                         }
                     };
                }

                @Override
                public void onEvent(int i, Bundle bundle) {

                }
            });

            final Intent recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            recognizerIntent.putExtra(
                    RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-IN");
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
            recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, (context).getPackageName());
            speechRecognizer.startListening(recognizerIntent);
            voiceMasterClass.getMasterInterfaceVoice().startedListening();
        }
    }

    public void destroyMain(){
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        this.voiceMasterClass.getMasterInterfaceVoice().destroy();
    }
}
