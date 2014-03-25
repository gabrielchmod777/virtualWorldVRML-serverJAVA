package ro.utcluj.learning3d.server;


public interface CommandTypeDependentInterpreter {
	public static final int XML_FORMAT = 1;
	public static final int PALIN_TEXT_FORMAT = 2;
	public void prepareResponse(ServerCommand execCmd, int format);
	String getInterpretedResult();
}
