package ma.upf.gestiondevises.controller;

import ma.upf.gestiondevises.entities.Devise;
import ma.upf.gestiondevises.service.DeviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/devises")
public class DeviseController {

    @Autowired
    private DeviseService deviseService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("devises", deviseService.findAll());
        model.addAttribute("devise", new Devise());
        return "devises";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Devise devise) {
        deviseService.save(devise);
        return "redirect:/devises";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Devise devise = deviseService.findById(id).orElse(new Devise());
        model.addAttribute("devise", devise);
        model.addAttribute("devises", deviseService.findAll());
        return "devises";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        deviseService.deleteById(id);
        return "redirect:/devises";
    }
}