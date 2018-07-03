package gateway.common.codec;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.RoutingContext;

public class RouteParam extends BaseSameCodec<RouteParam>{
	private String path;
	private HttpMethod method=HttpMethod.GET;
	private Handler<RoutingContext> contextHandler;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public HttpMethod getMethod() {
		return method;
	}
	public void setMethod(HttpMethod method) {
		this.method = method;
	}
	public Handler<RoutingContext> getContextHandler() {
		return contextHandler;
	}
	public void setContextHandler(Handler<RoutingContext> contextHandler) {
		this.contextHandler = contextHandler;
	}
}
