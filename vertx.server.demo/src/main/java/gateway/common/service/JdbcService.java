package gateway.common.service;

import gateway.common.Config;
import gateway.common.dao.JDBCCon;
import io.vertx.core.Vertx;

/**
 * 初始化jdbc的服务类，暂时不使用。
 * @author yangwg
 * @time 2018年6月19日  下午3:09:44
 */
public class JdbcService extends ServiceImpl {

	public JdbcService(Vertx vertx, Config config) {
		super(vertx, config);
	}

	@Override
	public void initDBCon(Vertx vertx, Config config) {
		con=new JDBCCon(vertx, config.getJdbc());
	}
	
	@Override
	public JDBCCon getCon(){
		return (JDBCCon)con;
	}
	
	
}
