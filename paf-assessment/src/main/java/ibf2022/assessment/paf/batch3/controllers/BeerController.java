package ibf2022.assessment.paf.batch3.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ibf2022.assessment.paf.batch3.exceptions.DatabaseException;
import ibf2022.assessment.paf.batch3.models.Beer;
import ibf2022.assessment.paf.batch3.models.Brewery;
import ibf2022.assessment.paf.batch3.models.Order;
import ibf2022.assessment.paf.batch3.models.OrderItem;
import ibf2022.assessment.paf.batch3.models.Style;
import ibf2022.assessment.paf.batch3.services.BeerService;

@Controller
public class BeerController {

	@Autowired
	BeerService beerService;

	//TODO Task 2 - view 0
	@GetMapping(path="/", produces="text/html")
	public String renderLandingPage(Model model) throws DatabaseException {		
		try {
			List<Style> styles = beerService.getStyles();
			model.addAttribute("styles", styles);
			return "view0";
		} catch (DatabaseException ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}
	
	//TODO Task 3 - view 1
	@GetMapping(path="/beer/style/{styleId}", produces="text/html")
	public String renderViewOne(@PathVariable String styleId, @RequestParam String styleName, Model model) throws DatabaseException {

		try {
			List<Beer> beers = beerService.getBreweriesByBeer(Integer.parseInt(styleId));
			model.addAttribute("styleName", styleName);
			model.addAttribute("beers", beers);
			return "view1";
		} catch (DatabaseException ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}

	//TODO Task 4 - view 2
	// Use ModelAndView
	@GetMapping(path="/brewery/{breweryId}")
	public ModelAndView renderViewTwo(@PathVariable String breweryId, Model model) throws DatabaseException {

		try {
			Optional<Brewery> opt = beerService.getBeersFromBrewery(Integer.parseInt(breweryId));
			
			if (opt.isEmpty()) {
				ModelAndView mav = new ModelAndView("error.html");
				mav.addObject("errorMessage", "Brewery id %s not found".formatted(breweryId));
				mav.setStatus(HttpStatus.NOT_FOUND);
				return mav;
			}
			
			ModelAndView mav = new ModelAndView("view2.html");
			mav.addObject("brewery", opt.get());
			mav.setStatus(HttpStatus.OK);
			return mav;
		} catch (DatabaseException ex) {
			ModelAndView mav = new ModelAndView("error.html");
				mav.addObject("errorMessage", ex.getMessage());
				mav.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
				return mav;
		}
	}

	// String version
	// @GetMapping(path="/beer/brewery/{breweryId}", produces="text/html")
	// public String renderViewTwo(@PathVariable int breweryId, @RequestParam String breweryName, Model model) {
	// 	Optional<Brewery> opt = beerService.getBeersFromBrewery(breweryId);

	// 	if (opt.isEmpty()) {
	// 		model.addAttribute("breweryName", "Brewery Not Found");
	// 	}

	// 	model.addAttribute("breweryName", breweryName);
	// 	return "view2";
	// }
	

	//TODO Task 5 - view 2, place order
	@PostMapping(path="/brewery/{breweryId}/order")
	public String postOrderHandler(@PathVariable String breweryId, @RequestBody MultiValueMap<String, String> form, Model model) throws DatabaseException {
		
		try {	
			Order order = new Order();
			order.setDate(new Date());
			order.setBreweryId(Integer.parseInt(breweryId));
		
			for (String s : form.keySet()) {
				if (form.getFirst(s).isBlank()) {
					continue; // quantity fields not filled in should not be considered
				} 
				OrderItem orderItem = new OrderItem(Integer.parseInt(s), Integer.parseInt(form.getFirst(s)));
				order.addOrderItem(orderItem);
			}
		
			String orderId = beerService.placeOrder(order);
			model.addAttribute("orderId", orderId);
			return "view3";
		} catch (DatabaseException ex) {
			model.addAttribute("errorMessage", ex.getMessage());
			return "error";
		}
	}
}
