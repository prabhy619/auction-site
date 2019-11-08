package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import spring.bean.Product;
import spring.bean.User;
@Service
public interface serviceImp {
	ArrayList<User> userar=new ArrayList<>();
	public User checkUser(String a,String b);
	public boolean checkIfUserRegistered(User a);
	public List<User> retList();
	public List<User> retUnapprovedList();
	boolean RegisterUser(User a);
	public void approve(Integer id);	
	public List<Product> getProd(User user);
	
	public boolean  addBidder(Integer id, User user, Integer bidPrice) ;
	public List<Product> getBidded(User user) ;
}
