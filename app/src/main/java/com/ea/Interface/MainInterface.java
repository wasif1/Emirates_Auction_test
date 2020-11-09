package com.ea.Interface;

import com.ea.Model.EmirateAuctionCarsResponse;

public interface MainInterface {
    void success(EmirateAuctionCarsResponse response, String Ticks);
    void error(String error);
    void progress(boolean flag);
}
