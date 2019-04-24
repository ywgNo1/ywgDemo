package gateway.common;

import io.vertx.core.Vertx;
import io.vertx.core.shareddata.LocalMap;
import io.vertx.core.shareddata.SharedData;
import io.vertx.ext.web.Router;

/**
 * 从vertx中获取共享的数据
 * @author yangwg
 * @time 2018年6月18日  下午2:11:45
 */
public class ShareDateUtil {
	
	/**
	 * 获取共享的路由器
	 * @time 2018年6月19日  下午2:20:09
	 * @param vertx
	 * @return
	 */
	public static Router getRouter(Vertx vertx){
		Router router;
	    SharedData sharedData=vertx.sharedData();
	    LocalMap<String, ShareData> map =sharedData.getLocalMap(GateWayConstants.WEBSERVER);
	    ShareData data=map.get(GateWayConstants.ROUTER);
	    if (data!=null) {
			router=(Router)data.getData();
		}else{
			router=Router.router(vertx);
			map.put(GateWayConstants.ROUTER, new ShareData(router));
		}
	    return router;
	}
}
