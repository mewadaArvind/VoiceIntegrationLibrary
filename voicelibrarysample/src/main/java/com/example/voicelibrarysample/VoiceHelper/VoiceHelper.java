package com.example.voicelibrarysample.VoiceHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.widget.EditText;



import com.example.voicelibrarysample.VoiceMasterClass;

import java.util.List;
import java.util.Locale;

import static android.speech.SpeechRecognizer.createSpeechRecognizer;

public class VoiceHelper  {

    private SpeechRecognizer speechRecognizer = null;
    private TextToSpeech tts = null;
    private Listening listening;
    private Processing processing;
    private StopListening stopping;
    private UtteranceProgressListener utteranceProgressListener;
    private boolean isSpeechRecognizerRunning;
    private Context context;
    private VoiceMasterClass voiceMasterClass;

    public VoiceHelper(Context context, VoiceMasterClass voiceMasterClass){
        this.voiceMasterClass = voiceMasterClass;
        this.context = context;
    }
    public VoiceHelper(Context context, Listening listening
            , Processing processing, StopListening stopping
            , final UtteranceProgressListener utteranceProgressListener
            , EditText editText){
        this.context = context;
        this.listening = listening;
        this.processing = processing;
        this.stopping = stopping;
        this.utteranceProgressListener = utteranceProgressListener;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    tts.setOnUtteranceProgressListener(utteranceProgressListener);
                    tts.setPitch(0.8f);
                    tts.setSpeechRate(1f);
                    Locale locale = new Locale("en","IN");
                    int result = tts.setLanguage(locale);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    } else {

                    }
                } else {
                    Log.e("TTS", "Initialization Failed!");
                }
            }
        });
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
        stopping.close();
        voiceMasterClass.getMasterInterfaceVoice().stopListening();
    }

    public void startListeningMain(){
        if (speechRecognizer != null)
        {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();

            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {
                    isSpeechRecognizerRunning  = true;
                    listening.data(null,null);
                }

                @Override
                public void onBeginningOfSpeech() {
                    listening.data(null,null);
                }

                @Override
                public void onRmsChanged(float v) {
                }

                @Override
                public void onBufferReceived(byte[] bytes) {

                }

                @Override
                public void onEndOfSpeech() {
                    stopping.close();
                    isSpeechRecognizerRunning = false;
                }

                @Override
                public void onError(int i) {
                    stopping.close();
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
                    listening.data(null,msg);

                    processing.run(msg);
                }

                @Override
                public void onPartialResults(Bundle bundle) {
                    List<String> arrayList = (List<String>) bundle.get("results_recognition");
                    String msg = arrayList.get(0);
                    listening.data(msg,null);
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
