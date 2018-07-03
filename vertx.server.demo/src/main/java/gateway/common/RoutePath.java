package gateway.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 模块类注解，相当于配置这个模块的路由前缀
 * @author yangwg
 * @time 2018年6月19日  下午2:17:33
 */
@Target({ElementType.TYPE})//作用域是类或者接口  
@Retention(RetentionPolicy.RUNTIME)//注解类型：运行时注解  
public @interface RoutePath {
	String value();
}
