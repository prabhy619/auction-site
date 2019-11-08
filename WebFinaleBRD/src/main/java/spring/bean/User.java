package spring.bean;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="users_prabhjyot")
public class User {
	
	
	
	
	  public List<Product> getBidList() {
		return bidList;
	}
	public void setBidList(List<Product> bidList) {
		this.bidList = bidList;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="user")
	  private List<Product> prod;
	  
	public List<Product> getProd() {
		return prod;
	}
	public void setProd(List<Product> prod) {
		this.prod = prod;
	}
	public char getRegistered() {
		return registered;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	int id;
	
	@Column(name = "Name")
	@NotEmpty
	String name;
	
	@NotEmpty
	@Column(name = "userName",unique=true)
	String userName;
	
	@Min(2)
	@Max(100)
	@Column(name = "age")
	int Age;
	
	
	@ManyToMany
	private List<Product> bidList;
	
	
	
	@Column(name = "emailID")
	@NotEmpty
	String emailId;
	@Column(name = "password")	
	@NotEmpty
	String password;
	
	@Column(name = "isRegistered")
	char registered;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char isRegistered() {
		return registered;
	}
	public void setRegistered(char registered) {
		this.registered = registered;
	}
	public User(){
		
	}
	public User(int id, String name, String userName, int age, String emailId, String password, char registered) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		Age = age;
		this.emailId = emailId;
		this.password = password;
		this.registered = registered;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", Age=" + Age + ", emailId=" + emailId
				+ ", password=" + password + ", registered=" + registered + "]";
	}
	
	
	
	
	
}
