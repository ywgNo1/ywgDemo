package gateway.common.service;

import gateway.common.Config;
import gateway.common.dao.RedisCon;
import io.vertx.core.Vertx;

/**
 * 初始化redis的服务，暂时服务类都继承这个类
 * @author yangwg
 * @time 2018年6月19日  下午3:04:51
 */
public class RedisService extends ServiceImpl {

	public RedisService(Vertx vertx, Config config) {
		super(vertx, config);
	}

	@Override
	public void initDBCon(Vertx vertx, Config config) {
		con=new RedisCon(vertx,config.getRedis());
	}
	
	@Override
	public RedisCon getCon(){
		return (RedisCon)con;
	}
}
