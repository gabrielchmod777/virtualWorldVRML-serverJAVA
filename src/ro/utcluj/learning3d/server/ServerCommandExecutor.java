package ro.utcluj.learning3d.server;

import ro.utcluj.learning3d.executives.XmlWrappedResponse;

public class ServerCommandExecutor {

	public static String execute(String type, ServerCommand execCmd) {

		if(!type.equals("")) {
			try {
	            ClassLoader myClassLoader = L3DServerManager.class.getClassLoader();
	            String classNameToBeLoaded = execCmd.executor;
	            Class<?> myClass = myClassLoader.loadClass(classNameToBeLoaded);
	            Object executor = myClass.newInstance();
	            ((CommandTypeDependentInterpreter)executor).prepareResponse(execCmd, CommandTypeDependentInterpreter.XML_FORMAT);
				return ((CommandTypeDependentInterpreter)executor).getInterpretedResult();
	        } catch (SecurityException e) {
	        	return XmlWrappedResponse.exception("Executor "+execCmd.executor+". Security Exception!");
	        } catch (IllegalArgumentException e) {
	        	return XmlWrappedResponse.exception("Executor "+execCmd.executor+". Illegal arguments exception!");
	        } catch (ClassNotFoundException e) {
	        	return XmlWrappedResponse.exception("Executor "+execCmd.executor+". Class not found exception!");
	        } catch (InstantiationException e) {
	        	return XmlWrappedResponse.exception("Executor "+execCmd.executor+". Instantiation exception!");
	        } catch (IllegalAccessException e) {
	        	return XmlWrappedResponse.exception("Executor "+execCmd.executor+". Illegal access exception!");
	        } 
		} else {
			return XmlWrappedResponse.exception("Not an valid command type!");
		}

	}

}
