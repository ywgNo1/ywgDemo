package gateway.common.dao;

import io.vertx.core.json.JsonObject;

/**
 * Postgresql和Mysql数据库通用配置文件
 *
 */
public class PostgresqlMysqlConfig extends JsonObject {

	private static final String MYSQL_FLAG = "mysql_flag";
	private static final String HOST = "host";
	private static final String PORT = "port";
	private static final String MAX_POOL_SIZE = "maxPoolSize";
	private static final String USER_NAME = "username";
	private static final String PWD = "password";
	private static final String DATABASE = "database";
	private static final String CHARSET = "charset";
	private static final String QUERY_TIMEOUT = "queryTimeout";
	private static final String SSL_MODE = "sslMode";
	private static final String SSL_ROOT_CERT = "sslRootCert";

	/**
	 * mysql标志
	 * <li>true:mysql数据库</li>
	 * <li>false:Postgresql数据库</li>
	 */
	private boolean mysql_flag = true;
	
	/**
	 * 主机地址
	 */
	private String host = "localhost";

	/**
	 * 端口
	 */
	private int port;

	/**
	 * 连接池最大连接数，默认10
	 */
	private int maxPoolSize = 10;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 数据库名
	 */
	private String database;

	/**
	 * 字符集，默认UTF-8
	 */
	private String charset = "UTF-8";

	/**
	 * 查询超时时间，默认10000(10s)
	 */
	private long queryTimeout = 10000;

	/**
	 * ss模式，默认prefer
	 * <li>"disable":only try a non-SSL connection
	 * <li>
	 * <li>"prefer":first try an SSL connection; if that fails, try a non-SSL
	 * connection
	 * <li>
	 * <li>"require":only try an SSL connection, but don’t verify Certificate
	 * Authority
	 * <li>
	 * <li>"verify-ca":only try an SSL connection, and verify that the server
	 * certificate is issued by a trusted certificate authority (CA)
	 * <li>
	 * <li>"verify-full":only try an SSL connection, verify that the server
	 * certificate is issued by a trusted CA and that the server host name matches
	 * that in the certificate
	 * <li>
	 */
	private String sslMode = "prefer";

	/**
	 * 证书地址
	 * <p>
	 * path to file with certificate
	 * </p>
	 */
	private String sslRootCert;

	/**
	 * 空构造方法
	 */
	public PostgresqlMysqlConfig() {

	}

	/**
	 * 构造方法
	 * 
	 * @param config
	 */
	public PostgresqlMysqlConfig(JsonObject config) {
		this.mysql_flag = config.getBoolean(MYSQL_FLAG, true);
		this.put(MYSQL_FLAG, mysql_flag);
		this.host = config.getString(HOST, "localhost");
		this.put(HOST, host);
		this.port = config.getInteger(PORT);
		this.put(PORT, port);
		this.maxPoolSize = config.getInteger(MAX_POOL_SIZE, 10);
		this.put(MAX_POOL_SIZE, maxPoolSize);
		this.username = config.getString(USER_NAME);
		this.put(USER_NAME, username);
		this.password = config.getString(PWD);
		this.put(PWD, password);
		this.database = config.getString(DATABASE);
		this.put(DATABASE, database);
		this.charset = config.getString(CHARSET);
		this.put(CHARSET, charset);
		this.queryTimeout = config.getLong(QUERY_TIMEOUT, 10000L);
		this.put(QUERY_TIMEOUT, queryTimeout);
		this.sslMode = config.getString(SSL_MODE, "prefer");
		this.put(SSL_MODE, sslMode);
		this.sslRootCert = config.getString(SSL_ROOT_CERT);
		this.put(SSL_ROOT_CERT, sslRootCert);
	}
	
	/**
	 * 构造方法
	 * @param mysql_flag true:mysql数据库;false:postgresql数据库
	 * @param host 主机地址
	 * @param port 端口
	 * @param maxPoolSize 连接池最大连接数，默认10
	 * @param username 用户名
	 * @param password 密码
	 * @param database 数据库名
	 * @param charset 字符集，默认UTF-8
	 * @param queryTimeout 查询超时时间，默认10000(10s)
	 * @param sslMode ss模式，默认prefer
	 * @param sslRootCert 证书地址
	 */
	public PostgresqlMysqlConfig(boolean mysql_flag, String host, int port, int maxPoolSize, String username, String password,
			String database, String charset, Long queryTimeout, String sslMode, String sslRootCert) {
		this.mysql_flag = mysql_flag;
		this.put(MYSQL_FLAG, mysql_flag);
		this.host = host;
		this.put(HOST, host);
		this.port = port;
		this.put(PORT, port);
		this.maxPoolSize = maxPoolSize;
		this.put(MAX_POOL_SIZE, maxPoolSize);
		this.username = username;
		this.put(USER_NAME, username);
		this.password = password;
		this.put(PWD, password);
		this.database = database;
		this.put(DATABASE, database);
		this.charset = charset;
		this.put(CHARSET, charset);
		this.queryTimeout = queryTimeout;
		this.put(QUERY_TIMEOUT, queryTimeout);
		this.sslMode = sslMode;
		this.put(SSL_MODE, sslMode);
		this.sslRootCert = sslRootCert;
		this.put(SSL_ROOT_CERT, sslRootCert);
	}
	
	public boolean isMysql_flag() {
		return mysql_flag;
	}

	public void setMysql_flag(boolean mysql_flag) {
		this.mysql_flag = mysql_flag;
		this.put(MYSQL_FLAG, mysql_flag);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
		this.put(HOST, host);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
		this.put(PORT, port);
	}

	public int getMaxPoolSize() {
		return maxPoolSize;
	}

	public void setMaxPoolSize(int maxPoolSize) {
		this.maxPoolSize = maxPoolSize;
		this.put(MAX_POOL_SIZE, maxPoolSize);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
		this.put(USER_NAME, username);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
		this.put(PWD, password);
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
		this.put(DATABASE, database);
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
		this.put(CHARSET, charset);
	}

	public long getQueryTimeout() {
		return queryTimeout;
	}

	public void setQueryTimeout(long queryTimeout) {
		this.queryTimeout = queryTimeout;
		this.put(QUERY_TIMEOUT, queryTimeout);
	}

	public String getSslMode() {
		return sslMode;
	}

	public void setSslMode(String sslMode) {
		this.sslMode = sslMode;
		this.put(SSL_MODE, sslMode);
	}

	public String getSslRootCert() {
		return sslRootCert;
	}

	public void setSslRootCert(String sslRootCert) {
		this.sslRootCert = sslRootCert;
		this.put(SSL_ROOT_CERT, sslRootCert);
	}
	
}
