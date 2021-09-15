package com.example.testmd4.controller;

import com.example.testmd4.model.City;
import com.example.testmd4.service.ICityService;
import com.example.testmd4.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CityController {
    @Autowired
    private ICountryService countryService;
    @Autowired
    private ICityService cityService;

    @GetMapping("/")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("cities",cityService.findAll());
        return modelAndView;
    }

    @GetMapping("/createCity")
    public ModelAndView createForm(){
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("city",new City());
        modelAndView.addObject("countries",countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/createCity")
    public ModelAndView createCity(@Valid @ModelAttribute("city") City city, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/create");
            modelAndView.addObject("city",city);
            modelAndView.addObject("countries",countryService.findAll());
            modelAndView.addObject("message","City Create Failed please check your information");
            return modelAndView;
        } else {
            cityService.save(city);
            ModelAndView modelAndView = new ModelAndView("/home");
            modelAndView.addObject("cities",cityService.findAll());
            modelAndView.addObject("message","City Created");
            return modelAndView;
        }
    }

    @GetMapping("/editCity/{id}")
    public ModelAndView editForm(@PathVariable long id){
        City city = cityService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("city",city);
        modelAndView.addObject("countries",countryService.findAll());
        return modelAndView;
    }

    @PostMapping("/editCity/{id}")
    public ModelAndView edit(@Valid @ModelAttribute("city") City city, BindingResult bindingResult){
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("/edit");
            modelAndView.addObject("city",city);
            modelAndView.addObject("countries",countryService.findAll());
            modelAndView.addObject("message","City Edit Failed please check your information");
            return modelAndView;
        } else {
            cityService.save(city);
            ModelAndView modelAndView = new ModelAndView("/home");
            modelAndView.addObject("cities",cityService.findAll());
            modelAndView.addObject("message","City Edited");
            return modelAndView;
        }
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("/detail");
        modelAndView.addObject("city", cityService.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/deleteCity/{id}")
    public ModelAndView deleteConfirm(@PathVariable long id){
        ModelAndView modelAndView = new ModelAndView("/delete");
        modelAndView.addObject("city",cityService.findById(id).get());
        return modelAndView;
    }

    @PostMapping("/deleteCity/{id}")
    public ModelAndView delete(@PathVariable long id){
        cityService.delete(id);
        ModelAndView modelAndView = new ModelAndView("/home");
        modelAndView.addObject("cities",cityService.findAll());
        modelAndView.addObject("message","City Deleted");
        return modelAndView;
    }
}
