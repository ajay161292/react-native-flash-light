//
//  RCTTorchClass.swift
// 
//  Created by APPLE on 21/07/22.

import Foundation
import AVFoundation
import React

@objc(RCTTorchClass)
class RCTTorchClass: NSObject{
  
  @objc
  func flashLevel(_ currentLevel: Float) {
      let currentLevel = RCTConvert.float(currentLevel)   
    
      guard let device = AVCaptureDevice.default(for: AVMediaType.video) else { return }
      guard device.hasTorch else { return }

      do {
          try device.lockForConfiguration()

          if (device.torchMode == AVCaptureDevice.TorchMode.on) {
            try device.setTorchModeOn(level: currentLevel)
          }
          device.unlockForConfiguration()
      } catch {
          print(error)
      }
  }
  
  @objc
  func toggleFlash() {    
      guard let device = AVCaptureDevice.default(for: AVMediaType.video) else { return }
      guard device.hasTorch else { return }

      do {
          try device.lockForConfiguration()

          if (device.torchMode == AVCaptureDevice.TorchMode.on) {
              device.torchMode = AVCaptureDevice.TorchMode.off
          } else {
            device.torchMode = AVCaptureDevice.TorchMode.on
            try device.setTorchModeOn(level: 1.0)
          }
          device.unlockForConfiguration()
      } catch {
          print(error)
      }
  }
  
  @objc
  func flashOff() {    
      guard let device = AVCaptureDevice.default(for: AVMediaType.video) else { return }
      guard device.hasTorch else { return }

      do {
          try device.lockForConfiguration()
          device.torchMode = AVCaptureDevice.TorchMode.off
          device.unlockForConfiguration()
      } catch {
          print(error)
      }
  }
  @objc
  func flashOn() {    
      guard let device = AVCaptureDevice.default(for: AVMediaType.video) else { return }
      guard device.hasTorch else { return }

      do {
          try device.lockForConfiguration()
          device.torchMode = AVCaptureDevice.TorchMode.on
          try device.setTorchModeOn(level: 1.0)
        
          device.unlockForConfiguration()
      } catch {
          print(error)
      }
  }
}
