package gateway.common.verticle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import gateway.common.Config;
import gateway.common.RouteMethod;
import gateway.common.RoutePath;
import gateway.common.ShareDateUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Context;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
/**
 * 基础模块的实现类，实现了基本的操作。
 * 同时也继承了AbstractVerticle，重写了init方法，加载配置文件。
 * 实现了添加路由的接口，在start方法中完成了添加路由的操作。
 * 子类的话，如果重写start(Future<Void> startFuture)这个方法，必须方法体内调用父类的这个方法，即super.start(startFuture);。
 * 重写start()方法，不用操作。
 * @author yangwg
 * @time 2018年6月13日  下午8:44:11
 */
public abstract class BaseVerticleImpl extends AbstractVerticle implements BaseVerticle{
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	protected Config config;
	private Router router;
    @Override
    public void init(Vertx vertx, Context context) {
	    super.init(vertx, context);
	    router=ShareDateUtil.getRouter(vertx);
	    config=Config.getInstance();
    }
    
    @Override
    public void start(Future<Void> startFuture) throws Exception {
      start();
      addRoute();
      startFuture.complete();
    }
	public <T> void  registryEvent(String address, Handler<Message<T>> handler) {
		vertx.eventBus().consumer(address, handler);
	}
	
	public void  registryEvent(String address) {
		vertx.eventBus().consumer(address);
	}
	

	public void callEvent(String address, Object message) {
		callEvent(address, message, new DeliveryOptions(), null);
	}
	
	public <T> void callEvent(String address, Object message, Handler<AsyncResult<Message<T>>> replyHandler) {
		callEvent(address, message, new DeliveryOptions(), replyHandler);
	}
	
	public void callEvent(String address, Object message, DeliveryOptions options) {
		callEvent(address, message, options, null);
	}
	
	public <T> void callEvent(String address, Object message, DeliveryOptions options, Handler<AsyncResult<Message<T>>> replyHandler) {
		vertx.eventBus().send(address,message, options, replyHandler);
	}
	
	  public void send(String address, String message) {
		    
	  }

	  public void publish(String address, String message) {
	    
	  }

	  public <T> void send(String address, String message,
	      Handler<T> replyHandler) {
	    
	  }

	  public <T> void publish(String address, String message,
	      Handler<T> replyHandler) {
	    
	  }

	  public void send(String address, JsonObject message) {
	    
	  }

	  public void publish(String address, JsonObject message) {
	   
	  }

	  public <T> void send(String address, JsonObject message,
	      Handler<T> replyHandler) {
	    
	  }

	  public <T> void publish(String address, JsonObject message,
	      Handler<T> replyHandler) {
	    
	  }
	  
	  /**
	   * 获取共享的路由
	   *
	   * @time 2018年6月18日  下午2:19:40
	   * @return
	   */
	  protected Router getRouter() {
		return router;
	}
	  /**
	   * 通过获取注解获得基础路由路径，然后通过判断方法的注解来获取注册路由的一些信息。
	   *
	   * @time 2018年6月19日  下午1:21:33
	   */
	  private void addRoute(){
		  Class<? extends BaseVerticleImpl> currentClass=this.getClass();
		  RoutePath pathInfo=(RoutePath)currentClass.getAnnotation(RoutePath.class);
		  String basicPath="";
		  if (pathInfo!=null) {
			basicPath=pathInfo.value();
		  }
	      Method[] methods = currentClass.getDeclaredMethods();
	      for (Method method : methods) {
	    	  method.setAccessible(true);
	          Annotation[] annotations = method.getDeclaredAnnotations();
	          for (Annotation annotation : annotations) {
	        	  if (RouteMethod.class.equals(annotation.annotationType())) {
					RouteMethod routeInfo=(RouteMethod)annotation;
					String path=checkRoutePath(basicPath, routeInfo.path());
		            Parameter[] parameters = method.getParameters();
		            for (int j = 0; j < parameters.length; j++) {
		                Parameter parameter = parameters[j];
		                Annotation[] paramAnnotations = parameter.getDeclaredAnnotations();
		                for (int k = 0; k < paramAnnotations.length; k++) {
		                    Annotation an = paramAnnotations[k];
		                    System.out.println(an);
		                }
		            }
					router.route()
						.path(path)
						.method(routeInfo.method())
						.handler(res->{
							try {
								method.invoke(this, res);
							} catch (Exception e) {
								logger.error("error occurs when invoke the route method,routePath is:"+routeInfo, e);
							}
						}
						);
				}
	          }
	      }
	  }
	  /**
	   * 校验并修正一下路径中可能出现的错误，
	   * 比如基础路径的结尾和路由路径开头都没有加“/”,就要加上一个。
	   * 或者都加了，去掉一个。
	   * @time 2018年6月19日  下午1:28:27
	   * @param basicPath
	   * @param routePath
	   * @return 真正的路由地址
	   */
	  public String checkRoutePath(String basicPath,String routePath){
		  String realPath;
		  if (!basicPath.endsWith("/")&&!routePath.startsWith("/")) {
			realPath=basicPath+"/"+routePath;
		  }else{
			realPath=basicPath+routePath;
		  }
		  return realPath.replaceAll("//", "/");
	  }
}
