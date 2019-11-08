package spring.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="products_prabhjyot")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Prodid")
	int prodId;

	@Column(name = "ProdName")
	String prodname;
	
	@ManyToOne
	private User user;
	
	@Column
	int biddingPrice;
	
	@Column(name="Sold")
	char sold;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER,mappedBy="bidList")
	private List<User> bidderList;
	
	public List<User> getBidderList() {
		return bidderList;
	}


	public void setBidderList(List<User> bidderList) {
		this.bidderList = bidderList;
	}


	public Product() {
		
	}

	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getProdId() {
		return prodId;
	}
	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	
	public char getSold() {
		return sold;
	}
	public void setSold(char sold) {
		this.sold = sold;
	}
	public int getBiddingPrice() {
		return biddingPrice;
	}
	public void setBiddingPrice(int biddingPrice) {
		this.biddingPrice = biddingPrice;
	}


	
}
