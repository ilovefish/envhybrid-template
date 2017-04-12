
  var SharedDataPlugin = function () {
  };

  SharedDataPlugin.prototype.isInitialized = function (succ, fail) {
      cordova.exec(succ, fail, 'SharedDataPlugin', 'isInitialized', []);
  };
  
  SharedDataPlugin.prototype.initialize = function (succ, fail, data) {
      cordova.exec(succ, fail, 'SharedDataPlugin', 'initialize', [data]);
  };
  
  SharedDataPlugin.prototype.getItem = function (succ, fail, key) {
      cordova.exec(succ, fail, 'SharedDataPlugin', 'getItem', [key]);
  };

  SharedDataPlugin.prototype.setItem = function (succ, fail, key, value) {
      cordova.exec(succ, fail, 'SharedDataPlugin', 'setItem', [key, value]);
  };

  SharedDataPlugin.prototype.removeItem = function (succ, fail, key) {
      cordova.exec(succ, fail, 'SharedDataPlugin', 'removeItem', [key]);
  };

  if (!window.plugins) {
      window.plugins = {};
  }

  var instance = new SharedDataPlugin();
  if (!window.plugins.SharedDataPlugin) {
      window.plugins.SharedDataPlugin = instance;
  }

  module.exports = instance;

