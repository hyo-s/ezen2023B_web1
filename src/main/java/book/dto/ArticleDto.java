package book.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class ArticleDto {
    private Long id;
    private String title;
    private String content;

}
