package com.example.myBoard.controller;

import com.example.myBoard.Service.ArticleService;
import com.example.myBoard.dto.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainCotroller {
    private final ArticleService articleService;
    public MainCotroller(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/")
    public String mainView(Model model){
        List<ArticleDto> articleDtos = articleService.showAll();
        model.addAttribute("dtos", articleDtos);
        return "articles/show_all";
    }
}
