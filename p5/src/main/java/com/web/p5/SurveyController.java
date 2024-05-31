package com.web.p5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class SurveyController {
	
	@Autowired
	private surveyRep srep;

    @GetMapping("/survery1")
    public String survery1() {
        return "survery1";
    }

    @GetMapping("/survery2")
    public String survery2(HttpSession se, @RequestParam("userid") String userid) {
        se.setAttribute("userid", userid);
        return "survery2";
    }

    @GetMapping("/survery3")
    public String survery3(HttpSession se, @RequestParam("artist") String artist) {
        se.setAttribute("artist", artist);
        return "survery3";
    }

    @GetMapping("/survery4")
    public String survery4(HttpSession se, @RequestParam("musician") String musician) {
        se.setAttribute("musician", musician);
        return "survery4";
    }

    @GetMapping("/survery5")
    public String survery5(HttpSession se, Model mo) {
    	
    	//* 세션에서 꺼내서 바로 모델에 넣을 때는 형변환이 필요 없습니다.
    	//* 중간에 지역변수 userid를 통할 때는 형변환이 필요합니다.
    	//* 세션이나 모델에는 데이터타입이 Object(자바 상속관계 젤 우선)입니다. 
    	String userid = (String)se.getAttribute("userid");
    	String artist = (String)se.getAttribute("artist");
    	String musician = (String)se.getAttribute("musician");
    	
        mo.addAttribute("userid", userid);
        mo.addAttribute("artist", artist);
        mo.addAttribute("musician", musician);
        
        if (userid != null) {
        	survey s = new survey();
        	s.userid = userid;
            s.artist = artist;
            s.musician = musician;
            srep.save(s);
        }
        return "survery5";
    }
    
    @GetMapping("/survey/list")
    public String surveyList(Model mo) {
    	mo.addAttribute("arr", srep.findAll());
    	return "surveyList";
    }

}
