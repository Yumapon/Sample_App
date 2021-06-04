package jisaku_jpa.query;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * {@link jisaku_jpa.query.QueryInfo}クラスのテストクラス
 * @author okamotoyuuma
 *
 */
public class QueryInfoTest {

	@Test
	public void QueryInfoが適切に作成できることを確認() {
		QueryInfo qi = QueryInfo.builder().setTableName("test")
											.setIdName("test")
											//.setColumnNames((ArrayList<String>) Arrays.asList("test"))
											.build();
		Assertions.assertEquals(qi.getTableName(), "test");
		Assertions.assertEquals(qi.getIdName(), "test");
		//Assertions.assertEquals(qi.getColumnNames(), "test");
	}

}
