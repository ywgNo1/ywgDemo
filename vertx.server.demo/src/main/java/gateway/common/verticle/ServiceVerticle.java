package gateway.common.verticle;

import gateway.common.service.Service;

/**
 * 服务模块接口。按照模块-服务-持久层，三层应用模式创建的模块必须实现这个接口。
 * 如果业务比较单一，直接继承BaseVerticleImpl即可。
 * @author yangwg
 * @time 2018年6月14日  上午11:24:25
 */
public interface ServiceVerticle {
	//初始化服务
	void ininService();
	
	//获得服务实例
	Service getService();
}
