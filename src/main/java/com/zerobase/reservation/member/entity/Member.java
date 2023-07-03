package com.zerobase.reservation.member.entity;

import com.zerobase.reservation.member.model.MemberRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;

    private String userId;
    private String password;
    private String userName;
    private String phone;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
    private String status;
    private LocalDateTime regDate;
}
