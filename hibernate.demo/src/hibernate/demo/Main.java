package hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

	public static void main(String[] args) {
		add();
	}

	public static void add() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			// HQL --> Hibernate query language

			City city = new City();
			city.setName("D�zce Hibernate");
			city.setCountryCode("TUR");
			city.setDistrict("D�zce");
			city.setPopulation(75000);
			session.save(city);

			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			sessionFactory.close();
		}
	}

	public static void select() {
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(City.class)
				.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			// HQL --> Hibernate query language
			String query = "";
			// query= "from City c where c.countryCode='TUR' and c.district='Ankara'"
			// query = "from City c where c.name like '%kar%'";
			// query = "from City c order by c.name desc";
			// List<City> cities = session.createQuery(query).getResultList(); // City:name
			// of entity not table
//			for (City city : cities) {
//				System.out.println(city.getName());
//			}
//			
			query = "select c.countryCode from City c group by c.countryCode";
			List<String> countryCodes = session.createQuery(query).getResultList(); // City:name of entity not table

			for (String countryCode : countryCodes) {
				System.out.println(countryCode);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			sessionFactory.close();
		}
	}

}
