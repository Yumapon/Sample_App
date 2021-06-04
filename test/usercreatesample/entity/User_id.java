package usercreatesample.entity;

import java.math.BigDecimal;

import jisaku_jpa.annotation.Entity;
import jisaku_jpa.annotation.Table;
import jisaku_jpa.annotation.column;
import jisaku_jpa.annotation.id;

@Entity
@Table("USER_ID")
public class User_id {

	@id
	@column
	private BigDecimal id;

	@column
	private String password;

	public User_id(){}

	private User_id(BigDecimal id, String password) {
		this.id = id;
		this.password = password;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private BigDecimal id;
		private String password;

		public Builder() {}

		public Builder setId(final BigDecimal id) {
			this.id = id;
			return this;
		}

		public Builder setPassword(final String password) {
			this.password = password;
			return this;
		}

		public User_id build() {
			return new User_id(this.id, this.password);
		}
	}

	public BigDecimal getId() {return this.id;}
	public String getPassword() {return this.password;}

}
