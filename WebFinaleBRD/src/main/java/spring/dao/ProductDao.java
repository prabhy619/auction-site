package spring.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.Product;

//@Repository
public interface ProductDao {

	public boolean saveProd(Product t);
	/*@Autowired
	SessionFactory sessionFactory;*/

	public boolean finalizeDeal(Integer pid);
	
	
}
