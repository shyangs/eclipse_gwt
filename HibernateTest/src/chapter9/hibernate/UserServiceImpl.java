package chapter9.hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.hibernate.ejb.Ejb3Configuration;

import chapter9.hibernate.client.UserItem;
import chapter9.hibernate.client.UserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	private static final long serialVersionUID = 1L;

	private static EntityManagerFactory emf;
	
	static {
		Ejb3Configuration ejb3Configuration = new Ejb3Configuration();
		ejb3Configuration.addAnnotatedClass(UserItem.class);
		emf = ejb3Configuration.buildEntityManagerFactory();
	}

	@Override
	public UserItem query(String name) {
		EntityManager em = emf.createEntityManager();
		UserItem u = em.find(UserItem.class, name);
		return u;
	}

	@Override
	public void save(UserItem user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
	}
}