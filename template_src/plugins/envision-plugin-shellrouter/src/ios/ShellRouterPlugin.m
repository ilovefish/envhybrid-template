#import "ShellRouterPlugin.h"
#import <Cordova/CDVPluginResult.h>
#import <Routable/Routable.h>
#import "NSString+Envision.h"
#import "UINavigationController+Envision.h"

@implementation ShellRouterPlugin
- (void)replaceState:(CDVInvokedUrlCommand*)command
{
    NSString * path = [command argumentAtIndex:0];
    if([path stringIsValid]){
        [[Routable sharedRouter] open:path];
    }
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)pushState:(CDVInvokedUrlCommand*)command
{
    NSString * path = [command argumentAtIndex:0];
    if([path stringIsValid]){
        [[Routable sharedRouter] open:path];
    }
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)goBack:(CDVInvokedUrlCommand*)command
{
    [[Routable sharedRouter] popViewControllerFromRouterAnimated:YES];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)showNativeMask:(CDVInvokedUrlCommand *)command
{
    NSNumber * showMask = [command argumentAtIndex:0];
    if(showMask.boolValue == true){
        UIWindow * currentWindow =[[[UIApplication sharedApplication] windows] objectAtIndex:0];
        CGRect webRect = self.webView.bounds;
        CGRect position =[self.webView convertRect:webRect toView:currentWindow];
    }
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)setNavigationBarTitle:(CDVInvokedUrlCommand *)command
{
    NSString * titleString = [command argumentAtIndex:0];
    if(titleString && self.viewController.navigationController && self.viewController.navigationController.visibleViewController){
        SEL onNaviTopSel = NSSelectorFromString(@"onNaviTop:");
        if([self.viewController respondsToSelector:onNaviTopSel]){
            [self.viewController.navigationController ex_setTitle:titleString withAction:onNaviTopSel target:self.viewController];
        }else{
            [self.viewController.navigationController ex_setTitle:titleString withAction:nil target:nil];
        }
        
    }
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)showTabBarBadge:(CDVInvokedUrlCommand *)command{
    NSNumber * index = [command argumentAtIndex:0];
    if(index && index.intValue>=0){
        //[[NSNotificationCenter defaultCenter] postNotificationName:kEventShowTagBadge object:index];
    }
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)hideTabBarBadge:(CDVInvokedUrlCommand *)command
{
    NSNumber * index = [command argumentAtIndex:0];
    //[[NSNotificationCenter defaultCenter] postNotificationName:kEventHideTagBadge object:index];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
@end
