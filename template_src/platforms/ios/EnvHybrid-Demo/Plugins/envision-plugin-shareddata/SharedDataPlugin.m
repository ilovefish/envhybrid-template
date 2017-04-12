#import "SharedDataPlugin.h"
#import <Cordova/CDVPluginResult.h>
#import <EnvHybridLib/AppData.h>

@implementation SharedDataPlugin
- (void)isInitialized:(CDVInvokedUrlCommand*)command
{
    if([AppData sharedData].isInitialized){
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }else{
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:NO];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }
}

- (void)initialize:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        if(![AppData sharedData].isInitialized){
            id value = [command argumentAtIndex:0];
            [[AppData sharedData]initializeWith:value];
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:YES];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }else{
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:NO];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }
    }];
}

- (void)setItem:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        NSString * key = [command argumentAtIndex:0];
        id value = [command argumentAtIndex:1];
        
        [[AppData sharedData] setItem:value withKey: key];
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)getItem:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        NSString * key = [command argumentAtIndex:0];
        id value = [[AppData sharedData] getItem:key];
        CDVPluginResult* pluginResult = nil;
        if(value && [value isKindOfClass:[NSString class]]){
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:value];
        }else if(value && [value isKindOfClass:[NSDictionary class]]){
            pluginResult =[CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:value];
        }else if(value && [value isKindOfClass:[NSArray class]]){
            pluginResult =[CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsArray:value];
        }else if(value && [value isKindOfClass:[NSNumber class]]){
            NSNumber * nValue = value;
            if (strcmp([nValue objCType], @encode(BOOL)) == 0) {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsBool:nValue.boolValue];
            }else if(strcmp([nValue objCType], @encode(int)) == 0){
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsInt:nValue.intValue];
            }else {
                pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDouble:nValue.floatValue];
            }
        }else{
            NSLog(@"@@ not found key %@" , key);
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        }
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)removeItem:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        NSString * key = [command argumentAtIndex:0];
        [[AppData sharedData] removeItem:key];
        CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}
@end
