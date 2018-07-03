package gateway.common.codec;

/**
 * 数据传输时，如果不需要做任何处理，数据类型继承这个类即可，无需做任何操作。
 * @author yangwg
 * @time 2018年6月13日  下午10:11:44
 * @param <R>
 */
public abstract class BaseSameCodec<R> extends BaseCodec<R, R> {
	@Override
	public R transform(R r) {
		return r;
	}
}
