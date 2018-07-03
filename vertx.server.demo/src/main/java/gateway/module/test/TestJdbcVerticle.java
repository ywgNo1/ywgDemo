package gateway.module.test;

import java.util.ArrayList;
import java.util.List;

import gateway.common.codec.RouteParam;
import gateway.common.verticle.ServiceVerticleImpl;
import io.vertx.core.Future;

public class TestJdbcVerticle extends ServiceVerticleImpl {
	
	  @Override
	  public void start(Future<Void> startFuture) throws Exception {
		super.start(startFuture);
	  }
	
	@Override
	public void ininService() {
		service=new TestJdbcService(vertx, config);
	}

	@Override
	public TestJdbcService getService() {
		return (TestJdbcService)service;
	}


	public List<RouteParam> getAllRoute() {
		List<RouteParam> list = new ArrayList<RouteParam>();
//		RouteParam setParam=new RouteParam();
//		setParam.setPath("/set");
//		setParam.setContextHandler(routingContext->{
//			getService().set(routingContext);
//		});
		
		RouteParam getParam=new RouteParam();
		getParam.setPath("/getjdbc");
		getParam.setContextHandler(routingContext->{
			getService().get(routingContext);
		});
		list.add(getParam);
//		list.add(setParam);
		return list;
	}
	
	
}
