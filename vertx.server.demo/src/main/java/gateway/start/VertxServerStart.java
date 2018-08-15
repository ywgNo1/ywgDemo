package gateway.start;

import java.io.IOException;

import gateway.common.codec.RouteParam;
import gateway.module.httpserver.WebServerVerticle;
import gateway.module.test.TestJdbcVerticle;
import gateway.module.test.TestModuleVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxServerStart{
	private Vertx vertx;
    public static void main(String[] args) throws Exception {  
        new VertxServerStart().start();
    }
    public void start() throws InterruptedException{
    	startVertx();
    	registerDefaultCodec();
    	deployWebServer();
    	deployVerticle();
    }
    public void startVertx(){
    	VertxOptions options = new VertxOptions();  
        options.setBlockedThreadCheckInterval(5000000);
        vertx = Vertx.vertx(options);
    }
   public void registerDefaultCodec(){
       vertx.eventBus().registerDefaultCodec(RouteParam.class, new RouteParam());
    }
    
    public void deployWebServer() throws InterruptedException{
	    vertx.deployVerticle(new WebServerVerticle());
	    Thread.sleep(1000);
    }
    
    public void deployVerticle(){
    	vertx.deployVerticle(new TestModuleVerticle());
    	vertx.deployVerticle(new TestJdbcVerticle());
    }
}
