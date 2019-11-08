package spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.bean.Product;
import spring.bean.User;
import spring.dao.CustomerDao;
import spring.dao.ProductDao;


@Service
public class ServiceClass implements serviceImp{
	@Autowired
	CustomerDao customerdao;
	
	@Autowired
	ProductDao productdao;
	
	@Override
	public User checkUser(String a, String b) {
		return (customerdao.checkLogin(a, b));
	}

	@Override
	public boolean checkIfUserRegistered(User a) {
		User t=customerdao.viewUserbyId(a.getId());
		if(t.isRegistered()=='y')
			return true;
		return false;
	}

	@Override
	public boolean RegisterUser(User a) {
		if(customerdao.register(a))
			return true;
		return false;
	}

	@Override
	public List<User> retList() {
		return customerdao.viewAll();
	}

	@Override
	public List<User> retUnapprovedList() {
		return customerdao.viewAllUnapproved();
	}

	public void delete(Integer id) {
		 customerdao.delete(customerdao.viewUserbyId(id));
	}

	public void approve(Integer id) {
			customerdao.approve(customerdao.viewUserbyId(id));
	}

	public boolean saveProduct(Product t) {
		return productdao.saveProd(t);
	}

	public List<Product> getProd(User user) {
		return customerdao.getProducts(user);
	}

	public List<Product> getBidProd(User a) {
		
		return customerdao.getBidPro(a);
	}

	public List<Product> getAll(User user) {
			
		return customerdao.getAll(user);
	}

	public boolean  addBidder(Integer id, User user, Integer bidPrice) {
		return customerdao.addBidder(id,user,bidPrice);
		
	}

	public boolean finalizeDeal(Integer pid) {
		return productdao.finalizeDeal(pid);
	}

	public List<Product> seeMine(int id) {
		// TODO Auto-generated method stub
		return customerdao.getMyProd(id);
	}

	public List<Product> getBidded(User user) {
		// TODO Auto-generated method stub
		return customerdao.getBiddedOn(user.getId());
	}

	public List<Product> getUserByPage(int page_id) {
		return customerdao.getPages(page_id);
	}
	
}
