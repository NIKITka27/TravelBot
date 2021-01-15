package com.project.travel.bot.Controllers;


import com.project.travel.bot.Repository.TravelRepository;
import com.project.travel.bot.model.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class TravelController {

    @Autowired
    private TravelRepository travelRepository;


    @GetMapping("/travel")
    public ModelAndView mainPage(Model model) {
        model.addAttribute("travels", travelRepository.findAll());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("travelList");

        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addPage(Model model) {
        model.addAttribute("travel", new Travel());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addTravel");

        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addTravel(@Valid Travel travel, BindingResult result, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            modelAndView.setViewName("addTravel");
        } else {
            modelAndView.setViewName("travelList");
            travelRepository.save(travel);
            model.addAttribute("travels", travelRepository.findAll());
        }
        return modelAndView;
    }

    @GetMapping("edit/{id}")
    public ModelAndView showUpdateForm(@PathVariable("id") long id, Model model) {
        Travel travel = travelRepository.findById(id);
        model.addAttribute("travel", travel);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updateTravel");
        return modelAndView;

    }

    @PostMapping("update/{id}")
    public ModelAndView updateTravel(@PathVariable("id") long id, @Valid Travel travel, BindingResult result,
                                     Model model) {
        ModelAndView modelAndView = new ModelAndView();

        if (result.hasErrors()) {
            model.addAttribute("travel", travel);
            modelAndView.setViewName("updateTravel");
        } else {
            travelRepository.save(travel);
            model.addAttribute("travels", travelRepository.findAll());
            modelAndView.setViewName("travelList");
        }

        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteTravel(@PathVariable("id") long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        Travel travel = travelRepository.findById(id);
        travelRepository.delete(travel);
        model.addAttribute("travels", travelRepository.findAll());
        modelAndView.setViewName("travelList");

        return modelAndView;
    }
}