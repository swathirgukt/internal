package com.indianeagle.internal.controller;

import com.indianeagle.internal.dto.Peripheral;
import com.indianeagle.internal.service.PeripheralService;
import com.indianeagle.internal.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller to handle peripheral requests
 * Author: Taymur Shaikh
 * Since: 24/01/2020
 */

@Controller
@RequestMapping("/peripheral")
public class PeripheralController {
    @Autowired
    private PeripheralService peripheralService;

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping
    public String peripheral(Model model) {
        model.addAttribute("peripheral", new Peripheral());
        return "html/peripheral";
    }

    @PostMapping("/save")
    public String savePeripheral(ModelMap modelMap, @ModelAttribute Peripheral peripheral) {
        peripheralService.saveOrUpdate(peripheral);
        modelMap.addAttribute("success", "Save or Update peripheral was successful.");
        modelMap.addAttribute("peripheral", new Peripheral());
        return "html/peripheral";
    }

    @PostMapping("/search")
    public String searchPeripheral(ModelMap modelMap, @ModelAttribute Peripheral peripheral) {
        List<Peripheral> peripheralList = peripheralService.searchPeripherals(peripheral);

        peripheralList.forEach(p -> {
            p.setYearOfPurchase(DateUtils.convertDateInCstToIst(p.getYearOfPurchase()));
            p.setWarrantyDate(DateUtils.convertDateInCstToIst(p.getWarrantyDate()));
        });

        modelMap.addAttribute("peripheralList", peripheralList);
        return "html/fragment/peripheralResult";
    }

    @GetMapping("/edit/{id}")
    public String editPeripheral(ModelMap modelMap, @PathVariable("id") Long peripheralId) {
        Peripheral peripheral = peripheralService.findById(peripheralId);
        peripheral.setYearOfPurchase(DateUtils.convertDateInCstToIst(peripheral.getYearOfPurchase()));
        peripheral.setWarrantyDate(DateUtils.convertDateInCstToIst(peripheral.getWarrantyDate()));
        modelMap.addAttribute("peripheral", peripheral);
        return "html/peripheral";
    }


}

