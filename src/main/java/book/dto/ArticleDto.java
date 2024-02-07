package book.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ArticleDto {
    private String title;
    private String content;

}
