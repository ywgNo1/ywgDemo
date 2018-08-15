package gateway.common;

import java.io.File;

public class GateWayConstants {
	
	//配置文件
	public static final String BASE_PATH="";
	public static final String CONFIG_PATH=BASE_PATH+"src/main/resources";
	public static final String CONFIG_NAME="config.json";
	
	public static final String CONFIG_FILE=CONFIG_PATH+File.separator+CONFIG_NAME;
	
	//配置文件中的参数
	public static final int WEBSERVER_DEFAULT_PORT=8080;
	public static final String WEBSERVER="webServer";
	public static final String PORT="port";
	public static final String REDIS="redis";
	public static final String REDIS_HOST="host";
	public static final String REDIS_ENCODING="encoding";
	public static final String REDIS_PORT="port";
	public static final String REDIS_AUTH="auth";
	public static final String REDIS_SELECT="select";
	
	public static final String JDBC="jdbc";
	
	public static final String ROUTER="router";
	
	
	
	

}
