package vertx.server.demo;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;

public class ServerWebSocket extends AbstractVerticle{
	  @Override
	  public void start(Future<Void> startFuture) throws Exception {
	    HttpServer server =vertx.createHttpServer();
	    server.websocketHandler(serverWebSocket-> {
	    	System.out.println(serverWebSocket.path());
			System.out.println("server:conneted");
			
			serverWebSocket.binaryMessageHandler(buffer->{
				System.out.println("buffer:"+buffer);
			});
			serverWebSocket.frameHandler(webSocketFrame->{
				
				Buffer fBuffer=webSocketFrame.binaryData();
				System.out.println("frame:"+fBuffer);
			});
		});
	    server.listen(8081);
	    
	    vertx.eventBus().consumer("websocket", message->{
	    	System.out.println("server receive a messgae");
		});
	    startFuture.complete();
	  }
	  
	 
}
