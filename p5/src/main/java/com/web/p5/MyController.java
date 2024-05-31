package com.web.p5;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    		@Nullable String[] foods,
    		@RequestParam("face") String face,
    		@RequestParam("grade") String grade,
    		@RequestParam("head") String head,
    		@RequestParam("promote") String promote, Model mo) {
    	mo.addAttribute("age", age);
    	mo.addAttribute("gender", gender);
    	if (foods == null)
    		mo.addAttribute("foods", "없음");
    	else
    		mo.addAttribute("foods", foods);
    	mo.addAttribute("face", face);
    	mo.addAttribute("grade", grade);
    	mo.addAttribute("head", head);
    	mo.addAttribute("promote", promote);
    	return "iamAnswer";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "login";
    }
    
    @GetMapping("/member")
    public String member() {
        return "member";
    }
    
    @GetMapping("/member/insert")
    public String memberInsert(
        @RequestParam("id") String id,
        @RequestParam("pw") String pw,
        @RequestParam("name") String name,
        @RequestParam("phone") String phone,
        RedirectAttributes re) {
        
        if (mrep.existsById(id)) {
            re.addAttribute("msg", id + "는 이미 사용되고 있는 아이디입니다.");
            re.addAttribute("url", "back");
        } 
        else {
            member m = new member();
            m.id = id;
            m.pw = pw;
            m.name = name;
            m.phone = phone;
            m.mileage = 0;
            mrep.save(m);
            
            re.addAttribute("msg", id + "님, 반갑습니다!! (로그인 화면으로 이동)");
            re.addAttribute("url", "/login");
        }
        return "redirect:/popup";
    }
    
    @GetMapping("/popup")
    public String popup(
    		@RequestParam("msg") String msg, 
    		@RequestParam("url") String url, Model mo) {
        mo.addAttribute("msg", msg);
        mo.addAttribute("url", url);
        return "popup";
    }
    
}
