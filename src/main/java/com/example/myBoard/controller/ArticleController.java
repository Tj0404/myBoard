package com.example.myBoard.controller;

import com.example.myBoard.Service.ArticleService;
import com.example.myBoard.dto.ArticleDto;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.jdbc.mutation.spi.BindingGroup;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("insert")
    public String insertView(Model model){
        model.addAttribute("dto", new ArticleDto());
        return "/articles/new";
    }
    @PostMapping("insert")
    public String insert(@Valid @ModelAttribute("dto")ArticleDto dto,
                         BindingResult bindingResult,
                         Model model){
        if(bindingResult.hasErrors()){
            log.info("------Validation error -------");
            return "/articles/new";
        }
        model.addAttribute("dto", new ArticleDto());
        log.info("@@@@@@result" + dto.toString());
        articleService.insert(dto);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateView(@PathVariable("id")Long id,
                             Model model){
        model.addAttribute("dto", articleService.findId(id));
        return "/articles/update";
    }

    @PostMapping("update")
    public String update(@Valid @ModelAttribute("dto")ArticleDto dto,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.info("------Validation error -------");
            return "update";
        }
        articleService.update(dto.fromArticleDto(dto));
        return "redirect:/";
    }

    @GetMapping("delete")
    public String delete(@RequestParam("deleteId")Long id){
        articleService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailView(@PathVariable("id")Long id,
                             Model model){
        model.addAttribute("dto", articleService.findId(id));
        return "/articles/detail";
    }
}
