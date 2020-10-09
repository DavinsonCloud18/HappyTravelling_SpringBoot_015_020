package del.ac.id.demo.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import del.ac.id.demo.jpa.LoginRepository;
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
	
	public RegistrationController(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}
	
	
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
		else {
			ModelAndView mv = new ModelAndView();
			List<Role> listRoles = roleRepository.findAll();
			System.out.println(listRoles.size());
			if(userLogin.getRoleid() == 1) {
				mv = new ModelAndView("indexAdmin");
				mv.addObject("roles", listRoles);
			}
			else if(userLogin.getRoleid() == 2) {
				mv = new ModelAndView("index");
				mv.addObject("roles", listRoles);
			}
			return mv;
		}
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
	public String registrationSubmit(@ModelAttribute User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			System.out.println("Error");
		}
		model.addAttribute("user",user);
		userRepository.save(user);
		return "redirect:login";
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
	
}
