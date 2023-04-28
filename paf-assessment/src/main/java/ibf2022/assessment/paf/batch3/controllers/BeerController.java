package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	BeerService beerService;

	//TODO Task 2 - view 0
	@GetMapping(path="/", produces="text/html")
	public String renderLandingPage(Model model) {
		List<Style> styles = beerService.getStyles();
		model.addAttribute("styles", styles);
		return "view0";
	}
	
	//TODO Task 3 - view 1
	

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}
