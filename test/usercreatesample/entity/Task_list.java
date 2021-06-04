package usercreatesample.entity;

import jisaku_jpa.annotation.Entity;
import jisaku_jpa.annotation.Table;
import jisaku_jpa.annotation.TimestampToDate;
import jisaku_jpa.annotation.column;
import jisaku_jpa.annotation.id;

@Entity
@Table("TASK_LIST")
public class Task_list {

	@id
	@column
	private String num;

	@TimestampToDate
	@column
	private java.sql.Timestamp deadline;

	@column
	private String name;

	@column
	private String content;

	@column
	private String client;

    public Task_list () {}

	private Task_list(String num, java.sql.Timestamp deadline, String name, String content, String client) { //不可視コンストラクタÍ
		this.num = num;
		this.deadline = deadline;
		this.name = name;
		this.content = content;
		this.client = client;
	}

	public static Builder builder() { return new Builder();}

	public static class Builder {
		private String num;
		private java.sql.Timestamp deadline;
		private String name;
		private String content;
		private String client;

		public Builder() {}; //コンストラクタ

		public Builder setNum(final String num) {
			this.num = num;
			return this;
		}

		public Builder setDeadline(final java.sql.Timestamp deadline) {
			this.deadline = new java.sql.Timestamp(deadline.getTime());;
			return this;
		}

		public Builder setName(final String name) {
			this.name = name;
			return this;
		}

		public Builder setContent(final String content) {
			this.content = content;
			return this;
		}

		public Builder setClient(final String client) {
			this.client = client;
			return this;
		}

		public Task_list build() {
			return new Task_list(this.num, this.deadline, this.name, this.content, this.client);
		}
	}

	//getter
	public String getNum() {return this.num;}
	public java.sql.Date getDeadline() {return new java.sql.Date(this.deadline.getTime());}
	public String getName() {return this.name;}
	public String getContent() {return this.content;}
	public String getClient() {return this.client;}

}
