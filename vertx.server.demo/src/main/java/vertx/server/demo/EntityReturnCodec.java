package vertx.server.demo;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class EntityReturnCodec implements MessageCodec<EntityReturn, EntityReturn>{

	@Override
	public void encodeToWire(Buffer buffer, EntityReturn s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityReturn decodeFromWire(int pos, Buffer buffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityReturn transform(EntityReturn s) {
		return s;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "EntityReturn";
	}

	@Override
	public byte systemCodecID() {
		// TODO Auto-generated method stub
		return -1;
	}

}
