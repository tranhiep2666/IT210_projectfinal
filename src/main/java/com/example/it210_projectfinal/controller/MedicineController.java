package com.example.it210_projectfinal.controller;

import com.example.it210_projectfinal.dto.MedicineRequest;
import com.example.it210_projectfinal.entity.Medicine;
import com.example.it210_projectfinal.service.MedicineService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public String list(Model model) {

        model.addAttribute(
                "medicines",
                medicineService.getAll()
        );

        return "medicine/list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {

        model.addAttribute(
                "medicine",
                new MedicineRequest()
        );

        return "medicine/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid
            @ModelAttribute("medicine")
            MedicineRequest request
    ) {

        medicineService.create(request);

        return "redirect:/admin/medicines";
    }

    @GetMapping("/edit/{id}")

    public String editPage(

            @PathVariable Long id,

            Model model

    ) {

        Medicine medicine = medicineService.getById(id);

        model.addAttribute("medicine", medicine);

        return "medicine/edit";

    }

    @PostMapping("/edit/{id}")

    public String edit(

            @PathVariable Long id,

            @Valid

            @ModelAttribute("medicine")

            MedicineRequest request

    ) {

        medicineService.update(id, request);

        return "redirect:/admin/medicines";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        medicineService.delete(id);

        return "redirect:/admin/medicines";
    }
}