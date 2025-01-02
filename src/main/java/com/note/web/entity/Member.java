package com.note.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Member {

    @GeneratedValue
    @Id
    @Column(name = "member_id")
    private Long id;
    private String name;
    private String email;
    private String password;


}
