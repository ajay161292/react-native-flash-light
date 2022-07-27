//
//  RCTTorch.m
//  Created by APPLE on 21/07/22.

#import <Foundation/Foundation.h>
#import <React/RCTBridgeModule.h>

@interface

RCT_EXTERN_MODULE(RCTTorchClass, NSObject)
RCT_EXTERN_METHOD(toggleFlash)
RCT_EXTERN_METHOD(flashOff)
RCT_EXTERN_METHOD(flashOn)
RCT_EXTERN_METHOD(flashLevel:(float)currentLevel)

@end
