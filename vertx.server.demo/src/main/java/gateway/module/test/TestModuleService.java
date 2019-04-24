package gateway.module.test;

import gateway.common.Config;
import gateway.common.service.RedisService;
import io.vertx.core.Vertx;
import io.vertx.ext.web.RoutingContext;

public class TestModuleService extends RedisService{

	public TestModuleService(Vertx vertx, Config config) {
		super(vertx, config);
	}

	
	public void get(RoutingContext routingContext){
		String key=routingContext.request().getParam("key");
		getCon().get(key, res->{
			String result="the key is not exist";
			if (res!=null) {
				result=res;
			}
			routingContext.response().putHeader("content-type", "text/html").end(result); 
		});
	}
	
	public void set(RoutingContext routingContext){
		String key=routingContext.request().getParam("key");
		String value=routingContext.request().getParam("value");
		getCon().set(key, value,res->{
			String result="success";
			if (res!=true) {
				result="fail";
			}
			routingContext.response().putHeader("content-type", "text/html").end(result); 
		});
	}
	
	public void del(RoutingContext routingContext){
		String key=routingContext.request().getParam("key");
		getCon().delete(key,null);
	}
}
