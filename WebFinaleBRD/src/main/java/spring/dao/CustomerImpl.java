package spring.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.bean.Product;
import spring.bean.User;

@Repository
public class CustomerImpl implements CustomerDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean register(User user) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();

				session.save(user);

		session.getTransaction().commit();
		System.out.println("In DAO");
		return true;
	}

	@Override
	public boolean delete(User a) {
			Session session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			session.delete(a);
			session.getTransaction().commit();
		return true;
	}

	@Override
	public List<User> viewAll() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Query query=session.createQuery("from User");
		List<User> ar=query.list();
		System.out.println(ar);
		session.getTransaction().commit();

		return ar;
	}

	@Override
	public List<User>  viewAllUnapproved() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query=session.createQuery("Select u from User u where u.registered='f'");
		List<User> ar=query.list();
		session.getTransaction().commit();
		System.out.println(ar);
		return ar;
	}

	@Override
	public boolean update(User a) {
		sessionFactory.getCurrentSession().update(a);
		return true;
	}
	
	@Override
	public User viewUserbyId(int a)
	{
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user =  (User) session.get(User.class, a);
		System.out.println(user);
		session.getTransaction().commit();
		return user;
	}

	@Override
	public void approve(User a) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		User o=(User) session.load(User.class,new Integer(a.getId()));
		Transaction tx = session.beginTransaction();	
		o.setRegistered('y');
		System.out.println(o);
		tx.commit();
		System.out.println("Object Updated successfully.....!!");
	}
//To check login
	@Override
	public User checkLogin(String name,String pass)
	{
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		System.out.println(name + " " + pass);
		Query query=session.createQuery("Select  u from User u where u.userName=:n AND u.password=:p")
		.setParameter("n", name)
		.setParameter("p", pass);
		User a=(User) query.uniqueResult();
		System.out.print("MOJITO"+a);
		session.getTransaction().commit();
return a;
	}

	@Override
	public List<Product> getProducts(User a) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
	
		User user = (User)session.get(User.class, a.getId());
		List<Product> prodList = user.getProd();
		List<Product> main=new ArrayList<>();
		for(Product s:prodList)
		{
			if(s.getSold()=='n')
				main.add(s);
		}
		System.out.print("MOJITO"+prodList);
		session.getTransaction().commit();
		return main;
	}

	@Override
	public List<Product> getBidPro(User a) {
			Session session=sessionFactory.getCurrentSession();
			session.beginTransaction();
			User user = (User)session.get(User.class, a.getId());
			List<Product> prodList = user.getBidList();
			System.out.println("muse"+prodList);
			session.getTransaction().commit();
			return prodList;
	}

	@Override
	public List<Product> getAll(User a) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		User user = (User)session.get(User.class, a.getId());
		
		Query query=session.createQuery("from Product");
		List<Product> prodList = query.list();
		List<Product> proret=new ArrayList<>();
		System.out.println("inserty"+prodList);
		for(Product t:prodList)
		{
			if(t.getSold()=='y' || t.getUser().getId()==a.getId())
				continue;
			
			if(t.getBidderList().size() >0)
				if(a.getId() ==  t.getBidderList().get(t.getBidderList().size()-1).getId()  )
					continue;
			
			proret.add(t);
		}
		
		
		session.getTransaction().commit();
		return proret;
	}

	@Override
	public boolean addBidder(Integer id, User user, Integer bidPrice) {
	
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
	
		User bidder= (User)session.get(User.class, user.getId());
		Product prodtobebid=(Product) session.get(Product.class, id);
		if(bidPrice<prodtobebid.getBiddingPrice())
			return false;
		else
		{	
			for(int i=0;i<bidder.getBidList().size();i++)
			{
				Product c=bidder.getBidList().get(i);
				if(c.getProdId()==prodtobebid.getProdId())
				{
					bidder.getBidList().remove(i);
				}
			}
			System.out.println("ddddddddddddddddddddddddd");
			System.out.println(bidder.getBidList());
			prodtobebid.getBidderList().add(bidder);
			prodtobebid.setBiddingPrice(bidPrice);			
			bidder.getBidList().add(prodtobebid);
			session.getTransaction().commit();
		}
		
		return true;
	}

	@Override
	public List<Product> getMyProd(int id) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, id);
		List<Product> prodList = user.getProd();
		List<Product> main=new ArrayList<>();
		for(Product s:prodList)
		{
			if(s.getSold()=='y')
				main.add(s);
		}
		System.out.print("YAKAmzu"+main);
		session.getTransaction().commit();
		return main;
	}

	@Override
	public List<Product> getBiddedOn(int id) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, id);
		List<Product> prodList=user.getBidList();
		List<Product> main=new ArrayList<>();
		for(Product P:prodList)
		{
			if(P.getSold()=='n')
			main.add(P);
		}	session.getTransaction().commit();
		return main;
	}

	@Override
	public List<Product> getPages(int page_id) {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query q=session.createQuery("from Product");
		q.setFirstResult((page_id - 1) * 2);
	    q.setMaxResults(2);
	    List<Product> l = q.list();
	    System.out.println(l);
		session.getTransaction().commit();
	   return l;
	}	
}
