package org.hibernate.bugs;

import com.test.Event2;
import com.test.EventDetails2;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private static final Logger logger = LoggerFactory.getLogger(JPAUnitTestCase.class);

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void entitiesWithPackageNameWithoutInPasses() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Event2 event = new Event2();
		EventDetails2 eventDetails = new EventDetails2("asd1");
		EventDetails2 eventDetails2 = new EventDetails2("asd2");
		event.addEventDetails(eventDetails);
		event.addEventDetails(eventDetails2);
		Event2 event2 = new Event2();
		EventDetails2 eventDetails3 = new EventDetails2("something");
		event2.addEventDetails(eventDetails3);
		entityManager.persist(event);
		entityManager.persist(event2);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		String hql1 = "select e1 from event e1";
		Query query1 = entityManager.createQuery(hql1);
		List<Event2> resultList1 = query1.getResultList();
		resultList1.forEach( as -> {
					logger.info("events {} {}",as.getId(),as.getEventDetails());
				}
		);
		String hql = "select e1 from event e1, eventDetails e2 where e2.text = :text and e2.event=e1";
		Query query = entityManager.createQuery(hql);
		query.setParameter("text","something");
		List<Event2> resultList = query.getResultList();
		resultList.forEach( as -> {
			logger.info("joined event {} {}",as.getId(), as.getEventDetails());
				}
		);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void entitiesWithPackageNameWithInFails() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Event2 event = new Event2();
		EventDetails2 eventDetails = new EventDetails2("asd1");
		EventDetails2 eventDetails2 = new EventDetails2("asd2");
		event.addEventDetails(eventDetails);
		event.addEventDetails(eventDetails2);
		Event2 event2 = new Event2();
		EventDetails2 eventDetails3 = new EventDetails2("something");
		event2.addEventDetails(eventDetails3);
		entityManager.persist(event);
		entityManager.persist(event2);
		entityManager.getTransaction().commit();
		entityManager.getTransaction().begin();
		String hql1 = "select e1 from event e1";
		Query query1 = entityManager.createQuery(hql1);
		List<Event2> resultList1 = query1.getResultList();
		resultList1.forEach( as -> {
					logger.info("events {} {}",as.getId(),as.getEventDetails());
				}
		);
		String hql = "select e1 from event2 e1, eventDetails2 e2 where e2.text = :text and e2.event=e1";
		Query query = entityManager.createQuery(hql);
		query.setParameter("text","something");
		List<Event2> resultList = query.getResultList();
		resultList.forEach( as -> {
					logger.info("joined event {} {}",as.getId(), as.getEventDetails());
				}
		);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
