package com.pixelTrice.elastic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class UIController {

    @Autowired
    private ElasticSearchQuery elasticSearchQuery;

    @GetMapping("/")
    public String viewHomePage(Model model) throws IOException {
        model.addAttribute("listProductDocuments",elasticSearchQuery.searchAllDocuments());
        return "index";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute("poll") Product poll) throws IOException {
        elasticSearchQuery.createOrUpdateDocument(poll);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) throws IOException {

        Product poll = elasticSearchQuery.getDocumentById(id);
        model.addAttribute("poll", poll);
        return "updateProductDocument";
    }

    @GetMapping("/showNewProductForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Product poll = new Product();
        model.addAttribute("poll", poll);
        return "newProductDocument";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(value = "id") String id) throws IOException {

        this.elasticSearchQuery.deleteDocumentById(id);
        return "redirect:/";
    }
}
