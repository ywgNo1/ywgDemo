package vertx.server.demo;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;

public class EntityCodec implements MessageCodec<Entity, EntityReturn>{

	@Override
	public void encodeToWire(Buffer buffer, Entity s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public EntityReturn decodeFromWire(int pos, Buffer buffer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityReturn transform(Entity s) {
		EntityReturn return1=new EntityReturn();
		return1.setId(s.getMsg());
		return1.setMsg(s.getId());
		return return1;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "Entity";
	}

	@Override
	public byte systemCodecID() {
		// TODO Auto-generated method stub
		return -1;
	}

	

}
