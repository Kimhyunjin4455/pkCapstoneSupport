package org.zerock.freview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.freview.dto.FoodReviewDTO;
import org.zerock.freview.dto.FoodReviewImageDTO;
import org.zerock.freview.dto.PageRequestDTO;
import org.zerock.freview.entity.FoodReviewImage;
import org.zerock.freview.service.FoodReviewService;

@Controller
@RequestMapping("/foodreview")
@Log4j2
@RequiredArgsConstructor // 자동 주입을 위한 어노테이션
public class FoodReviewController {

    private final FoodReviewService foodReviewService;
    @GetMapping({"/exTemplate","/exSidebar"})
    public void exSideBar(){
        log.info("exSideBar.................");
    }

    @GetMapping("/")
    public String index(){

        return "redirect:/guestbook/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        log.info("pageRequestDTO: " + pageRequestDTO);
        model.addAttribute("result", foodReviewService.getList(pageRequestDTO));
    }

    @GetMapping("/register")
    public void register(){
    }


    // post방식으로 전달된 파라미터들을 FoodReviewDTO로 수집해서 FoodReviewService 타입 객체의 register() 호출
    @PostMapping("/register")
//    public String register(Long fno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model){
    public String register(FoodReviewDTO foodReviewDTO, RedirectAttributes redirectAttributes){
//        log.info("fno: "+ fno);
//        FoodReviewDTO foodReviewDTO = foodReviewService.getFoodReview(fno);
//
//        model.addAttribute("dto", foodReviewDTO);
        log.info("foodReviewDTO의 값은->" +foodReviewDTO);

        Long fno = foodReviewService.register(foodReviewDTO);
        redirectAttributes.addFlashAttribute("msg", fno);
        return "redirect:/foodreview/list";
    }


}
