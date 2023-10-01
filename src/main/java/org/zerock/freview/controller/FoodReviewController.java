package org.zerock.freview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.freview.dto.PageRequestDTO;
import org.zerock.freview.service.FoodReviewService;

@Controller
@RequestMapping("/foodreview")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class FoodReviewController {

    private final FoodReviewService service;
    @GetMapping({"/exTemplate","/exSidebar"})
    public void exSideBar(){
        log.info("exSideBar.................");
    }

    @GetMapping("/")
    public String index(){

        return "redirect:/guestbook/list";
    }

    @GetMapping("list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("list..............." + pageRequestDTO);
        model.addAttribute("result", service.getList(pageRequestDTO));
    }


}
