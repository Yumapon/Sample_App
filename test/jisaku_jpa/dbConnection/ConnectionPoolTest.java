package jisaku_jpa.dbConnection;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import jisaku_jpa.exception.DoNotHaveDBAccessException;
import jisaku_jpa.exception.NoDBAccessException;
import jisaku_jpa.exception.NotBeginTransactionException;

/**
 * ConnectionPoolクラスのテストクラス
 * @author okamotoyuuma
 * @version 2.0.0
 * @version 2021.04.11
 */
public class ConnectionPoolTest {

	@Test
	@Order(1)
	public void インスタンスを取得する() {
		ConnectionPool cp = ConnectionPool.getInstance();
		Assert.assertThat(cp, notNullValue());
	}

	@Test
	@Order(2)
	public void DBAccessをチェックアウトする() throws NoDBAccessException, NotBeginTransactionException {
		ConnectionPool cp = ConnectionPool.getInstance();

		//スレッドIDを取得
		String threadId = String.valueOf(Thread.currentThread().getId());

		//DBAccessをチェックアウト
		cp.checkoutDBAccess(threadId);

		//DBAccessを取得
		DBAccess da = cp.getDBAccess(threadId);
		Assert.assertThat(da, notNullValue());
	}

	@Test(expected = NotBeginTransactionException.class)
	@Order(4)
	public void DBAccessを返却する() throws NoDBAccessException, NotBeginTransactionException, DoNotHaveDBAccessException {
		ConnectionPool cp = ConnectionPool.getInstance();

		//スレッドIDを取得
		String threadId = String.valueOf(Thread.currentThread().getId());

		//DBAccessをチェックアウト
		cp.checkoutDBAccess(threadId);

		//DBAccessを取得
		DBAccess da = cp.getDBAccess(threadId);
		Assert.assertThat(da, notNullValue());

		//DBAccessを返却
		cp.returnDBAccess(threadId);

		//返却確認
		cp.getDBAccess(threadId);
	}

	@Test
	@Order(5)
	public void TransactionID無しでDBAccessを取得する() throws NoDBAccessException {
		ConnectionPool cp = ConnectionPool.getInstance();

		//DBAccessの取得
		DBAccess da = cp.getDBAccess();

		Assert.assertThat(da, notNullValue());
	}

}
