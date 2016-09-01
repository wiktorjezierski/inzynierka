package database.memorydatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import database.Entitys;


/**
 * @author Wiktor Jezierski
 */
public class DataBaseController {
	
	private final String PERSISTENCE_NAME = "cammed";

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	public static void main(String[] args) {
	}

	/**
	 * standard constructor
	 */
	public DataBaseController() {
//		if (entityManagerFactory == null || entityManager == null) {
//			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
//			entityManager = entityManagerFactory.createEntityManager();			
//		}
	}

	/**
	 * 
	 * */
	@Override
	protected void finalize() throws Exception {
		entityManager.close();
		entityManagerFactory.close();
	}

	/**
	 * Open connection with database
	 */
	public void openConnection() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
		entityManager = entityManagerFactory.createEntityManager();
	}

	/**
	 * Close connection with database
	 */
	public void closeConnection() {
		entityManager.close();
		entityManagerFactory.close();
	}
	
	/**
	 * 
	 * */
	public boolean transactionIsActive() {
		return entityManager.getTransaction().isActive();
	}
	
	/**
	 * Close actually connection and open new with new parameters
	 * */
	public void reconnect(String username, String password){
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("javax.persistence.jdbc.user", username);
		properties.put("javax.persistence.jdbc.password", password);			
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME, properties);
		entityManager = entityManagerFactory.createEntityManager();	
	}

	/**
	 * Open transaction for JPA entityManager
	 */
	public void commitTransaction() {
		entityManager.getTransaction().commit();
	}

	/**
	 * Close transaction for JPA entityManager
	 */
	public void beginTransaction() {
		entityManager.getTransaction().begin();
	}
	
	public void rollbackTransaction() {
		entityManager.getTransaction().rollback();
	}

	/**
	 * Save object into Data Base, function is template
	 */
	public <T extends Entitys> void saveToDataBase(T param) throws RuntimeException {
		try {
			entityManager.persist(param);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("saveToDataBase" + param.getPrimaryKey());
		}
	}
	
	/**
	 * Save object into Data Base, function is template
	 * Must to use function beginTransaction() before and commitTransaction() after this function
	 * function to using in loop
	 */
	public <T extends Entitys> void saveMoreToDataBase(T param) throws RuntimeException {
		try {
			entityManager.persist(param);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("saveToDataBaseMore" + param.getPrimaryKey());
		}
	}
	
	/**
	 * Function remove record from database make tests!!!
	 */
	public <T extends Entitys> void remove(T obj) throws RuntimeException {
		try {
			entityManager.remove(obj);	// TODO User : take cafe about remove entities
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("remove" + obj.getPrimaryKey());
		}
	}

	/**
	 * Function search all rows in Table
	 * 
	 * @param <T>
	 */
	public <T extends Entitys> List<T> findAll(Class<T> type) throws RuntimeException {
		try {
			Query query = entityManager.createQuery("from " + type.getSimpleName());
			List<T> result = (List<T>) query.getResultList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("findAll" + type.getSimpleName());
		}
	}

	/**
	 * Second param is int for class which id is type of int
	 * 
	 * @param <T>
	 */
	public <T extends Entitys> T findByPrimaryKey(Class<T> type, int primaryKey) throws RuntimeException {
		try {
			T ob = (T) entityManager.find(type, primaryKey);

			return ob;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("findByPrimaryKey " + type.getSimpleName() + " " + primaryKey);
		}
	}

	/**
	 * Second param is String for class which id is type of String
	 * 
	 * @param <T>
	 */
	public <T extends Entitys> T findByPrimaryKey(Class<T> type, String primaryKey) throws RuntimeException { // TODO check usings this function
		try {
			T ob = (T) entityManager.find(type, primaryKey);

			return ob;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("findByPrimaryKey " + type.getSimpleName() + " " + primaryKey);
		}
	}

	/**
	 * Execute NamedQuery with char % before and after value in Like
	 * */
	public <T extends Entitys> List<T> executeNamedQueryForLike(Class<T> type, String queryName, String... value) throws RuntimeException {
		try {
			String x = "%"; 
			
			Query query = entityManager.createNamedQuery(queryName);
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i + 1, x+value[i]+x);
			}
			List<T> result = (List<T>) query.getResultList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("executeQuery " + type.getSimpleName());
		}
	}
	
	/**
	 * 
	 * */
	public <T extends Entitys> List<T> executeNamedQuery(Class<T> type, String queryName, String... value) throws RuntimeException {
		try {
			Query query = entityManager.createNamedQuery(queryName);
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i + 1, value[i]);
			}
			List<T> result = (List<T>) query.getResultList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("executeQuery " + type.getSimpleName());
		}
	}
	
	/**
	 * 
	 * */
	public void executeUpdateNamedQuery(String queryName, String... value) throws RuntimeException {
		try {
			Query query = entityManager.createNamedQuery(queryName);
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i + 1, value[i]);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("updateNamedQuery " + queryName);
		}
	}
	
	/**
	 * 
	 * */
	public <T extends Entitys> List<T> executeNamedQuery(Class<T> type, String queryName, int... value) throws RuntimeException {
		try {
			Query query = entityManager.createNamedQuery(queryName);
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i + 1, value[i]);
			}
			List<T> result = (List<T>) query.getResultList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("executeNamedQuery " + type.getSimpleName() + " " + queryName);
		}
	}
	
	/**
	 * was not tested
	 * */
	public <T extends Entitys> List<T> executeNamedQuery(Class<T> type, String queryName, Date... value) throws RuntimeException {
		try {
			Query query = entityManager.createNamedQuery(queryName);
			for (int i = 0; i < value.length; i++) {
				query.setParameter(i + 1, value[i]);
			}
			List<T> result = (List<T>) query.getResultList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("executeNamedQuery " + type.getSimpleName() + queryName);
		}
	}
	
	/**
	 * Function execute query SQL Language PAY ATTENTION for * in SELECT,
	 * remember to make cast
	 */
	public List<?> executeQuery(String sql) throws RuntimeException {
		try {
			Query query = entityManager.createQuery(sql);
			List<?> result = (List<?>) query.getResultList();

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("executeQuery " + sql);
		}
	}
}
