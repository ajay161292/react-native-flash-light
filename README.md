# react-native-torch

[![npm version](https://badge.fury.io/js/react-native-torch.svg)](http://badge.fury.io/js/react-native-torch)

A simple React Native plugin to switch a flashlight on/off and manage brightness level.

Currently supports both iOS (>= 8.0) and Android (all versions).

Applies the permission checks on Android API >=25 devices. Below API 25, Android will automatically require the user to accept permissions on install / update.

## Install

```shell
npm install --save react-native-torch
react-native link react-native-torch
```

### Manual install

#### iOS

1. `npm install react-native-torch --save`
2. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
3. Go to `node_modules` ➜ `react-native-torch` and add `RCTTorch.xcodeproj`
4. Expand the `RCTTorch.xcodeproj` ➜ `Products` folder
5. In XCode, in the project navigator, select your project. Add `libRCTTorch.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
6. Click `RCTTorch.xcodeproj` in the project navigator and go the `Build Settings` tab. Make sure 'All' is toggled on (instead of 'Basic'). In the `Search Paths` section, look for `Header Search Paths` and make sure it contains both `$(SRCROOT)/../../react-native/React` and `$(SRCROOT)/../../../React` - mark both as `recursive`.

## Usage

### Without permissions check

#### iOS

```js
import { RCTTorchClass } from "react-native-flash-light";

RCTTorchClass.flashOff(); // Turn ON
RCTTorchClass.flashOn(); // Turn OFF
RCTTorchClass.toggleFlash(); // Toggle Flash

import { Platform } from "react-native";

if (Platform.OS === "ios") {
  RCTTorchClass.flashOn();
}
```

#### android

```js
import { RCTTorchModule } from "react-native-flash-light";

RCTTorchModule.flashOff(); // Turn ON
RCTTorchModule.flashOn(); // Turn OFF
RCTTorchModule.toggleFlash(); // Toggle Flash

import { Platform } from "react-native";

if (Platform.OS === "android") {
  RCTTorchModule.flashOn();
}
```

NOTE:
iOS fails silently, on Android, you can still call without the try/catch block and it won't cause a crash

Android version has flow support.
