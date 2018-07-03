package gateway.common.dao;

public interface DataBase {
	//建立连接
	void connect();
	//重新连接
	void reConnect();
	//关闭连接
	void disConnect();
	//初始化数据库参数
	void initOptions();
}
