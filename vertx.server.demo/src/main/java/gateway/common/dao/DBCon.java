package gateway.common.dao;

import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public abstract class DBCon implements DataBase{
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	protected Vertx vertx;
	protected JsonObject config;
	
	public DBCon(Vertx vertx,JsonObject config){
		this.vertx=vertx;
		this.config=config;
		initOptions();
		connect();
	}
}
