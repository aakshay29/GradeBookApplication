
import org.junit.Test;

import customTools.DBUser;
import model.Gbuser;

public class InsertTest {

	@Test
	public void test() {
		Gbuser u = new Gbuser();
		u.setPassword("password");
		u.setUsername("sammy2");
		//u.setUserrole(1);
		DBUser.insert(u);
	}

}
