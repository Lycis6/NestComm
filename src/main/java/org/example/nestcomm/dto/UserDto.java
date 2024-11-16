package org.example.nestcomm.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {
    @Size(min = 2, max = 20, message = "размер должен находиться в пределах от 2 до 20 символов!")
    @Pattern(regexp = "\\b[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\\b", message = "email должен быть корректным!")
    @NotNull(message = "email не может быть пустым")
    private String email;
    // хотя бы одна заглавная и строчная буквы, хотя бы одна цифра
    @Size(min = 4, max = 20, message = "размер должен находиться в пределах от 4 до 20 символов!" )
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{4,20}$", message = " пароль должен соответствовать схеме:" +
            " хотя бы одна заглавная и строчная" +
            " буквы, хотя бы одна цифра")
    @NotNull(message = "пароль не может быть пустым!")
    private String password;
    @Size(min = 2, max = 20)
    private String name;
    @Size(min = 2, max = 20)
    private String surname;
    @Pattern(regexp = "\\b\\+(7|8)?[\\s.-]?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{2,4}\\b", message = "номер телефона" +
            "должен быть корректным!")
    private String phoneNumber;
}
