package jisaku_dicontainer.container;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import usercreatesample.businessLogic.BusinessLogic;

public class BusinessLogicFactoryTest {

	BusinessLogicFactory blf = new BusinessLogicFactory();

	@Test
	public void ビジネスロジックの生成() {
		BusinessLogic bl = (BusinessLogic) blf.getBusinessLogic("bl1");
		bl.login();
	}

	@Test
	public void ビジネスロジックがなければ例外をスロー() {
		Assertions.assertThrows(NullPointerException.class, () -> blf.getBusinessLogic("bl2"));
	}

}
