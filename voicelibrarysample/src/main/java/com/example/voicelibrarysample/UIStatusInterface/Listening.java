package com.example.voicelibrarysample.UIStatusInterface;

import android.support.annotation.Nullable;

public interface Listening {
    void data(@Nullable String partialResult, @Nullable String fullResult);
}
