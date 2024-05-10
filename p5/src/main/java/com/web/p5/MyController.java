package com.web.p5;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @Autowired private memberRep mrep;
    @Autowired private productRep prep;

    @GetMapping("/member/list")
    public String memberList(Model model) {
        List<member> members = mrep.findAll();
        model.addAttribute("members", members);
        return "memberList";
    }
    @GetMapping("/product/list")
    public String productList(Model mo) {
        List<product> arr = prep.findAll();
        mo.addAttribute("arr", arr);
        return "productList";  
    }
}
