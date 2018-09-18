import React from "react";
import { StyleSheet, Text, View, Button, NativeModules } from "react-native";

const TalkableBridge = NativeModules.TalkableBridge;

export default class App extends React.Component {
  showStandaloneCampaign = () => {
    NativeModules.TalkableBridge.registerOriginStandalone({});
  };

  showPostPurchaseCampaign = () => {
    NativeModules.TalkableBridge.registerOriginPostPurchase({
      purchase: {
        order_number: +new Date(), // Unique order number. Example: '100011'
        subtotal: "100", // Order subtotal (pre-tax, post-discount). Example: '23.97'
        coupon_code: "", // Coupon code that was used at checkout (pass multiple as an array). Example: 'SAVE20'
        shipping_zip: "1", // Optional - used for fraud protection by address. Example: '02222'
        shipping_address: "1", // Full address of the order, make sure to strictly follow a format: 'Apt #, Street address, City, State, ZIP, Country'
        email: "evgeniy.podgaetskiy@talkable.com" // Customer email address who issued a purchase. Example: 'customer@example.com'
      }
    });
  };
  render() {
    return (
      <View style={styles.container}>
        <Text>Talkable</Text>
        <Button
          title="Standalone Campaign"
          onPress={this.showStandaloneCampaign}
        />
        <Button
          title="Post Purchase Campaign"
          onPress={this.showPostPurchaseCampaign}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#fff",
    alignItems: "center",
    justifyContent: "center"
  }
});
