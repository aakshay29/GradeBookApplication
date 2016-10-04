package customTools;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import model.Gbgrade;
import model.Gbuser;
import UserData.DBUtil;

public class DBGrade {

	public static Gbuser getUser(long userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Gbuser user = em.find(Gbuser.class, userID);
		return user;
	}
	
	public static Gbgrade getRecordById(long recordID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Gbgrade user = em.find(Gbgrade.class, recordID);
		return user;
	}

	public static void insert(Gbgrade grade) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(grade);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			em.close();
		}
	}
	public static boolean isValidAssignment(String assignmentType) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select count(g) from Gbgrade g "
				+ "where g.assignmenttype = :assignmentType";
		TypedQuery<Long> q = em.createQuery(qString, Long.class);
		boolean result = false;
		q.setParameter("assignmentType", assignmentType);

		try {
			long userId = q.getSingleResult();
			if (userId > 0) {
				result = true;
			}
		} catch (Exception e) {

			result = false;
		} finally {
			em.close();
		}
		return result;

	}
	/**
	 * Updates the data in a Bhuser Pass the method a Bhuser with all the values
	 * set to your liking and this method will update the database with these
	 * values. This method doesn't actually return anything but the good feeling
	 * that your update has been completed. If it can't be completed then it
	 * won't tell you. Sounds like something needs to be added in the future.
	 * Hmmm.
	 * 
	 * @param bhUser
	 */
	public static void update(Gbgrade grade) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(grade);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}
	
	public static List<Gbgrade> gbPost() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "select b from Gbgrade b ORDER BY b.userid";

		List<Gbgrade> posts = null;
		try {
			TypedQuery<Gbgrade> query = em.createQuery(qString, Gbgrade.class);
			posts = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return posts;
	}
	
	public static int gbPostHighestScore(String assignmentType) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		int highestNumber = 0;
		int temp;
		TypedQuery<Gbgrade> query = em.createQuery("select b.grade from Gbgrade b WHERE b.assignmenttype = :assignmenttype", Gbgrade.class);	
		query.setParameter("assignmenttype",assignmentType);
		List<Gbgrade> valueArray = query.getResultList();
		try {
			for(int i = 0; i < valueArray.size(); i++){		
				Object grade = valueArray.get(i);	
				temp = Integer.parseInt(grade.toString());
				if(temp > highestNumber){
					highestNumber = temp;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return highestNumber;
	}
	
	public static int gbPostLowestScore(String assignmentType) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		int lowestNumber = 0;
		int temp;
		TypedQuery<Gbgrade> query = em.createQuery("select b.grade from Gbgrade b WHERE b.assignmenttype = :assignmenttype", Gbgrade.class);	
		query.setParameter("assignmenttype",assignmentType);
		List<Gbgrade> valueArray = query.getResultList();
		try {
			for(int i = 0; i < valueArray.size(); i++){		
				Object grade = valueArray.get(i);	
				temp = Integer.parseInt(grade.toString());
				if(temp > lowestNumber){
					lowestNumber = temp;
				}
			}
			for(int i = 0; i < valueArray.size(); i++){		
				Object grade = valueArray.get(i);	
				temp = Integer.parseInt(grade.toString());
				if(temp < lowestNumber){
					lowestNumber = temp;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return lowestNumber;
	}
	
	public static List<Gbgrade> gbPostStudent(long studentID) {
		int studentIDInt = new BigDecimal(studentID).intValueExact();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		TypedQuery<Gbgrade> query = em.createQuery("SELECT b FROM Gbgrade b WHERE b.userid = :studentid ORDER BY b.userid", Gbgrade.class);
		query.setParameter("studentid",studentIDInt);

		List<Gbgrade> posts = null;
		try {
			//TypedQuery<Gbgrade> query = em.createQuery(qString, Gbgrade.class);
			posts = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return posts;
	}
	
	public static int gbPostStudentAverage(long studentID) {
		int studentIDInt = new BigDecimal(studentID).intValueExact();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		TypedQuery<Gbgrade> query = em.createQuery("SELECT b.grade FROM Gbgrade b WHERE b.userid = :studentid", Gbgrade.class);
		query.setParameter("studentid",studentIDInt);
		int average = 0;
		List<Gbgrade> valueArray = query.getResultList();
		try {
			for(int i = 0; i < valueArray.size(); i++){		
				Object grade = valueArray.get(i);	
				average += Integer.parseInt(grade.toString());
			}
			//average = average/(valueArray.size()-1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return average/(valueArray.size());
	}
	
	public static List<Gbgrade> gbPostStudentAndAsstype(long studentID, String assignmentType) {
		int studentIDInt = new BigDecimal(studentID).intValueExact();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		TypedQuery<Gbgrade> query = em.createQuery("SELECT b FROM Gbgrade b WHERE b.userid = :studentid and b.assignmenttype = :assignmenttype ORDER BY b.userid", Gbgrade.class);
		query.setParameter("studentid",studentIDInt);
		query.setParameter("assignmenttype",assignmentType);

		List<Gbgrade> posts = null;
		try {
			//TypedQuery<Gbgrade> query = em.createQuery(qString, Gbgrade.class);
			posts = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return posts;
	}
		
	public static int gbPostStudentAverageByAssignment(long studentID, String assignmentType) {
		int studentIDInt = new BigDecimal(studentID).intValueExact();
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		int average = 0;
		TypedQuery<Gbgrade> query = em.createQuery("SELECT b.grade FROM Gbgrade b WHERE b.userid = :studentid and b.assignmenttype = :assignmenttype", Gbgrade.class);
		query.setParameter("studentid",studentIDInt);
		query.setParameter("assignmenttype",assignmentType);

		List<Gbgrade> valueArray = query.getResultList();
		try {
			for(int i = 0; i < valueArray.size(); i++){		
				Object grade = valueArray.get(i);	
				average += Integer.parseInt(grade.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return average/(valueArray.size());
	}
	
	public static List<Gbgrade> gbPostAssignmentType(String assignmentType) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		
		TypedQuery<Gbgrade> query = em.createQuery("SELECT b FROM Gbgrade b WHERE b.assignmenttype = :assignmenttype ORDER BY b.userid", Gbgrade.class);
		query.setParameter("assignmenttype",assignmentType);

		List<Gbgrade> posts = null;
		try {
			//TypedQuery<Gbgrade> query = em.createQuery(qString, Gbgrade.class);
			posts = query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		return posts;
	}
	/**
	 * Removes a Bhuser from the database. Not sure why you'd want to delete a
	 * Bhuser from the database but this method will do it for you. This method
	 * does not explicitly remove the user's posts but most likely you've set up
	 * the database with cascading deletes which will take care of that.Gives no
	 * feedback.
	 * 
	 * @param bhUser
	 *            that you never want to see again
	 */
	public static void delete(Gbuser gbuser) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.remove(em.merge(gbuser));
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Gets a user given their email address. You've got the email when they log
	 * in but you really need the user and all its related information This
	 * method will find the user matching that email. The database should ensure
	 * that you can't have two users with the same email. Otherwise there's no
	 * telling what you'd get.
	 * 
	 * @param email
	 * @return Bhuser with that unique email address
	 */
	public static Gbuser getUserByEmail(String email) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select u from Gbuser u " + "where u.username = :useremail";
		TypedQuery<Gbuser> q = em.createQuery(qString, Gbuser.class);
		q.setParameter("useremail", email);
		Gbuser user = null;
		try {
			user = q.getSingleResult();
		} catch (NoResultException e) {
			System.out.println(e);
		} finally {
			em.close();
		}
		return user;

	}

	/**
	 * Is this user valid? This method has the answer for you. Checks the
	 * database and counts the number of users with this username and password.
	 * If it returns 0 then either the username or password don't exist in the
	 * database. If it returns 1 then you have found the user with that username
	 * and password. If it returns >1 then you need to fix your database first.
	 * 
	 * @param user
	 *            of type Bhuser
	 * @return true or false indicating the user exists or doesn't
	 */
	public static boolean isValidUser(String username, String password) {
		System.out.println("Entered");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		System.out.println("Entered2");
		String qString = "Select count(g) from Gbuser g "
				+ "where g.username = :username and g.password = :userpass";
		TypedQuery<Long> q = em.createQuery(qString, Long.class);
		System.out.println("Entered3");
		boolean result = false;
		System.out.println(username + password);
		q.setParameter("username", username);
		q.setParameter("userpass", password);

		try {
			long userId = q.getSingleResult();
			if (userId > 0) {
				result = true;
			}
		} catch (Exception e) {

			result = false;
		} finally {
			em.close();
		}
		return result;

	}

}
