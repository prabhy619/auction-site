package spring.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.bean.Product;
import spring.bean.User;
import spring.service.ServiceClass;

@Controller
public class controller {
	@Autowired
	 ServiceClass service;

	
	@RequestMapping(path="/seeBids", method=RequestMethod.GET)
	public String viewProd(ModelMap map,HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession(false);
		System.out.println(session.getAttribute("userlog")	);
		if(session.getAttribute("userlog")==null)
			return "login";
		
		User user=((User)session.getAttribute("userlog"));
		List<Product> proolist; 	
		proolist=service.getBidded(user);
		System.out.println(proolist);
		map.addAttribute("bidProd", proolist);
		List<String> names=new ArrayList<>();String aname;
		for(Product c:proolist)
		{
					aname="-";
					try{aname=c.getBidderList().get(c.getBidderList().size()-1).getName();}
						catch(Exception E)
					{
							;
					}
					names.add(aname);
		}	
		map.addAttribute("names", names);
		return "seeBids";		
	}
	
	@GetMapping(path="/prod/{no}")
	public String seeAllprod(@PathVariable(value="no") int page_id,ModelMap map)
	{
		/*//int total = 5;
        if(page_id == 1) {
            // do nothing!
        } else {            
            page_id= (page_id-1)*total+1;  
        }
        */
		List<Product> list = service.getUserByPage(page_id);
        map.addAttribute("listPg",list);
        map.addAttribute("no",page_id);
		return "prod";
		
	}
	
	
	
	@RequestMapping(path="/newProd", method=RequestMethod.GET)
	public  String newProd(ModelMap map){
		map.addAttribute("prod", new Product());
		System.out.println("Hallo");
		return "newProd";
		
	}
	
	@RequestMapping(path="/confirmBid/{no}", method=RequestMethod.GET)
	public  String confirmBid(@PathVariable(value="no") Integer id,ModelMap map,HttpServletRequest req,HttpServletResponse res){
		HttpSession session=req.getSession(false);
		System.out.println(session.getAttribute("userlog")	);
		if(session.getAttribute("userlog")==null)
			return "login";
						
		User user=((User)session.getAttribute("userlog"));
		Integer bidPrice= new Integer(req.getParameter("bidNo"));	
		service.addBidder(id,user,bidPrice);
			
		return "redirect:/userDashboard";
	}
	
	
	@RequestMapping(path="/approveBid/{no}", method=RequestMethod.GET)
	public  String approveBid(@PathVariable(value="no") Integer Pid,ModelMap map,HttpServletRequest req,HttpServletResponse res){
		HttpSession session=req.getSession(false);
		System.out.println(session.getAttribute("userlog")	);
		if(session.getAttribute("userlog")==null)
			return "login";
						
		User user=((User)session.getAttribute("userlog"));
		if(service.finalizeDeal(Pid))
			{
				System.out.println("Symphony");
				return "redirect:/userDashboard";
			}
		return "login";
	}
	
	
	
	
	
	
	
	@RequestMapping(path="/newBid", method=RequestMethod.GET)
	public  String newBid(ModelMap map,HttpServletRequest req,HttpServletResponse res){
		HttpSession session=req.getSession(false);
		System.out.println(session.getAttribute("userlog")	);
		if(session.getAttribute("userlog")==null)
			return "login";
		
		User user=((User)session.getAttribute("userlog"));
		List<Product> allSee;
		allSee=service.getAll(user);
		System.out.print(allSee);
		map.addAttribute("prod",allSee);
		System.out.println("Hallo");
		return "newBid";
		
	}
	
	@RequestMapping(path="/submitProduct", method=RequestMethod.POST)
	public  String newProdPost(ModelMap map,HttpServletRequest req,HttpServletResponse res){	
		
		HttpSession session=req.getSession(false);
		System.out.println(session.getAttribute("userlog")	);
		if(session.getAttribute("userlog")==null)
			return "login";
		
		
		User user=((User)session.getAttribute("userlog"));
		String Prodname= req.getParameter("Prodname");
		Integer biddingPrice= new Integer( req.getParameter("biddingPrice"));
		Product t=new Product();
		t.setBiddingPrice(biddingPrice);
		t.setProdname(Prodname);
		t.setUser(user);
		t.setSold('n');
		service.saveProduct(t);
		System.out.println(t);
		return "redirect:/userDashboard";
	}
	
	
	
	
	@RequestMapping(path="/reg1", method=RequestMethod.POST)
	public  ModelAndView logUser2(@ModelAttribute("user") @Valid User user, BindingResult result){	
	ModelAndView mv = new ModelAndView();
	if(result.hasErrors()){
		mv.setViewName("register");
		return mv;
	}
	else{
		ModelAndView model=new ModelAndView("index");
		model.addObject("message","Wait for the admin to approve");
		user.setRegistered('f');
		System.out.println(user);
		//Check for unique email
		service.RegisterUser(user);
		return model;
	}
	
	}
	
	@RequestMapping(path="/reg1", method=RequestMethod.GET)
	public  String logUser1(ModelMap map){	
		map.addAttribute("user", new User());
		System.out.println("Hello");
		return "register";
	}
	
	
	@RequestMapping(path="/userDashboard", method=RequestMethod.POST)
	public  String login(ModelMap map,HttpServletRequest req,HttpServletResponse res){	
		
		HttpSession session=req.getSession();
		if(session.getAttribute("userlog")!=null)
			return "userDashboard";
		
		String name= req.getParameter("login");
		String pwd= req.getParameter("pass");
		User a=service.checkUser(name, pwd);
			System.out.print("DOLPHIN"+a);
			if(a==null)
			{
				map.addAttribute("Errormsg", "No such account please Register");
				return("redirect:reg1");
			}
			else if(a.isRegistered()=='f')
			{
				map.addAttribute("message", "Wait for the admin to approve");
				return("redirect:/");
			}
			else
				{
				List<Product> prolist,retPro;
				prolist=service.getProd(a);
				retPro=service.getBidProd(a);
					map.addAttribute("user",a);
					map.addAttribute("myProd",prolist);
					
					List<String> names=new ArrayList<>();String aname;
					for(Product c:prolist)
					{
								aname="-";
								try{aname=c.getBidderList().get(c.getBidderList().size()-1).getName();}
									catch(Exception E)
								{
										;
								}
								names.add(aname);
					}	
					System.out.println("klklklklklklklk"+'\n');
					session=req.getSession();
					session.setAttribute("userlog", a);
					map.addAttribute("myProd",prolist);map.addAttribute("names",names);
					map.addAttribute("bidProd",prolist);
					


					
					//**********************
					List<Product> proolist;
					proolist=service.getBidded(a);
					System.out.println(proolist);
					map.addAttribute("bidProd", proolist);
					List<String> names2=new ArrayList<>();String aaname;
					for(Product c:proolist)
					{
								aaname="-";
								try{aaname=c.getBidderList().get(c.getBidderList().size()-1).getName();}
									catch(Exception E)
								{
										;
								}
								names2.add(aaname);
					}	
					System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
					System.out.println(names2);
					map.addAttribute("names2", names2);
			
					
					//********************************
					
					
					
					
					
					
					
					
					
					return("userDashboard");
				}
	}
	
	
	@RequestMapping(path="/seeMy",method=RequestMethod.GET)
	public String SeeAll(ModelMap map,HttpServletRequest req,HttpServletResponse res)
	{
		HttpSession session=req.getSession();
		if(session.getAttribute("userlog")!=null)
		{
			User a=((User)session.getAttribute("userlog"));
			map.addAttribute("listMine",service.seeMine(a.getId()) );
			return "History";
		}
		return null;
		
	}
	
	@RequestMapping(path="/userDashboard", method=RequestMethod.GET)
	public  String login3(ModelMap map,HttpServletRequest req,HttpServletResponse res){	
		
		HttpSession session=req.getSession();
		if(session.getAttribute("userlog")!=null)
			{
				User a=((User)session.getAttribute("userlog"));
				List<Product> prolist;
				prolist=service.getProd(a);
				map.addAttribute("myProd",prolist);
				List<String> names=new ArrayList<>();String aname;
				for(Product c:prolist)
				{
					
						
							aname="-";
							try{aname=c.getBidderList().get(0).getName();}
								catch(Exception E)
							{
									;
							}
							System.out.print(names);
							names.add(aname);
						
				}	map.addAttribute("names",names);
				
				//**********************
				List<Product> proolist;
				proolist=service.getBidded(a);
				System.out.println(proolist);
				map.addAttribute("bidProd", proolist);
				List<String> names2=new ArrayList<>();String aaname;
				for(Product c:proolist)
				{
							aaname="-";
							try{aaname=c.getBidderList().get(c.getBidderList().size()-1).getName();}
								catch(Exception E)
							{;}
							names2.add(aaname);
				}	
				System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				System.out.println(names2);
				map.addAttribute("names2", names2);
		
				
				//********************************
				return "userDashboard";
			}
		
		String name= req.getParameter("login");
		String pwd= req.getParameter("pass");
		User a=service.checkUser(name, pwd);
			System.out.print("DOLPHIN"+a);
			if(a==null)
			{
				map.addAttribute("Errormsg", "No such account please Register");
				return("redirect:reg1");
			}
			else if(a.isRegistered()=='f')
			{
				map.addAttribute("message", "Wait for the admin to approve");
				return("redirect:/");
			}
			else
				{
				List<Product> prolist;
				prolist=service.getProd(a);
				/*retPro=service.getBidProd(a);*/
					map.addAttribute("user",a);
					map.addAttribute("myProd",prolist);
					System.out.println("hannuka"+prolist);
			/*		List<String> names=new ArrayList<>();String aname;
					for(Product c:prolist)
					{
						
							
								aname="-";
								try{aname=c.getBidderList().get(0).getName();}
									catch(Exception E)
								{
										;
								}
								System.out.print(name);
								names.add(aname);
							
					}	*/
					
					/*System.out.println("klklklklklklklk"+'\n'+names);*/
					session=req.getSession();
					session.setAttribute("userlog", a);
					map.addAttribute("myProd",prolist);
					/*map.addAttribute("anamebid",names);*/
					return("userDashboard");
				}
	}
	
	
	
	
	
	
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public  String login(ModelMap map,HttpServletRequest req){
		HttpSession session=req.getSession(false);
		if(session.getAttribute("userlog")!=null)
			return "userDashboard";
		
		return "login";
	}
	
	
	
	@GetMapping(path="adminLogin")
	public ModelAndView retAdmin()
	{
		ModelAndView modell=new ModelAndView("adminTools");
		List<User> ar=service.retUnapprovedList();
		System.out.println(ar);
		modell.addObject("unapproved",ar);
		return modell;
	}
	
	@GetMapping(path="delete/{co}")
	public ModelAndView deleteUser(@PathVariable(value="co") Integer id)
	{
		service.delete(id);
		return new ModelAndView("redirect:/adminLogin");
	}
	
	@GetMapping(path="approve/{co}")
	public ModelAndView approveUser(@PathVariable(value="co") Integer id)
	{
		service.approve(id);
		return new ModelAndView("redirect:/adminLogin");
	}
	
	
}
