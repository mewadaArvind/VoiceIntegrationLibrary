package com.example.voicelibrarysample.VoiceHelperMain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.EditText;

import com.example.voicelibrarysample.VoiceMasterClass;


public class VoiceUIHelper {

    private String eventStatus;
    private Context context;
    private boolean isAppendTextON;

    public VoiceUIHelper(Context context
            , boolean isAppendTextON, String eventStatus){
        this.context = context;
        this.eventStatus = eventStatus;
        this.isAppendTextON = isAppendTextON;
    }

//    public void setValueInEditText(@NonNull String msg){
//        if(isAppendTextON){
//            if(editText.getText().length() > 0){
//                editText.append(" "+msg);
//            }else{
//                editText.setText(msg);
//            }
//        }else{
//            editText.setText(msg);
//        }
//    }

    public void startinListening() {
        String s =  new VoiceMasterClass().getEventStatus();
        switch (s){
            case "LISTENING":{
                Log.e("Listening...","listing ui inside");
                break;
            }
            case "PROCESSING":{

                break;
            }
        }
    }
}
