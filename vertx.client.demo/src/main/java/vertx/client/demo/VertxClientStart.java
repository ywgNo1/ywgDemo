package vertx.client.demo;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxClientStart {

	public static void main(String[] args) {
		startClient();
	}
	public static void startClient(){
        VertxOptions options = new VertxOptions();  
        options.setBlockedThreadCheckInterval(5000000); 
        Vertx vertx = Vertx.vertx(options); 
        //vertx.deployVerticle(new ClientWebSocket());
        vertx.deployVerticle(new RedisVerticle());
        
	}
}
