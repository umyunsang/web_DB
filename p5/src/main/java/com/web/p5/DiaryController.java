package com.web.p5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class DiaryController {
	
	@Autowired
	private diaryRep drep;
	
	@GetMapping("/diary")
	public String diary(){
		return "diary";
	}
	@GetMapping("/diary/insert")
    public String diaryInsert(@RequestParam("je") String je,
    						@RequestParam("nae") String nae, 
    						RedirectAttributes re) {
        diary m = new diary();
        m.je = je;
        m.nae = nae;
        drep.save(m);
        
        re.addAttribute("msg", "저장되었습니다.");
        return "redirect:/diary/popup";
    }
	@GetMapping("/diary/popup")
	public String diaryPopup(@RequestParam("msg") String msg, Model mo){
		mo.addAttribute("msg", msg);
		return "diaryPopup";
	}
	@GetMapping("/diary/list")
	public String diaryList(Model mo){
		mo.addAttribute("arr", drep.findAll());
		return "diaryList";
	}

}
