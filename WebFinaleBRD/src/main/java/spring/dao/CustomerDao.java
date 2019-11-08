package spring.dao;

import java.util.ArrayList;
import java.util.List;

import spring.bean.Product;
import spring.bean.User;

public interface CustomerDao {
	public boolean register(User user);
	public boolean delete(User a);
	
	public List<User> viewAllUnapproved();
	public boolean update(User a);
	User viewUserbyId(int a);
	List<User> viewAll();
	public void approve(User a);
	public User checkLogin(String name, String pass);
	
	public List<Product> getProducts(User a);
	public List<Product> getBidPro(User a);
	public List<Product> getAll(User user);
	public boolean addBidder(Integer id, User user, Integer bidPrice);
	public List<Product> getMyProd(int id);
	public List<Product> getBiddedOn(int id);
	public List<Product> getPages(int page_id);

}
