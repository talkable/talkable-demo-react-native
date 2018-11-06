package com.talkabledemoreactnative;

import android.app.Activity;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.talkable.sdk.Talkable;
import com.talkable.sdk.interfaces.TalkableErrorCallback;
import com.talkable.sdk.models.AffiliateMember;
import com.talkable.sdk.models.Customer;
import com.talkable.sdk.models.Purchase;
import com.talkable.sdk.utils.TalkableOfferLoadException;

import java.io.UnsupportedEncodingException;

class TalkableBridgeModule extends ReactContextBaseJavaModule {
    public TalkableBridgeModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "TalkableAndroid";
    }

    @ReactMethod
    public void registerOriginStandalone() {
        final Activity activity = getCurrentActivity();
        Talkable.showOffer(activity, new AffiliateMember(), new TalkableErrorCallback<TalkableOfferLoadException>() {
            @Override
            public void onError(TalkableOfferLoadException error) {
                // Error handling. Note that it runs on non UI thread
            }
        });
    }

    @ReactMethod
    public void registerOriginPostPurchase(String orderNumber, Double subtotal, String coupon, String email ) {
        final Activity activity = getCurrentActivity();

        Purchase purchase = new Purchase(subtotal, orderNumber, coupon);
        Customer customer = null;
        try {
            customer = new Customer(email);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        purchase.setCustomer(customer);

        Talkable.showOffer(activity, purchase, new TalkableErrorCallback<TalkableOfferLoadException>() {
            @Override
            public void onError(TalkableOfferLoadException error) {
                // Error handling. Note that it runs on non UI thread
            }
        });
    }
}
