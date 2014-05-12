function avatar(name) {
	this.name = name;

	this.talk = function(name, chatID ,text) {
	var toSend = "<client_plugin>./plugins/libAvatar.so</client_plugin><client_plugin_source>null</client_plugin_source><action>talk</action><other_user>"+name+"</other_user><chat_id>"+chatID+"</chat_id><say>"+text+"</say>";
	return toSend;
	
	}
	
	this.move = function(mx, my,  mz) {
		var toSend = "<client_plugin>./plugins/libAvatar.so</client_plugin><client_plugin_source>null</client_plugin_source><action>move</action><other_user>"+name+"</other_user><x>"+mx+"</x><y>"+my+"</y><z>"+mz+"</z>";
		return toSend;
	}

	this.rotate = function(axis, degrees) {
		var toSend = "<client_plugin>./plugins/libAvatar.so</client_plugin><client_plugin_source>null</client_plugin_source><action>rotate</action><other_user>"+name+"</other_user><axis>"+axis+"</axis><degrees>"+degrees+"</degrees>";
		return toSend;
	}
	
	
} 

function console() {

	this.echo = function(text) {
		var toSend = "<client_plugin>./plugins/libL3dConsole.so</client_plugin><client_plugin_source>null</client_plugin_source><action>console_echo</action><message>text</message>";
		return toSend;
	}

}
