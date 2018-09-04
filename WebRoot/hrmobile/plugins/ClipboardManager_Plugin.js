

var ClipboardManager = { 
	copy:function(str, successCallback, failureCallback) {
		cordova.exec(successCallback, failureCallback, "ClipboardManager", "copy", [str]);
	},
	paste:function(successCallback, failureCallback) {
		cordova.exec(successCallback, failureCallback, "ClipboardManager", "paste", []);
	}

}


