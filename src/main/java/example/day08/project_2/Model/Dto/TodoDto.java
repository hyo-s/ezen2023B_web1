package example.day08.project_2.Model.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class TodoDto {

    private int id;
    private String content;
    private boolean tcondition;
    private String tdate;


}
