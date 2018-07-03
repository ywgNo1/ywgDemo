package gateway.module.test;

import gateway.common.Config;
import gateway.common.service.JdbcService;
import gateway.common.service.RedisService;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class TestJdbcService extends JdbcService{

	public TestJdbcService(Vertx vertx, Config config) {
		super(vertx, config);
	}

	
	public void get(RoutingContext routingContext){
		String key=routingContext.request().getParam("key");
		getCon().con();
	}
}
