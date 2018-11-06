#import <TalkableSDK/Talkable.h>
#import "TalkableBridge.h"
#import <React/RCTLog.h>

@implementation TalkableIOS : NSObject

//export the name of the native module as 'Device' since no explicit name is mentioned
RCT_EXPORT_MODULE(TalkableIOS);

//exports a method registerOriginStandalone to javascript
RCT_EXPORT_METHOD(registerOriginStandalone:(NSDictionary *) params) {
  dispatch_async(dispatch_get_main_queue(), ^{
    [[Talkable manager] registerOrigin:TKBLAffiliateMember params:nil];
  });
}

RCT_EXPORT_METHOD(registerOriginPostPurchase:(NSDictionary *)params) {
  dispatch_async(dispatch_get_main_queue(), ^{
    [[Talkable manager] registerOrigin:TKBLPurchase params:params];
  });
}

@end

