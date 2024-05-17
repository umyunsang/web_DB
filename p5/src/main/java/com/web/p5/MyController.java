package com.web.p5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @GetMapping("/iam")
    public String iam() {
    	return "iam";
    }
    
    @PostMapping("/iam/answer")
    public String iamAnswer(@RequestParam("age") String age,
    		@RequestParam("gender") String gender,
    		@RequestParam("foods") String foods,
    		@RequestParam("face") String face,
    		@RequestParam("grade") String grade,
    		@RequestParam("head") String head,
    		@RequestParam("promote") String promote, Model mo) {
    	mo.addAttribute("age", age);
    	mo.addAttribute("gender", gender);
    	mo.addAttribute("foods", foods);
    	mo.addAttribute("face", face);
    	mo.addAttribute("grade", grade);
    	mo.addAttribute("head", head);
    	mo.addAttribute("promote", promote);
    	return "iamAnswer";
    }
}
