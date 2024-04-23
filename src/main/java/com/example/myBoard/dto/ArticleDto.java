package com.example.myBoard.dto;


import com.example.myBoard.entity.Article;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {

    private Long id;
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Article fromArticleDto(ArticleDto dto){
        Article article = new Article();
        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setContent(dto.getContent());
        return article;
    }

    public static ArticleDto fromArticle(Article article){
        return new ArticleDto(
                article.getId(),
                article.getTitle(),
                article.getContent()
                );
    }


}
