package com.example.voicelibrarysample.VoiceInputHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.EditText;

import com.example.voicelibrarysample.VoiceHelper.Listening;
import com.example.voicelibrarysample.VoiceHelper.StopListening;
import com.example.voicelibrarysample.VoiceHelper.VoiceUIHelper;

import java.util.List;
import java.util.Locale;

import static android.speech.SpeechRecognizer.createSpeechRecognizer;

/**
 * Created By Mewada Arvind on 4-06-2019
 * Voice Input Helper call
 * listening
 * stop listening
 * is speech Recognizer
 * context
 * */
public class VoiceInputHelper {

    private SpeechRecognizer speechRecognizer = null;
    private TextToSpeech tts = null;
    private Listening listening;
    private StopListening stopping;
    private boolean isSpeechRecognizerRunning;
    private Context context;
    private VoiceUIHelper voiceUIHelper;

    /**
     * constructor voice input helper
     * @Param is voice ModuleOn
     * @Param listening
     * @Param stopListening
     * */
    public VoiceInputHelper(Context context, Listening listening
            , StopListening stopping, EditText editText){
        this.context = context;
        this.listening = listening;
        this.voiceUIHelper = new VoiceUIHelper(context,editText, true);
        this.stopping = stopping;
        tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
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

    /**
     * currently speaking
     * on or off checked
     * */
    public boolean isCurrentlySpeaking(){
        if(tts != null && tts.isSpeaking()){
            return true;
        }else{
            return false;
        }
    }

    /**
     * currently Listening
     * on or off checked
     * */
    public boolean isCurrentlyListening(){
        return isSpeechRecognizerRunning;
    }

    /**
     * currently start speaking
     * on or off checked
     * */
    public void startSpeaking(String msg, String utteranceID){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(msg,TextToSpeech.QUEUE_FLUSH,null,utteranceID);
        }
    }

    public void stopSpeaking(){
        isSpeechRecognizerRunning = false;
        if(tts != null && tts.isSpeaking()){
            tts.stop();
        }
    }

    public void stopListening(){
        if(speechRecognizer != null){
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();
        }
        stopping.close();
    }

    public void startListening(){
        if (speechRecognizer != null)
        {
            speechRecognizer.stopListening();
            speechRecognizer.cancel();
            speechRecognizer.destroy();

            speechRecognizer.setRecognitionListener(new RecognitionListener() {
                @Override
                public void onReadyForSpeech(Bundle bundle) {
                    isSpeechRecognizerRunning  =true;
                    listening.data("Listening...",null);
                }

                @Override
                public void onBeginningOfSpeech() {
                    listening.data("Listening...",null);
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
        }
    }

    public void destroy(){
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
    }
}
