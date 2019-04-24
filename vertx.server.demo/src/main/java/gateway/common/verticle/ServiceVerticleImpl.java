package gateway.common.verticle;

import gateway.common.service.Service;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
/**
 * 服务模块接口实现类。重写了AbstractVerticle的init方法，在最后执行initService方法。
 * @author yangwg
 * @param <T>
 * @time 2018年6月14日  上午11:26:00
 */
public abstract class ServiceVerticleImpl extends BaseVerticleImpl implements ServiceVerticle{
	protected Service service;
	
	@Override
	public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
		ininService();
	}	
}
