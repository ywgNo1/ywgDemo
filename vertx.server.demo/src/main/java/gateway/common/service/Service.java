package gateway.common.service;

import gateway.common.Config;
import gateway.common.dao.DBCon;
import io.vertx.core.Vertx;

public interface Service {
	/**
	 * 服务中初始化数据库客户端
	 *
	 * @time 2018年6月14日  上午10:09:41
	 */
	void initDBCon(Vertx vertx,Config config);
	
	/**
	 * 获取数据库连接
	 *
	 * @time 2018年6月19日  下午2:55:19
	 * @return
	 */
	DBCon getCon();
}
