package ma.upf.gestiondevises.controller;

import ma.upf.gestiondevises.entities.Devise;
import ma.upf.gestiondevises.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversionController {

    @Autowired
    private DeviseService deviseService;

    @GetMapping("/convert")
    public String showConvertForm(Model model) {
        model.addAttribute("devises", deviseService.findAll());
        return "convert";
    }

    @PostMapping("/convert")
    public String convert(@RequestParam String fromCode, 
                          @RequestParam String toCode, 
                          @RequestParam double montant, 
                          Model model) {
        Devise fromDevise = deviseService.findByCode(fromCode);
        Devise toDevise = deviseService.findByCode(toCode);
        
        if (fromDevise == null || toDevise == null) {
            model.addAttribute("error", "Devises invalides");
        } else {
            double result = montant * (toDevise.getTaux() / fromDevise.getTaux());
            model.addAttribute("result", result);
            model.addAttribute("fromCode", fromCode);
            model.addAttribute("toCode", toCode);
            model.addAttribute("montant", montant);
        }
        
        model.addAttribute("devises", deviseService.findAll());
        return "convert";
    }
}