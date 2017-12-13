package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Student;

public class UpdateTest {
	public static void main(String[] args) {
		
		Configuration cfg  = new Configuration();
		cfg.configure("resources/Hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		//session.get(Student.class,222); //in this case update() will not work, merge can we used
		
		Student st =  new Student();
		st.setId(111);
		st.setEmail("abcd@gmail.com");
		st.setName("abcd");
		
		//1.limitaion of update - we have to update all the non-primary key columns
		//only name you cant if you do this you will get null as inserted value
		//2.your current seesion must not contain a duplicate object that you are passing '
		//else it will through DuplicateObjectExcetion -- in this case use merge(it will not check current session)
		
		session.update(st);
	    t.commit();
	    
	    session.close();
	    sf.close();
	    System.out.println("Record updated successfully by update() method");
	}
}
