package com.example.myBoard.Service;

import com.example.myBoard.dto.ArticleDto;
import com.example.myBoard.entity.Article;
import com.example.myBoard.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<ArticleDto> showAll() {
        List<ArticleDto> dtos = articleRepository.findAll()
                .stream()
                .map(x -> ArticleDto.fromArticle(x))
                .toList();
        return dtos;
    }

    public void insert(ArticleDto dto) {
        Article article = dto.fromArticleDto(dto);
        articleRepository.save(article);
    }

    public void update(Article article) {
        articleRepository.save(article);
    }

    public void delete(Long id) {
        articleRepository.deleteById(id);
    }

    public Object findId(Long id) {
        return ArticleDto.fromArticle(articleRepository.findById(id).orElse(null));
    }
}
