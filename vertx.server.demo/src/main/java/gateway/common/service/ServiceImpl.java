package gateway.common.service;

import gateway.common.Config;
import gateway.common.dao.DBCon;
import io.vertx.core.Vertx;

public abstract class ServiceImpl implements Service{
	protected DBCon con;
	
	public  ServiceImpl(Vertx vertx,Config config) {
		initDBCon(vertx,config);
	}
}
