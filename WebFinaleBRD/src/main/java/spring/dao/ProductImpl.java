package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.Product;
import spring.bean.User;

@Repository
public class ProductImpl implements ProductDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean saveProd(Product t) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(t);
		session.getTransaction().commit();
		System.out.println("In DAO");
		return true;
	}
//TO BE ANNOUNCED
	@Override
	public boolean finalizeDeal(Integer pid) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Product product=(Product) session.get(Product.class, pid)	;
		product.setSold('y');
		User d=product.getBidderList().get(product.getBidderList().size()-1);
		User user=(User) session.get(User.class, d.getId())	;
		//user.setRegistered(registered);
		User owner=product.getUser();
		product.setUser(d);
		return true;
		
	}

}
