package com.talkabledemoreactnative;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.talkable.sdk.Talkable;
import com.talkable.sdk.interfaces.TalkableErrorCallback;
import com.talkable.sdk.models.AffiliateMember;
import com.talkable.sdk.utils.TalkableOfferLoadException;

class TalkableBridgeModule extends ReactContextBaseJavaModule {
    public TalkableBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TalkableAndroid";
    }

    @ReactMethod
    public void showOffer() {
        final Activity activity = getCurrentActivity();
        Talkable.showOffer(activity, new AffiliateMember(), new TalkableErrorCallback<TalkableOfferLoadException>() {
            @Override
            public void onError(TalkableOfferLoadException error) {
                // Error handling. Note that it runs on non UI thread
            }
        });
    }
}
