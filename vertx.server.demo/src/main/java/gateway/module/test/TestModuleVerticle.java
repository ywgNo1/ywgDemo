package gateway.module.test;

import gateway.common.RouteMethod;
import gateway.common.RoutePath;
import gateway.common.verticle.ServiceVerticleImpl;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

@RoutePath("/gateway/test")
public class TestModuleVerticle extends ServiceVerticleImpl {
	
	  @Override
	  public void start(Future<Void> startFuture) throws Exception {
		super.start(startFuture);
		
	  }
	
	@Override
	public void ininService() {
		service=new TestModuleService(vertx, config);
	}

	@Override
	public TestModuleService getService() {
		return (TestModuleService)service;
	}


	@RouteMethod(path="/get",method=HttpMethod.GET)
	public void get(RoutingContext routingContext){
		getService().get(routingContext);
	}
	
	@RouteMethod(path="/set",method=HttpMethod.GET)
	public void set(RoutingContext routingContext){
		getService().set(routingContext);
	}
	
	@RouteMethod(path="/del",method=HttpMethod.GET)
	public void del(RoutingContext routingContext){
		getService().del(routingContext);
	}
}
