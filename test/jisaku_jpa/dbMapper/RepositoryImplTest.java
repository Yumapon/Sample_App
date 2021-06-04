package jisaku_jpa.dbMapper;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jisaku_jpa.dbConnection.ConnectionPool;
import jisaku_jpa.exception.NoDBAccessException;
import usercreatesample.entity.Task_list;
import usercreatesample.entity.User_id;

/**
 * {@link src.jisaku_jpa.dbMapper.RepositoryImpl}のテストクラス
 * @author okamotoyuuma
 */
public class RepositoryImplTest {

	@Ignore
	public void データベースにデータを格納する() throws NoDBAccessException {
		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		//テストデータの作成
		Task_list task = Task_list.builder()
								  .setNum(LocalDateTime.now().toString())
								  .setDeadline(new Timestamp(System.currentTimeMillis()))
								  .setName("Test")
								  .setClient("Test")
								  .setContent("Test")
								  .build();
		User_id user = User_id.builder()
						      .setId(BigDecimal.valueOf(1234))
						      //.setPassword("password")
						      .build();

		//コネクションとっとく
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		//メソッドの実行
		repos1.save(task);
		repos2.save(user);

		//これで確認する（自分でDBから確認 DBAccessの自動コミットをONにしておく）
		System.out.print("DBに格納したデータ" + "\n"
					+ "★Task_list" + "\n"
					+ "num :" + task.getNum() + "\n"
					+ "name :" + task.getName() + "\n"
					+ "client :" + task.getClient() + "\n"
					+ "content :" + task.getContent() + "\n"
					+ "★User_id" + "\n"
					+ "id :" + user.getId() + "\n"
					+ "password :" + user.getPassword() + "\n");
	}

	@Ignore
	public void 指定されたIDで識別されるエンティティを返す() throws NoDBAccessException {
		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		//コネクションとっとく
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		Optional<Task_list> result1 = repos1.findById("027cf20d-8189-4fe3-babb-1b76428cf32d");
		Optional<User_id> result2 = repos2.findById(100);

		Assertions.assertEquals(true, result1.isPresent());//何か帰ってきてるか確認
		Assertions.assertEquals(true, result2.isPresent());//何か帰ってきてるか確認

		//意図した値が返ってきているか確認
		Assertions.assertEquals("test", result1.get().getName());
		Assertions.assertEquals("test", result1.get().getContent());
		Assertions.assertEquals("test", result1.get().getClient());

		Assertions.assertEquals(BigDecimal.valueOf(100), result2.get().getId());
		Assertions.assertEquals("password", result2.get().getPassword());

	}

	@Ignore
	public void DBに格納されている全てのデータを返す() throws NoDBAccessException {
		//テストクラスの取得
		//Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		//Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		//コネクションとっとく
		//ConnectionPool cp = ConnectionPool.getInstance();
		//cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		//Optional<ArrayList<Task_list>> result1  = repos1.findAll();
		//Optional<ArrayList<User_id>> result2  = repos2.findAll();

	}

	@Ignore
	public void 指定された条件のエンティティを返す() throws NoDBAccessException {
		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		//コネクションとっとく
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		Task_list task = Task_list.builder()
				  .setName("test")
				  .setClient("test")
				  .setContent("test")
				  .build();
		User_id user = User_id.builder()
		      .setPassword("password")
		      .build();

		Optional<ArrayList<Task_list>> result1  = repos1.findAll(task);
		Optional<ArrayList<User_id>> result2  = repos2.findAll(user);

		Assertions.assertEquals(true, result1.isPresent());//何か帰ってきてるか確認
		Assertions.assertEquals(true, result2.isPresent());//何か帰ってきてるか確認

		//テストコードの確認
		Task_list taskresult = result1.get().get(0);
		Assertions.assertEquals("test", taskresult.getName());
		Assertions.assertEquals("test", taskresult.getContent());
		Assertions.assertEquals("test", taskresult.getClient());

		User_id userresult = result2.get().get(0);
		Assertions.assertEquals(BigDecimal.valueOf(100), userresult.getId());
		Assertions.assertEquals("password", userresult.getPassword());
	}

	@Ignore
	public void DBに格納されているデータの数を返します() throws NoDBAccessException {

		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		int result1 = repos1.count();
		int result2 = repos2.count();

		Assertions.assertEquals(5, result1);
		Assertions.assertEquals(2, result2);

	}

	@Ignore
	public void 主キーを指定されたエンティティを削除します() throws NoDBAccessException {
		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		//テストデータの作成
		Task_list task = Task_list.builder()
								  .setNum("027cf20d-8189-4fe3-babb-1b76428cf32d")
								  .build();
		User_id user = User_id.builder()
						      .setId(BigDecimal.valueOf(1234))
						      //.setPassword("password")
						      .build();

		//コネクションとっとく
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		//メソッドの実行
		repos1.delete(task);
		repos2.delete(user);

		//自分で確認（自動コミットON）
	}

	@Test
	public void 条件を指定されたエンティティを削除します() throws NoDBAccessException {
		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		//テストデータの作成
		Task_list task = Task_list.builder()
				  				  .setName("Test")
				  				  .setClient("Test")
				  				  .setContent("Test")
								  .build();
		User_id user = User_id.builder()
						      .setId(BigDecimal.valueOf(1234))
						      //.setPassword("password")
						      .build();

		//コネクションとっとく
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.checkoutDBAccess(String.valueOf(Thread.currentThread().getId()));

		//メソッドの実行
		repos1.delete(task);
		repos2.delete(user);

		//自分で確認（自動コミットON）
	}

	@Test
	public void 指定されたIDのエンティティが存在するかどうかを判断する() {
		//テストクラスの取得
		Repository<Task_list, String> repos1 = new RepositoryImpl<>();
		Repository<User_id, Integer> repos2 = new RepositoryImpl<>();

		boolean result1 = repos1.existsById("100");
		boolean result2 = repos2.existsById(100);

		Assertions.assertEquals(true, result1);
		Assertions.assertEquals(true, result2);
	}

}
