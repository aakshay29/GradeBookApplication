package customTools;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import model.Gbuser;
import UserData.DBUtil;

/**
 * @author djw DbUser class contains helper methods for working with Bhusers
 *
 */
public class DBUser {
	/**
	 * Gets a Bhuser from the database
	 * 
	 * @param userID
	 *            - primary key from database. Must be type long
	 * @return Bhuser
	 */
	public static Gbuser getUser(long userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		Gbuser user = em.find(Gbuser.class, userID);
		return user;
	}

	public static void insert(Gbuser gbuser) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		// System.out.println("DbBullhorn: begin transaction");
		try {
			trans.begin();
			em.persist(gbuser);
			// System.out.println("DbBullhorn: commit transaction");
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println("DbBullhorn: rollback transaction");
			trans.rollback();
		} finally {
			// System.out.println("DbBullhorn: close em");
			em.close();
		}
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
	public static void update(Gbuser gbuser) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(gbuser);
			trans.commit();
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
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
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		String qString = "Select count(g) from Gbuser g "
				+ "where g.username = :username and g.password = :userpass";
		TypedQuery<Long> q = em.createQuery(qString, Long.class);
		boolean result = false;
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
