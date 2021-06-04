package usercreatesample.businessLogic;

import java.util.ArrayList;

import usercreatesample.entity.Task_list;

public class DefaultBusinessLogic implements BusinessLogic {

	@Override
	//ログイン処理の実装(ログイン失敗、該当のユーザIDが存在しない場合は例外処理発生)
	public boolean login() {

		//確認用
		System.out.println(" _________________");
		System.out.println("<ログイン機能スタート>");
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
		return false;

	}

	@Override
	public void taskstorage(Task_list task_list) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public ArrayList<Task_list> getList() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void deleteTask(String[] taskNumList) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public String taskNum() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}