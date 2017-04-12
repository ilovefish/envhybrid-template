#import <Cordova/CDVPlugin.h>

@interface SharedDataPlugin : CDVPlugin
- (void)isInitialized:(CDVInvokedUrlCommand*)command;
- (void)initialize:(CDVInvokedUrlCommand*)command;
- (void)setItem:(CDVInvokedUrlCommand*)command;
- (void)getItem:(CDVInvokedUrlCommand*)command;
- (void)removeItem:(CDVInvokedUrlCommand*)command;
@end