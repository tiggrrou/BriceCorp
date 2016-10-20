import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.wha.springmvc.configuration.HelloWorldConfiguration;
import com.wha.springmvc.configuration.JpaConfiguration;
import com.wha.springmvc.dao.UserDaoImpl;
import com.wha.springmvc.model.Client;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, HelloWorldConfiguration.class})
public class UserDaoTest {

	@Autowired
	UserDaoImpl userDao;

	@Test
	public void findCliById() {
		Client cli =  userDao.findCliById(3);
		assertEquals(2, cli.getComptes().size());
		assertEquals(1, cli.getNotifications().size());

	}

}
