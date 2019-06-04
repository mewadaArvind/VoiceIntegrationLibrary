package com.example.voicelibrarysample.VoiceHelper;

import android.support.annotation.Nullable;

public interface Listening {
    void data(@Nullable String partialResult, @Nullable String fullResult);
}
