package com.sergiovanovi.controller;

import com.sergiovanovi.model.User;
import com.sergiovanovi.service.MeterService;
import com.sergiovanovi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MeterController {

    private final MeterService meterService;
    private final UserService userService;
    private int currentPageUser = 0;
    private final static int METERS_ON_PAGE = 20;
    private final static int USERS_ON_PAGE = 10;

    @Autowired
    public MeterController(MeterService meterService, UserService userService) {
        this.meterService = meterService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String home(){
        return "redirect:/users/0";
    }

    @RequestMapping("/meters/{page}")
    public String listMeters(@PathVariable("page") int page, Model model) {
        long maxPage = (meterService.numberOfMeters() - 1) / METERS_ON_PAGE;
        model.addAttribute("meterList", meterService.listOfMeter(page, METERS_ON_PAGE));
        model.addAttribute("page", page);
        model.addAttribute("maxPage", maxPage);
        return "meters";
    }

    @RequestMapping("/users/{page}")
    public String listUsers(@PathVariable("page") int page, Model model) {
        currentPageUser = page;
        long maxPage = (userService.numberOfMeters() - 1) / USERS_ON_PAGE;
        model.addAttribute("userList", userService.listUsers(page, USERS_ON_PAGE));
        model.addAttribute("user", new User());
        model.addAttribute("page", page);
        model.addAttribute("maxPage", maxPage);
        return "users";
    }

    @RequestMapping("/add")
    public String addAndUpdateUser(@ModelAttribute("user") User user) {
        long maxPage = (userService.numberOfMeters() - 1) / USERS_ON_PAGE;
        if (user.getId() == 0) {
            userService.addUser(user);
            return "redirect:/users/" + maxPage;
        } else {
            userService.updateUser(user);
            return "redirect:/users/" + currentPageUser;
        }
    }

    @RequestMapping("/remove/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return "redirect:/users/" + currentPageUser;
    }

    @RequestMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") int id, Model model){
        long maxPage = (userService.numberOfMeters() - 1) / USERS_ON_PAGE;
        model.addAttribute("userList", userService.listUsers(currentPageUser, USERS_ON_PAGE));
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("page", currentPageUser);
        model.addAttribute("maxPage", maxPage);
        return "users";
    }
}
