#### `npm install`
#### `npm run ios`

You must integrate the documentaion Talkable IOS SDK http://docs.talkable.com/ios_sdk/getting_started.html#

After you create Native Module IOS for calling the methods of Talkable IOS SDK from React Native Application:

1. Create your module files
Open the workspace for your app in Xcode and create a new file. Select Cocoa Touch Class to create both a header and an implementation file.
Make sure it subclasses NSObject and save it in your App folder.
2. Update your header file
A .h header file contains all the information about a class that should be publicly known like properties and functions.
Update your header file to implement React’s RCTBridgeModule:

TalkableBridge.h ( ios/TalkableBridge/TalkableBridge.h)

3. Update your implementation file
A .m implementation file contains the implementations of all functions listed in the header and any private instance variables and methods.

Your class should contain two macros, RCT_EXPORT_MODULE() and RCT_EXPORT_METHOD() . Update your .m file to implement these two macros.

RCT_EXPORT_MODULE registers our module with the bridge. It takes an optional argument in case you want to name the JavaScript (JS) module differently to the Objective-C class name.

RCT_EXPORT_METHOD macro exposes our new method to JS. In this case, we’re just logging the supplied argument back to the console.

You must call all native method of Talkable SDK IOS in the main thread.
```
dispatch_async(dispatch_get_main_queue(), ^{
  [[Talkable manager] registerOrigin:TKBLAffiliateMember params:nil];
});
```
TalkableBridge.m (ios/TalkableBridge/TalkableBridge.m)

4. Use your Talkable Native Method in JS
  Our new method is now available in JS through the NativeModules object:
  - method showStandaloneCampaign in App.js for Standalone Campaign (http://docs.talkable.com/ios_sdk/integration/standalone.html#ios-sdk-integration-standalone)
  - method showPostPurchaseCampaign in App.js for Post Purchase Campaign (http://docs.talkable.com/ios_sdk/integration/post_purchase.html#ios-sdk-integration-post-purchase)

#### `npm run android`

You must integrate the documentaion Talkable Android SDK http://docs.talkable.com/android_sdk/getting_started.html

After you create Native Module Android for calling the methods of Talkable Android SDK from React Native Application:

1. Create your module file
Open the workspace for your app in Android Studio and create a new file. Select new Java Class to create a file.
TalkableBridge.java (android/app/src/main/com/your-app-name/TalkableBridge.java)

ReactContextBaseJavaModule requires that a method called getName is implemented. The purpose of this method is to return the string name of the NativeModule which represents this class in JavaScript. So here we will call this TalkableAndroid so that we can access it through React.NativeModules.TalkableAndroid in JavaScript.

2. Register the Module
Create a new Java Class named TalkableBridgePackage.java
TalkableBridgePackage.java (android/app/src/main/com/your-app-name/TalkableBridgePackage.java)

3. Update MainApplication.java
The package needs to be provided in the getPackages method of the MainApplication.java file. This file exists under the android folder in your react-native application directory. The path to this file is: android/app/src/main/java/com/your-app-name/MainApplication.java.

4. Use your Talkable Native Method in JS
  Our new method is now available in JS through the NativeModules object:
  - method showStandaloneCampaign in App.js for Standalone Campaign (http://docs.talkable.com/android_sdk/integration/standalone.html)
  - method showPostPurchaseCampaign in App.js for Post Purchase Campaign (http://docs.talkable.com/android_sdk/integration/post_purchase.html)

This project was bootstrapped with [Create React Native App](https://github.com/react-community/create-react-native-app).

Below you'll find information about performing common tasks. The most recent version of this guide is available [here](https://github.com/react-community/create-react-native-app/blob/master/react-native-scripts/template/README.md).

## Table of Contents

* [Updating to New Releases](#updating-to-new-releases)
* [Available Scripts](#available-scripts)
  * [npm start](#npm-start)
  * [npm test](#npm-test)
  * [npm run ios](#npm-run-ios)
  * [npm run android](#npm-run-android)
  * [npm run eject](#npm-run-eject)
* [Writing and Running Tests](#writing-and-running-tests)
* [Environment Variables](#environment-variables)
  * [Configuring Packager IP Address](#configuring-packager-ip-address)
* [Customizing App Display Name and Icon](#customizing-app-display-name-and-icon)
* [Sharing and Deployment](#sharing-and-deployment)
  * [Publishing to Expo's React Native Community](#publishing-to-expos-react-native-community)
  * [Building an Expo "standalone" app](#building-an-expo-standalone-app)
  * [Ejecting from Create React Native App](#ejecting-from-create-react-native-app)
    * [Build Dependencies (Xcode & Android Studio)](#build-dependencies-xcode-android-studio)
    * [Should I Use ExpoKit?](#should-i-use-expokit)
* [Troubleshooting](#troubleshooting)
  * [Networking](#networking)
  * [iOS Simulator won't open](#ios-simulator-wont-open)
  * [QR Code does not scan](#qr-code-does-not-scan)

## Updating to New Releases

You should only need to update the global installation of `create-react-native-app` very rarely, ideally never.

Updating the `react-native-scripts` dependency of your app should be as simple as bumping the version number in `package.json` and reinstalling your project's dependencies.

Upgrading to a new version of React Native requires updating the `react-native`, `react`, and `expo` package versions, and setting the correct `sdkVersion` in `app.json`. See the [versioning guide](https://github.com/react-community/create-react-native-app/blob/master/VERSIONS.md) for up-to-date information about package version compatibility.

## Available Scripts

If Yarn was installed when the project was initialized, then dependencies will have been installed via Yarn, and you should probably use it to run these commands as well. Unlike dependency installation, command running syntax is identical for Yarn and NPM at the time of this writing.

### `npm start`

Runs your app in development mode.

Open it in the [Expo app](https://expo.io) on your phone to view it. It will reload if you save edits to your files, and you will see build errors and logs in the terminal.

Sometimes you may need to reset or clear the React Native packager's cache. To do so, you can pass the `--reset-cache` flag to the start script:

```
npm start --reset-cache
# or
yarn start --reset-cache
```

#### `npm test`

Runs the [jest](https://github.com/facebook/jest) test runner on your tests.

#### `npm run ios`

Like `npm start`, but also attempts to open your app in the iOS Simulator if you're on a Mac and have it installed.

#### `npm run android`

Like `npm start`, but also attempts to open your app on a connected Android device or emulator. Requires an installation of Android build tools (see [React Native docs](https://facebook.github.io/react-native/docs/getting-started.html) for detailed setup). We also recommend installing Genymotion as your Android emulator. Once you've finished setting up the native build environment, there are two options for making the right copy of `adb` available to Create React Native App:

##### Using Android Studio's `adb`

1. Make sure that you can run adb from your terminal.
2. Open Genymotion and navigate to `Settings -> ADB`. Select “Use custom Android SDK tools” and update with your [Android SDK directory](https://stackoverflow.com/questions/25176594/android-sdk-location).

##### Using Genymotion's `adb`

1. Find Genymotion’s copy of adb. On macOS for example, this is normally `/Applications/Genymotion.app/Contents/MacOS/tools/`.
2. Add the Genymotion tools directory to your path (instructions for [Mac](http://osxdaily.com/2014/08/14/add-new-path-to-path-command-line/), [Linux](http://www.computerhope.com/issues/ch001647.htm), and [Windows](https://www.howtogeek.com/118594/how-to-edit-your-system-path-for-easy-command-line-access/)).
3. Make sure that you can run adb from your terminal.

#### `npm run eject`

This will start the process of "ejecting" from Create React Native App's build scripts. You'll be asked a couple of questions about how you'd like to build your project.

**Warning:** Running eject is a permanent action (aside from whatever version control system you use). An ejected app will require you to have an [Xcode and/or Android Studio environment](https://facebook.github.io/react-native/docs/getting-started.html) set up.

## Sharing and Deployment

Create React Native App does a lot of work to make app setup and development simple and straightforward, but it's very difficult to do the same for deploying to Apple's App Store or Google's Play Store without relying on a hosted service.

### Ejecting from Create React Native App

If you want to build and deploy your app yourself, you'll need to eject from CRNA and use Xcode and Android Studio.

This is usually as simple as running `npm run eject` in your project, which will walk you through the process. Make sure to install `react-native-cli` and follow the [native code getting started guide for React Native](https://facebook.github.io/react-native/docs/getting-started.html).
