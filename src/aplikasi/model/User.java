package aplikasi.model;
// Generated Jan 7, 2017 1:48:23 AM by Hibernate Tools 5.2.0.CR1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer id;
	private String username;
	private String password;
	private Byte typeuser;

	public User() {
	}

	public User(String username, String password, Byte typeuser) {
		this.username = username;
		this.password = password;
		this.typeuser = typeuser;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getTypeuser() {
		return this.typeuser;
	}

	public void setTypeuser(Byte typeuser) {
		this.typeuser = typeuser;
	}

}
