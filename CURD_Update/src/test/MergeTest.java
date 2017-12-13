package test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import beans.Student;

public class MergeTest {
	public static void main(String[] args) {
		
		Configuration cfg  = new Configuration();
		cfg.configure("resources/Hibernate.cfg.xml");
		SessionFactory sf=cfg.buildSessionFactory();
		Session session = sf.openSession();
		Transaction t = session.beginTransaction();
		
		session.get(Student.class,111);
		
		Student st =  new Student();
		st.setId(111);
		st.setEmail("kumar@gmail.com");
		st.setName("kumar");
		
		session.merge(st);
	    t.commit();
	    
	    session.close();
	    sf.close();
	    
	    System.out.println("Record updated successfully by merge() method");
	}
}
