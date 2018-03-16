package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    MessageRepository messageRepository;

    @RequestMapping("/")
    public String messages(Model model) {
        model.addAttribute("messages", messageRepository.findAll());
        return "listmessages";
    }

    @GetMapping("/add")
    public String addMessage(Model model) {
        System.out.println(new Message());
        model.addAttribute("messageobject", new Message());
        return "addMessage";
    }

    @PostMapping("/process")
    public String processForm(@Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "addMessage";
        }
        messageRepository.save(message);
        return "redirect:/";
    }
}
