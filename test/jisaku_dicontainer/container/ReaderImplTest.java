package jisaku_dicontainer.container;

import java.util.HashMap;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import jisaku_dicontainer.container.definition_entity.ActionDefinition;
import jisaku_dicontainer.container.definition_entity.BeanDefinition;
import jisaku_dicontainer.container.definition_entity.BusinessLogicDefinition;

/**
 * ReaderImplのテストクラス
 * @author okamotoyuuma
 *
 */
public class ReaderImplTest {

	@Test
	public void ActionDefinitionConfiguファイルを読み込む () {
		//テストクラスを取得
		Reader<ActionDefinition> reader = new ReaderImpl<>();

		HashMap<String, ActionDefinition> result = reader.read(DefaultSettingValueFile.ACTIONCONFIGFILENAME);

		System.out.println(result);
		Assertions.assertEquals("action", result.get("action").getName());
		Assertions.assertEquals("usercreatesample.actions.Action", result.get("action").getType());

		Assertions.assertEquals("loginAction", result.get("loginAction").getName());
		Assertions.assertEquals("usercreatesample.actions.LoginAction", result.get("loginAction").getType());
	}

	@Test
	public void BeanDefinitionConfigファイルを読み込む () {
		//テストクラスを取得
		Reader<BeanDefinition> reader = new ReaderImpl<>();

		HashMap<String, BeanDefinition> result = reader.read(DefaultSettingValueFile.BEANCONFIGFILENAME);

		System.out.println(result);
		Assertions.assertEquals("createTask", result.get("createTask").getName());
		Assertions.assertEquals("usercreatesample.beans.CreateTaskEntity", result.get("createTask").getType());

		Assertions.assertEquals("userInfo", result.get("userInfo").getName());
		Assertions.assertEquals("usercreatesample.beans.UserInfoEntity", result.get("userInfo").getType());

		Assertions.assertEquals("deleteTask", result.get("deleteTask").getName());
		Assertions.assertEquals("usercreatesample.beans.DeleteTaskEntity", result.get("deleteTask").getType());
	}

	@Test
	public void BusinessLogicDefinitionConfigファイルを読み込む () {
		//テストクラスを取得
		Reader<BusinessLogicDefinition> reader = new ReaderImpl<>();

		HashMap<String, BusinessLogicDefinition> result = reader.read(DefaultSettingValueFile.BUSINESSLOGICCONFIGFILENAME);

		System.out.println(result);
		Assertions.assertEquals("bl1", result.get("bl1").getName());
		Assertions.assertEquals("usercreatesample.businessLogic.BusinessLogic", result.get("bl1").getInterfaceClass());
		Assertions.assertEquals("usercreatesample.businessLogic.DefaultBusinessLogic", result.get("bl1").getType());
	}
}
