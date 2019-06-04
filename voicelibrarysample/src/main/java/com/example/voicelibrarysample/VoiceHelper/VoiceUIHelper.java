package com.example.voicelibrarysample.VoiceHelper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.EditText;



public class VoiceUIHelper {

    private EditText editText;
    private Context context;
    private boolean isAppendTextON;

    public VoiceUIHelper(Context context
            , EditText editText
            , boolean isAppendTextON){
        this.context = context;
        this.isAppendTextON = isAppendTextON;
        this.editText = editText;
    }

    public void setValueInEditText(@NonNull String msg){
        if(isAppendTextON){
            if(editText.getText().length() > 0){
                editText.append(" "+msg);
            }else{
                editText.setText(msg);
            }
        }else{
            editText.setText(msg);
        }
    }
}
