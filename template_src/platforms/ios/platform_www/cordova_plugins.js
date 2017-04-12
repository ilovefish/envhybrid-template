cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "id": "envision-plugin-shareddata.SharedDataPlugin",
        "file": "plugins/envision-plugin-shareddata/www/SharedDataPlugin.js",
        "pluginId": "envision-plugin-shareddata",
        "clobbers": [
            "window.plugins.SharedDataPlugin"
        ]
    },
    {
        "id": "envision-plugin-shellrouter.ShellRouterPlugin",
        "file": "plugins/envision-plugin-shellrouter/www/ShellRouterPlugin.js",
        "pluginId": "envision-plugin-shellrouter",
        "clobbers": [
            "window.plugins.ShellRouterPlugin"
        ]
    },
    {
        "id": "envision-plugin-log.LoggingPlugin",
        "file": "plugins/envision-plugin-log/www/LoggingPlugin.js",
        "pluginId": "envision-plugin-log",
        "clobbers": [
            "window.plugins.LoggingPlugin"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "cordova-plugin-whitelist": "1.3.2",
    "envision-plugin-shareddata": "1.0.0",
    "envision-plugin-shellrouter": "1.0.0",
    "envision-plugin-envcontext": "1.0.0",
    "envision-plugin-log": "1.0.0"
};
// BOTTOM OF METADATA
});