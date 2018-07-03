package vertx.client.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.WebSocket;
import io.vertx.ext.web.Router;

public class ClientWebSocket extends AbstractVerticle{
   
  private WebSocket websocket=null;
  
  @Override
  public void start(Future<Void> startFuture) throws Exception {
	HttpClient client=vertx.createHttpClient();

	client.websocket(8081, "localhost", "websocket1", websocket->{
		this.websocket=websocket;
		System.out.println("connect the webSocket");
		
	});
	
	
	Router router=Router.router(vertx);
	router.get("/ceshi").handler(routingContext->{
		String result=null;
		if (websocket==null) {
			result="the websocket is not connected";
		}else{
			String arg=routingContext.request().getParam("arg");
			Buffer buffer = Buffer.buffer().appendString("send an messgae:"+arg);
			websocket.writeBinaryMessage(buffer);
			result ="send message success";
		}
		routingContext.response().putHeader("content-type", "text/html").end(result); 
	});
	HttpServer server=vertx.createHttpServer();
	server.requestHandler(router::accept);
	server.listen(8082);
	
    startFuture.complete();
  }
}
