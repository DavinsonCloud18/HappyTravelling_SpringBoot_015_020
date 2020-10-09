package del.ac.id.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import del.ac.id.demo.jpa.LoginRepository;
import del.ac.id.demo.jpa.Maskapai;
import del.ac.id.demo.jpa.MaskapaiRepository;
import del.ac.id.demo.jpa.MaskapaiService;
import del.ac.id.demo.jpa.Penerbangan;
import del.ac.id.demo.jpa.PenerbanganRepository;
import del.ac.id.demo.jpa.PenerbanganService;
import del.ac.id.demo.jpa.Role;
import del.ac.id.demo.jpa.RoleRepository;
import del.ac.id.demo.jpa.User;
import del.ac.id.demo.jpa.UserRepository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
public class RegistrationController {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private LoginRepository loginRepository;
	private PenerbanganRepository penerbanganRepository;
	private MaskapaiRepository maskapaiRepository;
	private MaskapaiService maskapaiService;  
	private PenerbanganService penerbanganService;
	
	public RegistrationController(UserRepository userRepository, RoleRepository roleRepository,
			LoginRepository loginRepository, PenerbanganRepository penerbanganRepository, 
			MaskapaiRepository maskapaiRepository, MaskapaiService maskapaiService, PenerbanganService penerbanganService) {
		
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.loginRepository = loginRepository;
		this.penerbanganRepository = penerbanganRepository;
		this.maskapaiRepository = maskapaiRepository;
		this.maskapaiService = maskapaiService;
		this.penerbanganService = penerbanganService;
	}
	
	Date sekarang = new Date();
	java.sql.Date sqlTime = new java.sql.Date(sekarang.getTime());
	Timestamp stampTime = new Timestamp(sekarang.getTime());
	
	@RequestMapping(value="/loginIndex",method = RequestMethod.POST)
	public ModelAndView loginIndex(@ModelAttribute User user, BindingResult bindingResult, Model model,RedirectAttributes attribute) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		User userLogin = userRepository.findByUsername(user.getUsername());
		if(userLogin == null) {
			attribute.addFlashAttribute("NotRegistered", "Akun tidak terdaftar di database");
			return new ModelAndView("redirect:/login");
		}
		
		//masih harus ganti Pojo jadi atribut Date dan Time bukan datetime agar bisa dimasukkan ke mysql
		
		else {
			ModelAndView mv = new ModelAndView();
			List<Penerbangan> listPenerbangan = penerbanganRepository.findAll();
			System.out.println(listPenerbangan.size());
			System.out.println(userLogin.getUsername());
			System.out.println(userLogin.getRoleid());
			if(userLogin.getRoleid() == 1) {
				del.ac.id.demo.jpa.Login LoginDetail = new del.ac.id.demo.jpa.Login(
						userLogin.getUsername(),userLogin.getRoleid(),stampTime,1);
				loginRepository.save(LoginDetail);
				mv = new ModelAndView("indexAdmin");
				mv.addObject("user",userLogin);
				mv.addObject("flights", listPenerbangan);
			}
			else if(userLogin.getRoleid() == 2) {
				mv = new ModelAndView("index");
				mv.addObject("flights", listPenerbangan);
			}
			return mv;
		}
	}
	
	@GetMapping("/logout/{un}")
	public ModelAndView Logout(@ModelAttribute("user") User user,
		BindingResult bindingResult, Model model, @PathVariable(value="un") String username) {
		del.ac.id.demo.jpa.Login userLogin = loginRepository.findByUsername(username);
		userLogin.setIsactive(0);
		loginRepository.save(userLogin);
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user",new User());
		return mv;
	}
	
	@GetMapping("/loginIndex")
	public ModelAndView LoginIndex() {
		ModelAndView mv = new ModelAndView("index");
		mv.addObject("user",new User());
		return mv;
	}
	
	@GetMapping("/registration")
	public ModelAndView Registration() {
		List<Role> listRoles = roleRepository.findAll();
		System.out.println(listRoles.size());
		
		ModelAndView mv = new ModelAndView("registration");
		mv.addObject("roles", listRoles);
		mv.addObject("user",new User());
		return mv;
	}
	
	@RequestMapping(value="/registration",method = RequestMethod.POST)
	public ModelAndView registrationSubmit(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("user",user);
		ModelAndView mv = new ModelAndView("login");
		userRepository.save(user);
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView Login() {
		List<Role> listRoles = roleRepository.findAll();
		System.out.println(listRoles.size());
		
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user",new User());
		return mv;
	}
	
	@GetMapping("/")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("user",new User());
		return mv;
	}
	
	@RequestMapping(value="/",method = RequestMethod.POST)
	public String Dashboard(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("user",user);
		return "redirect:login";
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public String loginSubmit(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("user",user);
		loginRepository.findByUsername(user.getUsername());
		return "redirect:index";
	}
	
	@RequestMapping(value="/bookPenerbangan",method = RequestMethod.POST)
	public ModelAndView bookPenerbangan(@ModelAttribute Penerbangan penerbangan, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("penerbangan",penerbangan);
		ModelAndView mv = new ModelAndView("index");
		penerbanganRepository.save(penerbangan);
		return mv;
	}
	
	
	//admin list seluruh maskapai
	@RequestMapping("/allMaskapai")
	public ModelAndView allMaskapai(Model model) {
		List<Maskapai> listMaskapai = maskapaiRepository.findAll();
		ModelAndView mv = new ModelAndView("allMaskapai");
		
		System.out.println(listMaskapai.size());
		model.addAttribute("listMaskapai", listMaskapai);
		return mv;
		
	}
	
	
	//admin tambah maskapai
	@GetMapping("/maskapaiAdmin")
	public ModelAndView maskapaiAdmin() {	
		ModelAndView mv = new ModelAndView("maskapaiAdmin");
		mv.addObject("maskapaiAdmin",new Maskapai());
		return mv;
	}
	
	@RequestMapping(value="/maskapaiAdmin",method = RequestMethod.POST)
	public ModelAndView maskapaiAdminSubmit(@ModelAttribute Maskapai maskapai, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("maskapaiAdmin",maskapai);
		ModelAndView mv = new ModelAndView("maskapaiAdmin");
		maskapaiRepository.save(maskapai);
		return mv;
	}
	
	//admin edit maskapai
	@GetMapping("/edit/{id}")
	public ModelAndView showUpdateForm(@PathVariable("id") int no, Model model) { 
		ModelAndView mv = new ModelAndView("updateMaskapai");
		
		Maskapai maskapai = maskapaiService.getMaskapaiById(no);
		
		mv.addObject("maskapai", maskapai );	 
		return mv; 
		
	}
		 
	//admin save update maskapai	 
	@RequestMapping(value = "/save", method = RequestMethod.POST) 
	public String updateMaskapai(@ModelAttribute("maskapai") Maskapai maskapai) { 
		maskapaiService.saveOrUpdate(maskapai);
		
		return "redirect:/allMaskapai";
	}
		
	
	 
	//admin delete maskapai
	@GetMapping("/delete/{id}")
	public String deleteMaskapai(@PathVariable("id") int noMaskapai, Model model) {
	    
		maskapaiService.delete(noMaskapai);
		
	    return "redirect:/allMaskapai";
	}
	
	//admin list seluruh Penerbangan
	@RequestMapping("/allPenerbangan")
	public ModelAndView allPenerbangan(Model model) {
		List<Penerbangan> listPenerbangan = penerbanganRepository.findAll();
		ModelAndView mv = new ModelAndView("allPenerbangan");
		
		System.out.println(listPenerbangan.size());
		model.addAttribute("listPenerbangan", listPenerbangan);
		return mv;
		
	}
	
	
	//admin tambah penerbangan
	@GetMapping("/penerbanganAdmin")
	public ModelAndView penerbanganAdmin() {	
		ModelAndView mv = new ModelAndView("penerbanganAdmin");
		mv.addObject("penerbanganAdmin",new Penerbangan());

		List<Maskapai> listMaskapai = maskapaiRepository.findAll();
		
		mv.addObject("maskapai", listMaskapai);
		
		return mv;
	}
	
	@RequestMapping(value="penerbanganAdmin",method = RequestMethod.POST)
	public ModelAndView penerbanganAdminSubmit(@ModelAttribute Penerbangan penerbangan, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("penerbanganAdmin",penerbangan);
		ModelAndView mv = new ModelAndView("penerbanganAdmin");
		penerbanganRepository.save(penerbangan);
		return mv;
	}
	
	//admin edit penerbangan
		@GetMapping("/editpenerbangan/{id}")
		public ModelAndView editUpdate(@PathVariable("id") int no, Model model) { 
			ModelAndView mv = new ModelAndView("updatePenerbangan");
			
			Penerbangan penerbangan = penerbanganService.getPenerbanganById(no);
			
			mv.addObject("penerbangan", penerbangan );	 
			return mv; 
			
		}
			 
		//admin save update penerbangan	 
		@RequestMapping(value = "/savepenerbangan", method = RequestMethod.POST) 
		public String updatePenerbangan(@ModelAttribute("penerbangan") Penerbangan penerbangan) { 
			penerbanganService.saveOrUpdate(penerbangan);
			
			return "redirect:/allMaskapai";
		}
		
		//admin delete penerbangan
		@GetMapping("/deletepenerbangan/{id}")
		public String deletePenerbangan(@PathVariable("id") int idPenerbangan, Model model) {
		    
			penerbanganService.delete(idPenerbangan);
			
		    return "redirect:/allMaskapai";
		}

	
}
