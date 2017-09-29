package com.framgia.forder.data.event;

/**
 * Created by nguyenhuy95dn on 9/29/2017.
 */

public class NetWorkStateEvent {

    private boolean mIsConnected;

    public NetWorkStateEvent(boolean isConnected) {
        mIsConnected = isConnected;
    }

    public boolean isConnected() {
        return mIsConnected;
    }

    public void setConnected(boolean connected) {
        mIsConnected = connected;
    }
}
