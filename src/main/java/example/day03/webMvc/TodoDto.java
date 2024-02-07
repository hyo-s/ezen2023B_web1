package example.day03.webMvc;   // PACKAGE NAME

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TodoDto {  // CLASS START

    private int id;
    private String content;
    private String deadline;
    private boolean state;

}   // CLASS END
