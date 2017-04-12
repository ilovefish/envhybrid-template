# SharedDataPlugin
提供了多个webview之间共享的内存变量的操作方法，包括存储、获取、删除数据的方法

# Installation

```bash
cordova plugin add http://gitlab.envisioncn.com/map-framework/envision-plugin-shareddata.git --save
```

# Methods

```bash
window.plugins.SharedDataPlugin.initialize(state)  // 将初始化状态传递给nativa
window.plugins.SharedDataPlugin.setItem(key, value) //保存数据
window.plugins.SharedDataPlugin.getItem(key) //读取数据
window.plugins.SharedDataPlugin.removeItem(key) //移除数据
```

# Supported Platforms

```bash
ios
```

# Quick Example

```bash
//将初始状态state传递给native
document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.SharedDataPlugin){
        window.plugins.SharedDataPlugin.isInitialized( bInit =>{
          console.log('@@@@@@ bInit ' + bInit);
          if(!bInit){
            window.plugins.SharedDataPlugin.initialize(() => {
              resolve();
            }, () => { reject();}, state);
          }else{
            reject();
          }
        }, null);
      }else{
        reject();
      }
});

document.addEventListener('deviceready', () => {
      if (window.plugins && window.plugins.SharedDataPlugin) {
        window.plugins.SharedDataPlugin.setItem(() => {
          resolve();
        }, () => { reject(); }, key, value);
      } else {
        reject();
      }
 });

document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.SharedDataPlugin){
        window.plugins.SharedDataPlugin.getItem((value)=>{
          resolve(value);
        }, () => { reject(); }, key);
      }else{
        reject();
      }
});

document.addEventListener('deviceready', () => {
      if(window.plugins && window.plugins.SharedDataPlugin){
        window.plugins.SharedDataPlugin.removeItem(()=>{
          resolve();
        }, null, key);
      }else{
        reject();
      }
});
```  


