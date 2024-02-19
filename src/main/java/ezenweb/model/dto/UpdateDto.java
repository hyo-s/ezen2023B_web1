package ezenweb.model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UpdateDto {
    private int no;
    private String pw;
    private String name;
    private String email;
    private String phone;
    private String img;
}
