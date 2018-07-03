package gateway.common.dao;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

public class JDBCCon extends DBCon{
	private JDBCClient client;
	public JDBCCon(Vertx vertx,JsonObject config) {
		super(vertx,config);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void connect() {
		client =JDBCClient.createShared(vertx, config, "MyDataSource");
		
	}
	
	public void con(){
		client.getConnection(new Handler<AsyncResult<SQLConnection>>() {
			
			@Override
			public void handle(AsyncResult<SQLConnection> event) {
				if (event.succeeded()) {
					JsonArray array=new JsonArray();
					//array.add("true");
				    SQLConnection connection = event.result();
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
