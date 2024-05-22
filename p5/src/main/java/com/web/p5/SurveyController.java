package com.web.p5;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class SurveyController {

    @GetMapping("/survery1")
    public String survery1() {
        return "survery1";
    }

    @GetMapping("/survery2")
    public String survery2(HttpSession se, @RequestParam("mid") String mid) {
        se.setAttribute("mid", mid);
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
        mo.addAttribute("mid", se.getAttribute("mid"));
        mo.addAttribute("artist", se.getAttribute("artist"));
        mo.addAttribute("musician", se.getAttribute("musician"));
        return "survery5";
    }

}
