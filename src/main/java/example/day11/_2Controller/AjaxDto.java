package example.day11._2Controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AjaxDto {
    private int id;
    private String content;
}
