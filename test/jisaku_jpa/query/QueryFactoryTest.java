package jisaku_jpa.query;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * QueryFactoryクラスのテストクラス
 *
 * @author okamotoyuuma
 * @version 2.0.0
 * @version 2021.04.11
 */
public class QueryFactoryTest {

	@Test
	public void Queryインスタンスの取得() {
		//Queryを取得
		Query qu = QueryFactory.getQueryClass("oracledb");

		Assert.assertThat(qu, notNullValue());
	}
}
