package net.javaguides.kordianspetitions;

import net.javaguides.kordianspetitions.models.Petition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private List<Petition> petitions = new ArrayList<>();

    public HomeController() {
        petitions.add(new Petition("1", "Save the Earth", "Protect the environment for future generations.", "John"));
        petitions.add(new Petition("2", "Animal Welfare", "Support the rights of animals globally.", "Alice"));
    }

    @GetMapping("/")
    public String redirectToCreate() {
        return "redirect:/create";
    }

    @GetMapping("/create")
    public String showCreatePetitionPage() {
        return "create-petition";
    }

    @PostMapping("/create")
    public String createPetition(@RequestParam String title, @RequestParam String description, @RequestParam String creator) {
        String id = String.valueOf(petitions.size() + 1);
        petitions.add(new Petition(id, title, description, creator));
        return "redirect:/view";
    }

    @GetMapping("/view")
    public String viewAllPetitions(Model model) {
        model.addAttribute("petitions", petitions);
        return "view-petitions";
    }

    @GetMapping("/search")
    public String showSearchPage() {
        return "search-petitions";
    }

    @PostMapping("/search")
    public String searchPetitions(@RequestParam String keyword, Model model) {
        List<Petition> results = new ArrayList<>();
        for (Petition petition : petitions) {
            if (petition.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    petition.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(petition);
            }
        }
        model.addAttribute("results", results);
        return "search-results";
    }

    @GetMapping("/view/{id}")
    public String viewPetition(@PathVariable String id, Model model) {
        Petition petition = petitions.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        model.addAttribute("petition", petition);
        return "view-petition";
    }

    @PostMapping("/sign/{id}")
    public String signPetition(@PathVariable String id, @RequestParam String name, @RequestParam String email) {
        System.out.println("Petition signed by " + name + " (" + email + ")");
        return "redirect:/view";
    }
}
