package gateway.common.dao;

import gateway.common.GateWayConstants;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.RedisClient;
import io.vertx.redis.RedisOptions;
public class RedisCon extends DBCon implements DataBase{
	private static RedisOptions options=null;
	private RedisClient client;
	public RedisCon(Vertx vertx,JsonObject config) {
		super(vertx,config);
	}

	@Override
	public void connect() {
		client=RedisClient.create(vertx,options);
	}

	@Override
	public void reConnect() {
		
		
	}

	@Override
	public void disConnect() {
		
		
	}
	
	public void get(String key,Handler<String> handler){
		client.get(key, res -> {
			String result;  
			if (res.succeeded()) {
				result=res.result();
			  }else {
				result=null;
				logger.error("error when get the key:"+key+"from redis",res.cause());
			}
			handler.handle(result);
		});
	}
	public void getByte(String key,Handler<byte[]> handler){
		client.getBinary(key, res -> {
			byte[] result;  
			if (res.succeeded()) {
				result=res.result().getBytes();
			  }else {
				result=null;
				logger.error("error when get the key:"+key+"from redis",res.cause());
			}
			handler.handle(result);
		});
	}
	
	public void set(String key,String value,Handler<Boolean> handler){
		client.set(key, value,res->{
			boolean flag=res.succeeded();
			handler.handle(flag);
			if (!flag) {
				logger.error("error when set the key:"+key+",value:"+value+" from redis",res.cause());
			}
		});
	}
	
	public void setByte(String key,byte[] bytes,Handler<Boolean> handler){
		client.setBinary(key, Buffer.buffer(bytes),res->{
			boolean flag=res.succeeded();
			if (handler!=null) {
				handler.handle(flag);
			}
			if (!flag) {
				logger.error("error when set the key:"+key+" from redis",res.cause());
			}
		});
	}
	
	@SuppressWarnings("unused")
	public void delete(String key,Handler<Boolean> handler){
		client.del(key, res->{
			boolean flag=res.succeeded();
			long num=res.result();
		});
	}
	
	@Override
	public void initOptions() {
		if (options!=null) {
			return;
		}
		options=new RedisOptions();
		String auth=config.getString(GateWayConstants.REDIS_AUTH);
		if (auth!=null&&auth.trim().length()!=0) {
			options.setAuth(auth);
		}
		String encoding=config.getString(GateWayConstants.REDIS_ENCODING);
		if (encoding!=null&&encoding.trim().length()!=0) {
			options.setEncoding(encoding);
		}
		String host=config.getString(GateWayConstants.REDIS_HOST);
		if (host!=null&&host.trim().length()!=0) {
			options.setHost(host);
		}
		Integer port=config.getInteger(GateWayConstants.REDIS_PORT);
		if (port!=null) {
			options.setPort(port);
		}
		Integer select=config.getInteger(GateWayConstants.REDIS_SELECT);
		if (select!=null) {
			options.setSelect(select);
		}
	}
}
