package gateway.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class Config extends JsonObject{
	private static final Logger log = LoggerFactory.getLogger(Config.class.getName());

	private static Config instance =null;
	
	private Config(){
		JsonObject jsonObject=loadConfig();
		if (jsonObject!=null) {
		    this.mergeIn(jsonObject);	
		}
	}

	public static Config getInstance(){
		if (instance==null) {
			instance=new Config();
		}
		return instance;
	}
	
	@SuppressWarnings("resource")
	private JsonObject loadConfig(){
	    JsonObject conf = null;
	    Scanner scanner =null;
	    try{
		  scanner = new Scanner(new File(GateWayConstants.CONFIG_FILE)).useDelimiter("\\A");
		      try {
		        conf = new JsonObject(scanner.next());
		      } catch (DecodeException e) {
		        log.error("Configuration file " + GateWayConstants.CONFIG_FILE
		            + " does not contain a valid JSON object");
		        return null;
		      } finally {
		        scanner.close();
		      }
	    } catch (FileNotFoundException e) {
	      log.error(GateWayConstants.CONFIG_FILE+ " file not found!");
	      return null;
	    }
	    return conf;
	}
	
	public int getWebServerPort(){
		Integer port;
		try {
			port =this.getJsonObject(GateWayConstants.WEBSERVER).getInteger(GateWayConstants.PORT);
		} catch (Exception e) {
			port=GateWayConstants.WEBSERVER_DEFAULT_PORT;
		}
		if (port==null) {
			port=GateWayConstants.WEBSERVER_DEFAULT_PORT;
		}
		return port;
	}
	
	public JsonObject getRedis(){
		return this.getJsonObject(GateWayConstants.REDIS);
	}
	
	public JsonObject getJdbc(){
		return this.getJsonObject(GateWayConstants.JDBC);
	}
}
