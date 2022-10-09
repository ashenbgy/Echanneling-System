package com.echanneling.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@EqualsAndHashCode(callSuper = false)

@Entity
@Table(name ="user_role")
public class UserRole {
    @Id
    @Column(name="roll_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int rollId;

    @Column(name = "description")
    private String description;

    @Column(name ="code")
    private String code;

}
