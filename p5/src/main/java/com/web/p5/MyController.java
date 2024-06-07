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

import jakarta.servlet.http.HttpSession;

@Controller
public class MyController {

    @Autowired private memberRep mrep;
    @Autowired private productRep prep;

    @GetMapping("/member/list")
    public String memberList(Model mo) {
        mo.addAttribute("arr", mrep.findAll());
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
    
    @GetMapping("/login/check")
    public String loginCheck(HttpSession se, @RequestParam("id") String id, Model mo, RedirectAttributes re) {
        if (mrep.existsById(id)) {
            se.setAttribute("id", id);
            return "redirect:/menu";
        } else {
            re.addAttribute("msg", id + "는 미등록 아이디입니다. 확인 후 로그인 부탁드립니다.");
            re.addAttribute("url", "/login");
            return "redirect:/popup";
        }
    }
    @GetMapping("/menu") 
    public String menu(HttpSession se, Model mo) { 
    	mo.addAttribute("id", se.getAttribute("id"));
    	return "menu"; 
    }
    
    @GetMapping("/myinfo") 
    public String myinfo(HttpSession se, Model mo) { 
    	String id = (String)se.getAttribute("id"); 
    	mo.addAttribute("m",mrep.findById(id).get()); 
    	return "myinfo"; 
    }
    
    @GetMapping("/airinfo") 
    public String airinfo(Model mo) { 
    	mo.addAttribute("mcount",mrep.memberCount()); 
    	return "airinfo"; 
    }
    
    @GetMapping("/logout") 
    public String logout(HttpSession se, Model mo) { 
    	mo.addAttribute("id", se.getAttribute("id")); 
    	se.invalidate(); 
    	return "logout"; 
    }
    
    
    
    
}
