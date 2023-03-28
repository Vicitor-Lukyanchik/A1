package com.victor.a1.task3.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "logins")
public class Login {

    //I have added one login db-migrations/v-1.0/2023-03-28-inserts.sql, because in file postings.csv there isn't
    //posting with username which would be in logins csv and I decided to add to check program
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application")
    private String application;

    @Column(name = "app_account_name")
    private String appAccountName;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "job_title")
    private String jobTitle;

    @Column(name = "department")
    private String department;
}
