package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GBGRADE database table.
 * 
 */
@Entity
@NamedQuery(name="Gbgrade.findAll", query="SELECT g FROM Gbgrade g")
public class Gbgrade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	private String assignment;

	private String assignmenttype;

	private String grade;

	private String subject;

	private int userid;

	public Gbgrade() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssignment() {
		return this.assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	public String getAssignmenttype() {
		return this.assignmenttype;
	}

	public void setAssignmenttype(String assignmenttype) {
		this.assignmenttype = assignmenttype;
	}

	public String getGrade() {
		return this.grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userID2) {
		this.userid = userID2;
	}

}