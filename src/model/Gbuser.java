package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the GBUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Gbuser.findAll", query="SELECT g FROM Gbuser g")
public class Gbuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String password;

	private String username;

	private BigDecimal userrole;

	public Gbuser() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getUserrole() {
		return this.userrole;
	}

	public void setUserrole(BigDecimal userrole) {
		this.userrole = userrole;
	}

}