package com.zerobase.reservation.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberInput {
    @Id @GeneratedValue
    private Long id;

    @Size(min = 4, max = 20, message = "회원아이디는 4자이상 20자 이하만 가능합니다.")
    @NotBlank
    private String userId;

    @Size(min = 4, max = 16, message = "비밀번호는 4자이상 16자 이하만 가능합니다.")
    @NotBlank(message = "비밀번호는 필수 입력값 입니다.")
    private String password;

    @NotBlank(message = "고객 이름은 필수 입력값 입니다.")
    private String userName;

    @NotBlank(message = "전화번호는 필수 입력값 입니다.")
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;

    private String status;

    private LocalDateTime regDate;
}
