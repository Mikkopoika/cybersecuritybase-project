package sec.project.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.config.CustomUserDetailsService;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class SignupController {
    
    
    private CustomUserDetailsService users = new CustomUserDetailsService();
    @Autowired
    private SignupRepository signupRepository;

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loadLogin() {
        return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(@RequestParam String name, @RequestParam String password) {
        users.loadUserByUsername(name);
        // This lists the content of repository to the output.
        List<Signup> nimet = signupRepository.findAll();
        for (int i = 0; i < nimet.size(); i++) {
            System.out.println("name: " + nimet.get(i).getName() + " and address:  " + nimet.get(i).getAddress());
        }
        
        return "secret";
    }
    
    
    @RequestMapping(value = "/secret", method = RequestMethod.GET)
    public String loadSecret() {
        return "secret";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm() {
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        Signup signup = signupRepository.save(new Signup(name, address));
        // This lists the content of repository to the output.
        List<Signup> nimet = signupRepository.findAll();
        for (int i = 0; i < nimet.size(); i++) {
            System.out.println("name: " + nimet.get(i).getName() + " and address:  " + nimet.get(i).getAddress());
        }
        
        return "redirect:/done/" + signup.getId();
        
    }
    
    @RequestMapping(value = "/done/{id}", method = RequestMethod.GET)
    public String loaddone(Model model, @PathVariable Long id) {
        model.addAttribute("user", signupRepository.findOne(id));
        
        return "done";
    }
    

}
