package gateway.common.verticle;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * 基础模块，包含一些基本方法：在eventBus中注册事件和调用事件，向webSocket发送请求。
 * @author yangwg
 * @time 2018年6月14日  上午11:22:53
 */
public interface BaseVerticle {
	
	//下面都是向通道中发送消息
	public void send(String address, String message);
	public void send(String address, JsonObject message);
	public <T> void send(String address, String message,Handler<T> replyHandler);
	public <T> void send(String address, JsonObject message, Handler<T> replyHandler);

	public void publish(String address, String message);
	public void publish(String address, JsonObject message);
	public <T> void publish(String address, String message,Handler<T> replyHandler);
	public <T> void publish(String address, JsonObject message,Handler<T> replyHandler);
	
	//向eventBus中注册事件和调用事件
	public <T> void  registryEvent(String address, Handler<Message<T>> handler);
	public void  registryEvent(String address);
	public void callEvent(String address, Object message);
	public <T> void callEvent(String address, Object message, Handler<AsyncResult<Message<T>>> replyHandler);
	public void callEvent(String address, Object message, DeliveryOptions options);
	public <T> void callEvent(String address, Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler);
	
	
}
