package spring.formation.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BidonController {

//	@RequestMapping(value = "/bid", method = RequestMethod.GET)
	@GetMapping("/bid")
	public String bidon(@RequestParam String nom, @RequestParam(required = false, defaultValue = "0") int age, Model model) {
		model.addAttribute("param1", nom);
		model.addAttribute("age", age);

		return "bidon/pageBidon";
	}

}
