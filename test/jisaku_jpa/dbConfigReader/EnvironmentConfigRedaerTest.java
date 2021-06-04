package jisaku_jpa.dbConfigReader;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * EnvironmentConfigReaderのテストクラス
 *
 * @author okamotoyuuma
 * @version 2.0.0
 * @version 2021.04.11
 */
public class EnvironmentConfigRedaerTest {

	@Test
	public void DBProfileの設定値を取得() {
		EnvironmentConfigReader reader = new EnvironmentConfigReader();
		DBConfig dc = reader.read();

		Assert.assertEquals(dc.getDriver(), "oracle.jdbc.OracleDriver");
		Assert.assertEquals(dc.getUrl(), "jdbc:oracle:thin:@localhost:1521/taskappdatabase.localdomain");
		Assert.assertEquals(dc.getUser(), "TASKAPP_ADMIN");
		Assert.assertEquals(dc.getPassword(), "password");
		Assert.assertEquals(dc.getNumberOfAccess(), 5);
		Assert.assertEquals(dc.getDbName(), "taskappdatabase.localdomain");
		Assert.assertEquals(dc.getDbType(), "OracleDB");
		Assert.assertThat(dc.getSchema(), nullValue());
	}

}
