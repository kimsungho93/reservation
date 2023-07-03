package com.zerobase.reservation.member.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    @Id
    @GeneratedValue
    private Long id;

    private String userId;
    private String userName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private LocalDateTime regDate;
}
