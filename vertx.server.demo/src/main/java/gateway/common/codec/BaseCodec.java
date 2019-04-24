package gateway.common.codec;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

/**
 * MessageCodec其实是一个转换接口，如果在eventBus之间发送消息，我们发送的消息类型是S
 * 而接收方接收到的类型是R，就需要在这边用transform方法进行转换。
 * 发送自定义对象数据的话必须实现这个接口，普通数据类型不需要。
 * 
 * codec基础类，主要实现两个方法，返回名称和返回系统ID。
 * encodeToWire和decodeFromWire方法用于集群情况下，暂不处理
 * 其余codec只要是实现transform方法即可。
 * @author yangwg
 * @time 2018年6月13日  下午9:48:06
 * @param <R>  接收方接收到的消息类型
 * @param <S>  发送方发出的消息类型
 */
public abstract class BaseCodec<R, S> implements MessageCodec<S, R>{
	
	@Override
	public  void encodeToWire(Buffer buffer, S s){
	}

	@Override
	public  R decodeFromWire(int pos, Buffer buffer){
		return null;
	}

	@Override
	public String name() {
		return this.getClass().getSimpleName();
	}

	@Override
	public byte systemCodecID() {
		return -1;
	}

}
