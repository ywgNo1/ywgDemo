package vertx.server.demo;

import java.io.IOException;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class VertxServerStart{  
	  
    public static void main(String[] args) throws IOException {  
        runExample();  
    }  
  
   
  
    public static void runExample() {  
        VertxOptions options = new VertxOptions();  
        options.setBlockedThreadCheckInterval(5000000);
//        DropwizardMetricsOptions metricsOptions = new DropwizardMetricsOptions();
//        metricsOptions.setEnabled(true);
//        options.setMetricsOptions(metricsOptions);
        

        // Vert.x实例是vert.x api的入口点，我们调用vert.x中的核心服务时，均要先获取vert.x实例，  
        // 通过该实例来调用相应的服务，例如部署verticle、创建http server  
        Vertx vertx = Vertx.vertx(options); 
        vertx.eventBus().registerDefaultCodec(Entity.class, new EntityCodec());
        vertx.eventBus().registerDefaultCodec(EntityReturn.class, new EntityReturnCodec());
  
        vertx.deployVerticle(HttpServerVerticle.class.getName());
        vertx.deployVerticle(new VertxHttpVerticle());
        vertx.deployVerticle(new ServerWebSocket());
    }  
}
