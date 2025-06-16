package com.example.taskflow.domain.auth.dto.request;

import com.example.taskflow.domain.user.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Getter
public class SignupRequest {

    @NotBlank(message = "아이디는 비어 있을 수 없습니다.")
    @Size(min = 2, max = 20, message = "아이디는 2자 이상 20자 이하로 입력해야 합니다.")
    private String username;

    @NotBlank(message = "email은 비어 있을 수 없습니다.")
    @Email(message = "email 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다.")
    @Pattern(
            regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).{8,20}$",
            message = "비밀번호는 8~20자이며, 영문자, 숫자, 특수문자를 모두 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    private String name;
}
