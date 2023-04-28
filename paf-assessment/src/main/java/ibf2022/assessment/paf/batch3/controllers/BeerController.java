package ibf2022.assessment.paf.batch3.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ibf2022.assessment.paf.batch3.models.Beer;
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
	@GetMapping(path="/beer/style/{styleId}")
	public String renderViewOne(@PathVariable int styleId, @RequestParam String styleName, Model model) {
		List<Beer> beers = beerService.getBreweriesByBeer(styleId);
		model.addAttribute("styleName", styleName);
		model.addAttribute("beers", beers);
		return "view1";
	}

	//TODO Task 4 - view 2

	
	//TODO Task 5 - view 2, place order

}
