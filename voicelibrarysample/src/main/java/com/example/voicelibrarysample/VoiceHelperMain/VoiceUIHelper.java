package com.example.voicelibrarysample.VoiceHelperMain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;



public class VoiceUIHelper {

    private EditText editText;
    private Context context;
    private boolean isAppendTextON;

    public VoiceUIHelper(Context context
            , boolean isAppendTextON,EditText editText){
        this.context = context;
        this.editText =editText;
        this.isAppendTextON = isAppendTextON;
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
