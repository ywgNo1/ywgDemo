package vertx.server.demo;

public class EntityReturn {

	private String id;
	private String msg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public String toString(){
		return "EntityReturn	id:"+id+"	msg:"+msg;
	}
}
