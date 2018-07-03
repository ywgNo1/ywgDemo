package vertx.server.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class VertxHttpVerticle extends AbstractVerticle{
	  
    @Override  
    public void start() throws Exception {  
    	 final Router router = Router.router(vertx);  
    	Route route1=router.route("/hello/b");
        route1.handler(routingcontxt->{
        	String aString=routingcontxt.request().getParam("id");
        	String bString=routingcontxt.request().getParam("msg");
        	System.out.println("route1: "+aString+" "+bString);
        	
    	    SharedData sharedData=vertx.sharedData();
    	    LocalMap<String, Router> map =sharedData.getLocalMap("localMap");
    	    Router router1=map.get("router");
	        Route route3=router1.route("/hell/abc");
	        route3.handler(routingcontxt1->{
	        	String aString1=routingcontxt1.request().getParam("id");
	        	String bString1=routingcontxt1.request().getParam("msg");
	        	System.out.println("routeabc: "+aString1+" "+bString1);
	        	routingcontxt1.response().putHeader("content-type", "text/html").end("routeabc: "+aString1+" "+bString1); 
	        });
//        	Entity entity=new Entity();
//        	entity.setId(aString);
//        	entity.setMsg(bString);
//        	vertx.eventBus().send("http", entity,
//        		op->{
//        			if (op.succeeded()) {
//						String result=op.result().body().toString();
//						routingcontxt.response().putHeader("content-type", "text/html").end(result); 
//					}
//        		});
        });
        
        HttpServer  server=vertx.createHttpServer();

	    server.requestHandler(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest event) {
				router.accept(event);
			}
		});
	    server.listen(8082);

//    	EventBus eb = vertx.eventBus();
//    	eb.consumer("http1", new Handler<Message<EntityReturn>>() {
//			@Override
//			public void handle(Message<EntityReturn> event) {
//				System.out.println("I have received a message: " + (event.body().toString()));
//				event.reply(event.body());
//			}
//		});
    }  
	
}
