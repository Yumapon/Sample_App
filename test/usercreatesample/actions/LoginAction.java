package usercreatesample.actions;

import jisaku_dicontainer.annotation.ActionMethod;
import jisaku_dicontainer.annotation.FormInjection;
import jisaku_dicontainer.annotation.Service;
import jisaku_servlet.servlet.Model;
import usercreatesample.beans.UserInfoEntity;
import usercreatesample.businessLogic.BusinessLogic;

public class LoginAction {

	@Service
	BusinessLogic bl1;

	@FormInjection
	UserInfoEntity userInfo;

	@ActionMethod("login")
	public Model actionMethod3() {
		//確認用
		System.out.println(" _________________");
		System.out.println("<Action機能スタート>");
		System.out.println("-----------------");
		System.out.println("    \\");
		System.out.println("     \\");
		System.out.println("      \\");
		System.out.println("                   ##        .");
		System.out.println("              ## ## ##       ==");
		System.out.println("           ## ## ## ##      ===");
		System.out.println("       /\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"\"___/ ===");
		System.out.println("  ~~~ {~~ ~~~~ ~~~ ~~~~ ~~ ~ /  ===- ~~~");
		System.out.println("       \\______ o          __/");
		System.out.println("        \\    \\        __/");
		System.out.println("          \\____\\______/");

		bl1.login();

		System.out.println(userInfo.getUser_id());
		System.out.println(userInfo.getPassword());

		Model model  = new Model();
		//次画面をセット
		model.setNextPage("login.jsp");
		return model;
	}

	@ActionMethod("login2")
	public Model actionMethod4() {

		//taskListをJSONセット
		Model model = new Model();
		model.setJSON();
		model.setJsonObj("json　Test　成功！");
		System.out.println("json　Test　成功！");

		return model;
	}
}
