cordova.define("envision-plugin-shellrouter.ShellRouterPlugin", function(require, exports, module) {

  var ShellRouterPlugin = function () {
  };

  ShellRouterPlugin.prototype.pushState = function (succ, fail, path) {
    cordova.exec(succ, fail, 'ShellRouterPlugin', 'pushState', [path]);
  };
  
  ShellRouterPlugin.prototype.replaceState = function (succ, fail, path) {
    cordova.exec(succ, fail, 'ShellRouterPlugin', 'replaceState', [path]);
  };

  ShellRouterPlugin.prototype.goBack = function (succ, fail) {
    cordova.exec(succ, fail, 'ShellRouterPlugin', 'goBack', []);
  };

  ShellRouterPlugin.prototype.showNativeMask = function (succ, fail, show) {
    cordova.exec(succ, fail, 'ShellRouterPlugin', 'showNativeMask', [show]);
  };

  ShellRouterPlugin.prototype.setNavigationBarTitle = function (succ, fail, title) {
    cordova.exec(succ, fail, 'ShellRouterPlugin', 'setNavigationBarTitle', [title]);
  };

  if (!window.plugins) {
      window.plugins = {};
  }

  var instance = new ShellRouterPlugin();
  if (!window.plugins.ShellRouterPlugin) {
      window.plugins.ShellRouterPlugin = instance;
  }
  module.exports = instance;


});
