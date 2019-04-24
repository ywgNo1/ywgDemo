package vertx.server.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.SQLConnection;



public class Test {
	
	public void batchOneToManySave(Message<JsonObject> msg) {
		JsonObject sqlJson = msg.body();
		// 默认autoCommit为true
		boolean autoCommit = sqlJson.getBoolean("autoCommit", true);
		JsonArray sqlArray = sqlJson.getJsonArray("array", null);
		if (null == sqlArray || sqlArray.size() < 1) {
			JsonObject replyMsg = new JsonObject();
			replyMsg.put("resCode", 1).put("resMsg", "Can not execute batch, sqls is null!");
			reply(replyMsg, msg);
			return;
		}
		client.getConnection(res -> {
			if (res.succeeded()) {
				SQLConnection con = res.result();

				if (autoCommit) {
					con.setAutoCommit(autoCommit, auto -> {
						if (auto.failed()) {
							JsonObject replyMsg = new JsonObject();
							replyMsg.put("resCode", 1).put("resMsg", auto.cause().getMessage());
							reply(replyMsg, msg);
							con.close();
							return;
						}
					});
				}
//				Iterator<Object> it = sqlArray.iterator();
//				List<String> batch = new ArrayList<String>();
//				while (it.hasNext()) {
//					batch.add((String) it.next());
//				}
				JsonArray oneArray=sqlArray.getJsonArray(0);
				String sql=oneArray.getString(0);
				JsonArray pArray=oneArray.getJsonArray(1);
				con.updateWithParams(sql, pArray, rs -> {
					if (rs.succeeded()) {
						if (oneArray.size()>1) {
							JsonArray manyArray=sqlArray.getJsonArray(1);
							Integer i=0;
							for(i=0;i<manyArray.size();i++) {
								JsonArray many=manyArray.getJsonArray(i);
								String manysql=many.getString(0);
								JsonArray manyParam=many.getJsonArray(1);
								List<JsonArray> batchParam=manyParam.getList();
								con.batchWithParams(manysql, batchParam, res1->{
									JsonObject replyMsg = new JsonObject();
									if (res1.succeeded()) {
										if (!autoCommit) {
											con.commit(cm -> {
												if (cm.failed()) {
													logger.error("Commit update sql \"" + sql + "\" error," + cm.cause());
													con.rollback(rb -> {
														if (rb.failed()) {
															logger.error("Commit update sql \"" + sql + "\"error and rollback error,"
																	+ rb.cause());
															replyMsg.put("resCode", 1).put("resMsg", cm.cause());
															reply(replyMsg, msg);
															con.close();
															return;
														}
													});
													replyMsg.put("resCode", 1).put("resMsg", cm.cause());
													reply(replyMsg, msg);
													con.close();
													return;
												}
											});
										}
									}else {
										replyMsg.put("resCode", 1);
									}
									reply(replyMsg, msg);
									con.close();
								});
							}
							
						}else {
							JsonObject replyMsg = new JsonObject();
							replyMsg.put("resCode", 0);
							reply(replyMsg, msg);
							con.close();
						}
					}else {
						JsonObject replyMsg = new JsonObject();
						replyMsg.put("resCode", 1).put("resMsg", rs.cause().getMessage());
						reply(replyMsg, msg);
						con.close();
					}
				});
			}else {
				JsonObject replyMsg = new JsonObject();
				replyMsg.put("resCode", 1).put("resMsg", res.cause().getMessage());
				reply(replyMsg, msg);
			}
		});
	}
	@org.junit.Test
	public void showUser(){
        //数据库连接
        Connection connection = null;
        //预编译的Statement，使用预编译的Statement提高数据库性能
        PreparedStatement preparedStatement = null;
        //结果 集
        ResultSet resultSet = null;

        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");

            //通过驱动管理类获取数据库链接
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/esb", "root", "mysql123");
            //定义sql语句 ?表示占位符
            String sql = "select * from user where username = ?";
            //获取预处理statement
            preparedStatement = connection.prepareStatement(sql);

            //设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
            preparedStatement.setString(1, "王五");
            //向数据库发出sql执行查询，查询出结果集
            resultSet =  preparedStatement.executeQuery();

            preparedStatement.setString(1, "张三");
            resultSet =  preparedStatement.executeQuery();
            //遍历查询结果集
            while(resultSet.next()){
                System.out.println(resultSet.getString("id")+"  "+resultSet.getString("username"));
            }
            resultSet.close();
            preparedStatement.close();

            System.out.println("#############################");

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //释放资源
            if(resultSet!=null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }
}
