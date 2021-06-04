package jisaku_jpa.dbConnection;

import java.sql.Connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jisaku_jpa.dbConfigReader.DBConfig;

/**
 * DBAccessのテストクラス
 *
 * @author okamotoyuuma
 * @version 2.0.0
 * @version 2021.04.09
 *
 */
public class DBAccessTest {
	//DBConfigクラスの生成
	//#------
	//#   DBとのコネクションをテストする際は、ここを編集してください。
	//#------
			DBConfig dc = new DBConfig("oracle.jdbc.OracleDriver", //driver名
										"jdbc:oracle:thin:@localhost:1521/taskappdatabase.localdomain", //url
										"TASKAPP_ADMIN", //user名
										"password", //pass
										"", //shema(空欄でOK)
										5, //Connectionをはる数
										"taskappdatabase.localdomain", //DB名
										"OracleDB" //DBの種類(OracleDB, MyDQL)
								  	);
	@Test
	public void コネクションが生成される() {
		//テスト対象クラス
		@SuppressWarnings("resource")
		DBAccess da = new DBAccess(dc);

		Connection conn = da.getConnection();
		Assertions.assertNotNull(conn);
	}

	@Test
	public void コネクションが破棄される() {
		//テスト対象クラス
		DBAccess da = new DBAccess(dc);
		da.close();
	}

}
