package gateway.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import io.vertx.core.http.HttpMethod;
/**
 * 注册路由的方法的注解
 * @author yangwg
 * @time 2018年6月19日  下午2:17:04
 */
@Target({ElementType.METHOD})//方法
@Retention(RetentionPolicy.RUNTIME)//注解类型：运行时注解  
public @interface RouteMethod {
	HttpMethod method();
	String path();
}
