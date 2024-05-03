package com.web.p5;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired
    private memberRep mrep;

    @GetMapping("/member/list")
    public String memberList(Model model) {
        List<member> members = mrep.findAll();
        model.addAttribute("members", members);
        return "memberList";
    }
}
