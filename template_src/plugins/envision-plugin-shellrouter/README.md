# ShellRouterPlugin
提供了操作原生界面的一些方法，包括：导航栏标题的设置、角标的设置等方法

# Installation

```bash
cordova plugin add http://gitlab.envisioncn.com/map-framework/envision-plugin-shellrouter.git --save
```

# Methods

```bash
window.plugins.ShellRouterPlugin.showNativeMask(show)   //禁用native UI的用户操作
window.plugins.ShellRouterPlugin.clearCacheFolder([description])
window.plugins.ShellRouterPlugin.setNavigationBarTitle(title)  //设置原生导航栏的标题
window.plugins.ShellRouterPlugin.showTabBarBadge(index，num）//显示tabbar上的角标
window.plugins.ShellRouterPlugin.hideTabBarBadge(index) //隐藏tabbar上的角标
```

# Supported Platforms

```bash
ios
```

# Quick Example

```bash
//禁用native UI的用户操作
document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.ShellRouterPlugin){
        window.plugins.ShellRouterPlugin.showNativeMask(()=>{
          resolve();
        }, ()=>{
          reject();
        }, show);
      }
    });
 //清理缓存文件夹   
 document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.ShellRouterPlugin){
        window.plugins.ShellRouterPlugin.clearCacheFolder(()=>{
          resolve();
        }, ()=>{
          reject();
        }, dirs);
      }
    });
//设置原生导航栏标题
document.addEventListener('deviceready', () => {
      if (window.plugins && window.plugins.ShellRouterPlugin) {
        window.plugins.ShellRouterPlugin.setNavigationBarTitle(() => {
          resolve();
        }, () => {
          reject();
        }, title);
      }
    });
//显示tabbar上的红点
    document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.ShellRouterPlugin){
        window.plugins.ShellRouterPlugin.showTabBarBadge(()=>{
          resolve();
        }, ()=>{
          reject();
        }, index, num);
      }
    });
//隐藏tabbar上的红点
    document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.ShellRouterPlugin){
        window.plugins.ShellRouterPlugin.hideTabBarBadge(()=>{
          resolve();
        }, ()=>{
          reject();
        }, index);
      }
    });
```  


