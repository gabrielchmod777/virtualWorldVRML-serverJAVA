function avatar(name, type) {
	this.name = name;
	this.type = type;

	this.talk = function(name, chatID ,text) {
	var toSend = "<client_plugin>./plugins/libAvatar.so</client_plugin><client_plugin_source>libAvatar.so</client_plugin_source><action>talk</action><other_user>"+name+"</other_user><avatar_type_id>"+this.type+"</avatar_type_id><chat_id>"+chatID+"</chat_id><say>"+text+"</say>";
	return toSend;
	
	}
	
	this.move = function(mx, my,  mz) {
		var toSend = "<client_plugin>libAvatar.so</client_plugin><client_plugin_source>http://localhost/plugins/libAvatar.so</client_plugin_source><action>move</action><other_user>"+name+"</other_user><avatar_type_id>"+this.type+"</avatar_type_id><x>"+mx+"</x><y>"+my+"</y><z>"+mz+"</z>";
		return toSend;
	}

	this.rotate = function(axis, degrees) {
		var toSend = "<client_plugin>libAvatar.so</client_plugin><client_plugin_source>http://localhost/plugins/libAvatar.so</client_plugin_source><action>rotate</action><other_user>"+name+"</other_user><avatar_type_id>"+this.type+"</avatar_type_id><axis>"+axis+"</axis><degrees>"+degrees+"</degrees>";
		return toSend;
	}
	
	
} 

function console() {

	this.echo = function(text) {
		var toSend = "<client_plugin>./plugins/libL3dConsole.so</client_plugin><client_plugin_source>null</client_plugin_source><action>console_echo</action><message>text</message>";
		return toSend;
	}

}
