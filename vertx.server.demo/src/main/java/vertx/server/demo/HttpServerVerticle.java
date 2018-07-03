package vertx.server.demo;

import gateway.common.ShareData;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class HttpServerVerticle extends AbstractVerticle{
	@Override  
    public void start() throws Exception {  

	
        final Router router = Router.router(vertx);  
        
        
        Route route=router.route("/hello*");
        route.handler(routingcontxt->{
        	String aString=routingcontxt.request().getParam("id");
        	String bString=routingcontxt.request().getParam("msg");
        	HttpServerResponse response = routingcontxt.response();
        	response.setChunked(true);
        	System.out.println("route: "+aString+" "+bString);
        	routingcontxt.next();
        });
        
        
        Route route1=router.route("/hello/b");
        route1.handler(routingcontxt->{
        	String aString=routingcontxt.request().getParam("id");
        	String bString=routingcontxt.request().getParam("msg");
        	System.out.println("route1: "+aString+" "+bString);
        	Entity entity=new Entity();
        	entity.setId(aString);
        	entity.setMsg(bString);
        	vertx.eventBus().send("http", entity,
        		op->{
        			if (op.succeeded()) {
						String result=op.result().body().toString();
						routingcontxt.response().putHeader("content-type", "text/html").end(result); 
					}
        		});
        });
 
        HttpServer  server=vertx.createHttpServer();

	    server.requestHandler(new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest event) {
				router.accept(event);
			}
		});
	    server.listen(8080);
	    SharedData sharedData=vertx.sharedData();
	    LocalMap<String, ShareData> map =sharedData.getLocalMap("webServer");
	    map.put("router", new ShareData(router));
	    
	    LocalMap<String, ShareData> map1 =sharedData.getLocalMap("webServer");
	    ShareData data=map1.get("router");
	    
//	    EventBus eb = vertx.eventBus();
//    	eb.consumer("http", new Handler<Message<EntityReturn>>() {
//			@Override
//			public void handle(Message<EntityReturn> event) {
//				System.out.println("I have received a message: " + (event.body().toString()));
//				event.reply(event.body());
//				
//		        Route route3=router.route("/hell/abc");
//		        route3.handler(routingcontxt->{
//		        	String aString=routingcontxt.request().getParam("id");
//		        	String bString=routingcontxt.request().getParam("msg");
//		        	System.out.println("routeabc: "+aString+" "+bString);
//		        	routingcontxt.response().putHeader("content-type", "text/html").end("routeabc: "+aString+" "+bString); 
//		        });
//			}
//		});
    }  
}
