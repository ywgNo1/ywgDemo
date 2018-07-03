package vertx.client.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.ConnectException;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
import io.vertx.redis.impl.RedisClientImpl;

public class RedisVerticle extends AbstractVerticle{
	private RedisClient redis=null;
  @Override
  public void start(Future<Void> startFuture) throws Exception {
	  RedisOptions config = new RedisOptions();
	  //config.setPort(660);
	  
	  redis =RedisClient.create(vertx,config);
	  

	  redis.get("s", res -> {
		  if (res.succeeded()) {
		    
		  }else {
			System.out.println(getStack((Exception)res.cause()));
		}
		});
	  redis.set("s", "key",res -> {
		  if (res.succeeded()) {
		    
		  }else {
			System.out.println(getStack((Exception)res.cause()));
		}
		});
	  
  }
	public String getStack(Exception e){
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		e.printStackTrace(pw);
		String exceptionStack=sw.getBuffer().toString();
		try {
			sw.close();
			pw.close();
		} catch (IOException e1) {
			
		}
		return exceptionStack;
	}
}
