package hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			List<City> cities = session.createQuery("from City").getResultList(); // City:name of entity not table
			for (City city : cities) {
				System.out.println(city.getName());
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			sessionFactory.close();
		}
	}

}
