#import <Cordova/CDVPlugin.h>

@interface ShellRouterPlugin : CDVPlugin
- (void)replaceState:(CDVInvokedUrlCommand*)command;
- (void)pushState:(CDVInvokedUrlCommand*)command;
- (void)goBack:(CDVInvokedUrlCommand*)command;
- (void)showNativeMask:(CDVInvokedUrlCommand *)command;
- (void)setNavigationBarTitle:(CDVInvokedUrlCommand *)command;
- (void)showTabBarBadge:(CDVInvokedUrlCommand *)command;
- (void)hideTabBarBadge:(CDVInvokedUrlCommand *)command;
@end