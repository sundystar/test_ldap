package test_ldap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sly.demo.entity.Person;
import com.sly.demo.service.PersonDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:mvc.xml" })
public class LdapTest {
	
	@Autowired
	PersonDao personDao;
	@Test
	public void t1(){
		System.err.println("----");
		Person p = new Person();
		p.setCn("liyangsun");
		if(personDao.getPersonList(p).size()>0){
			String dn = personDao.getDnForUser("liyangsun");
			if(personDao.authenticate(dn, "LYS@XYTechJava")){
				System.err.println("成功");
			}else{
				System.err.println("fail");
			}
		}else{
			System.err.println("false");
		}
	}
	
}

