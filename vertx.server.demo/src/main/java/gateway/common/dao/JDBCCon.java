package gateway.common.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import org.junit.Test;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;

public class JDBCCon extends DBCon{
	private JDBCClient client;
	private SQLClient sqlClient;
	public JDBCCon(Vertx vertx,JsonObject config) {
		super(vertx,config);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect() {
		client =JDBCClient.createShared(vertx, config, "MyDataSource");
		
		PostgresqlMysqlConfig config = new PostgresqlMysqlConfig(true, "localhost", 3306, 10, "root",
				"mysql123", "esb", "UTF-8", 10000L, "prefer", null);
		sqlClient = MySQLClient.createNonShared(vertx, config);
		
	}
	@Test
	public void test() {
	}
	
	public void con1(){
		client.getConnection(new Handler<AsyncResult<SQLConnection>>() {
			
			@Override
			public void handle(AsyncResult<SQLConnection> event) {
				if (event.succeeded()) {
					JsonArray array=new JsonArray();
					//array.add("true");
				    SQLConnection connection = event.result();
				    
				    List<String> list=new ArrayList<>();
					list.add("insert into  teacher (pk, name) values ('1c10e0ef-366c-44cf-8d20-fd3aa71cf754', 'canglaoshi')");
					list.add("insert into  student (pk, name, teacher) values ('51adfb52-f6e7-45d8-a472-05e7576d74de', 'ceshi0', '1c10e0ef-366c-44cf-8d20-fd3aa71cf754')");
					list.add("insert into  student (pk, name, teacher) values ('5d3b5822-9747-4c95-ac66-8e19a71c9d36', 'ceshi1', '1c10e0ef-366c-44cf-8d20-fd3aa71cf754')");
					list.add("insert into  student (pk, name, teacher) values ('4c1ff1df-0853-4fa9-946e-3e1edec71f09', 'ceshi2', '1c10e0ef-366c-44cf-8d20-fd3aa71cf754')");
					connection.batch(list,res1->{
							if (res1.succeeded()) {
								System.out.println("success");
							}else {
								System.out.println(res1.cause());
							}
						});
					
				    
				    connection.queryWithParams("SELECT * FROM eip_global_var where var_value = 'true'",array, res2 -> {
				      if (res2.succeeded()) {
				    	  ResultSet rs = res2.result();
				    	  
				    	  List<JsonObject> rows=rs.getRows();
				    	  for (JsonObject row : rows) {
				    		  Object ts=row.getValue("TS");
				    		  
				    		  
				    		  if (ts!=null) {
				    			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				    			  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    			  
				    			  df.setTimeZone(TimeZone.getTimeZone("UTC"));
				    			  try {
									System.out.println(df1.format(df.parse(ts.toString())));
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
				    			System.out.println(ts);
								System.out.println(ts.getClass());
							}
				    		

				    		}
				    	  rs.toJson();
				    	  rs.getNumRows();
				      }
				    });
				    
				    
				    
				  } else {
				    // Failed to get connection - deal with it
				  }
				
			}
		});
	}
	
	public void con(){
		sqlClient.getConnection(new Handler<AsyncResult<SQLConnection>>() {
			
			@Override
			public void handle(AsyncResult<SQLConnection> event) {
				if (event.succeeded()) {
					JsonArray array=new JsonArray();
					//array.add("true");
				    SQLConnection connection = event.result();
				    
				    List<String> list=new ArrayList<>();
					list.add("insert into  teacher (pk, name) values ('1c10e0ef-366c-44cf-8d20-fd3aa71cf754', 'canglaoshi')");
					list.add("insert into  student (pk, name, teacher) values ('51adfb52-f6e7-45d8-a472-05e7576d74de', 'ceshi0', '1c10e0ef-366c-44cf-8d20-fd3aa71cf754')");
					list.add("insert into  student (pk, name, teacher) values ('5d3b5822-9747-4c95-ac66-8e19a71c9d36', 'ceshi1', '1c10e0ef-366c-44cf-8d20-fd3aa71cf754')");
					list.add("insert into  student (pk, name, teacher) values ('4c1ff1df-0853-4fa9-946e-3e1edec71f09', 'ceshi2', '1c10e0ef-366c-44cf-8d20-fd3aa71cf754')");
					connection.batch(list,res1->{
							if (res1.succeeded()) {
								System.out.println("success");
							}else {
								System.out.println(res1.cause());
							}
						});
					
				    
				    connection.queryWithParams("SELECT * FROM eip_global_var where var_value = 'true'",array, res2 -> {
				      if (res2.succeeded()) {
				    	  ResultSet rs = res2.result();
				    	  
				    	  List<JsonObject> rows=rs.getRows();
				    	  for (JsonObject row : rows) {
				    		  Object ts=row.getValue("TS");
				    		  
				    		  
				    		  if (ts!=null) {
				    			  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
				    			  SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    			  
				    			  df.setTimeZone(TimeZone.getTimeZone("UTC"));
				    			  try {
									System.out.println(df1.format(df.parse(ts.toString())));
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}	
				    			System.out.println(ts);
								System.out.println(ts.getClass());
							}
				    		

				    		}
				    	  rs.toJson();
				    	  rs.getNumRows();
				      }
				    });
				    
				    
				    
				  } else {
				    // Failed to get connection - deal with it
				  }
				
			}
		});
	}
	@Override
	public void reConnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disConnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initOptions() {
		// TODO Auto-generated method stub
		
	}

}
