package com.example.voicelibrarysample;

/**
 *  Created by Mewada Arvind on 1 June 2019
 * on click start or stop
 * handle permission and stated here
 * */
public interface OnClickedInterface {
    /**
     * handle permission state
     * */
    void permissionStatusChecked();

    /*
     *handle start or stop
     * on click
     * */
    void startOrStopOnClick();
}
