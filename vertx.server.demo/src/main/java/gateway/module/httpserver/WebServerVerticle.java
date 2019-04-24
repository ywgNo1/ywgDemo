package gateway.module.httpserver;

import gateway.common.verticle.BaseVerticleImpl;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;

public class WebServerVerticle extends BaseVerticleImpl{
	private int port;
	
	  @Override
	  public void init(Vertx vertx, Context context) {
		  super.init(vertx, context);
		  this.port=config.getWebServerPort();
	  }
	
	
	  @Override
	  public void start(Future<Void> startFuture) throws Exception {
		HttpServer server=vertx.createHttpServer();
		initRouter();
		server.requestHandler(getRouter()::accept);
		server.requestHandler(new Handler<HttpServerRequest>() {
			
			@Override
			public void handle(HttpServerRequest event) {
				getRouter().accept(event);
				
			}
		});
		server.listen(port);
		super.start(startFuture);
		
	  }
	  
	  public void initRouter(){
		  //在这边注册一些基本的路由 TODO
	  }

	  
	  @Override
	  public void stop(Future<Void> stopFuture) throws Exception {
	    stop();
	    stopFuture.complete();
	  }
}
