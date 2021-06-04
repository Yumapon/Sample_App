package jisaku_dicontainer.container;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import usercreatesample.actions.LoginAction;
import usercreatesample.beans.UserInfoEntity;

/**
 * ApplicationContainerImplのテストクラス
 * @author okamotoyuuma
 *
 */
public class ApplicationContainerImplTest {

	ApplicationContainer container = new ApplicationContainerImpl();

	@Ignore
	@Test
	public void 指定したBeanを取得する() {
		UserInfoEntity result = (UserInfoEntity) container.generator("userInfo");
		result.setUser_id(1234);
		result.setPassword("password");
		Assertions.assertEquals(1234, result.getUser_id());
		Assertions.assertEquals("password", result.getPassword());
	}

	@Ignore
	@Test
	public void 指定したActionを取得する() {
		LoginAction result = (LoginAction) container.getAction("loginAction");
		result.actionMethod3();
	}

}
