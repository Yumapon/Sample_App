package jisaku_jpa.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import jisaku_jpa.dbConnection.ConnectionPool;
import jisaku_jpa.exception.NoColumnValueException;
import jisaku_jpa.exception.NoDBAccessException;

/**
 * OracledbQueryクラスのテストクラス
 *
 * @author okamotoyuuma
 * @version 2.0.0
 * @version 2021.04.12
 */
public class OracledbQueryTest {

	@Test
	public void INSERT文を生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");}})
										  .build();
		Assertions.assertEquals("INSERT INTO Task_list (num ,client ) values (\'test\',\'test2\')", mq.createInsertSql(qi));
	}

	@Test
	public void UPDATE文を生成する() {
		//テストクラスの作成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
				  .setIdName("num")
				  .setColumnNames(new ArrayList<String>() {{add("num");add("client");add("pow");}})
				  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");put("pow", "test3");}})
				  .build();

		Assertions.assertEquals("UPDATE Task_list SET client = \'test2\',pow = \'test3\' WHERE num = \'test\'", mq.createUpdateSql(qi));
	}

	@Test
	public void 主キー検索のSELECT文を生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");}})
										  .build();
		Assertions.assertEquals("SELECT * FROM Task_list WHERE num = \'test\'", mq.createSelectSql(qi));
	}

	@Test
	public void 条件検索のSELECT文を生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  //.setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");}})
										  .build();
		Assertions.assertEquals("SELECT * FROM Task_list WHERE num = \'test\' AND client = \'test2\'", mq.createSelectSql(qi));
	}

	@Test(expected = NoColumnValueException.class)
	public void 主キー検索のDELETE文作成時に例外をスローする() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .build();
		Assertions.assertEquals("SELECT * FROM Task_list WHERE num = \'test\';", mq.createDeleteSql(qi));
	}

	@Test
	public void 主キー検索のDELETE文を生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");}})
										  .build();
		Assertions.assertEquals("DELETE FROM Task_list WHERE num = \'test\'", mq.createDeleteSql(qi));
	}

	@Test
	public void 条件検索のDELETE文を生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");}})
										  .build();
		Assertions.assertEquals("DELETE FROM Task_list WHERE num = \'test\' AND client = \'test2\'", mq.createDeleteSql(qi));
	}

	@Test
	public void レコード数を確認するSQLを生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .build();
		Assertions.assertEquals("SELECT COUNT(*) FROM Task_list", mq.createCheckCountSql(qi));
	}

	@Test
	public void COUNTCHECK用SQLを生成する() {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", "test");put("client", "test2");}})
										  .build();
		Assertions.assertEquals("SELECT COUNT(*) FROM Task_list WHERE num = \'test\'", mq.createCheckRecordSql(qi));
	}

	@Test
	public void 更新かけてみる() throws NoDBAccessException {
		//テストクラスの生成
		OracledbQuery mq = new OracledbQuery();

		//事前にTransactionの取得
		ConnectionPool cp = ConnectionPool.getInstance();//ConnectionPoolを取得
		cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		QueryInfo qi = QueryInfo.builder().setTableName("Task_list")
										  .setIdName("num")
										  .setColumnNames(new ArrayList<String>() {{add("num");add("deadline");add("name");add("content");add("client");}})
										  .setColumnValues(new HashMap<String, String>() {{put("num", UUID.randomUUID().toString());put("deadline", "05-06-07");put("name", "test");put("content", "test");put("client", "test");}})
										  .build();
		//SQL作成
		String sql = mq.createInsertSql(qi);

		//これは自分で確認(自動コミットをONにしないと、DB側に反映されない)
		int i = mq.executeUpdate(sql);
		Assertions.assertEquals(1, i);
	}

}
