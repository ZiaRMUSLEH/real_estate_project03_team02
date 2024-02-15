package com.project.real_estate_project03_team02.entity.concretes.business;



import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column( length = 150)
    private String title;
    @NotNull
    @Column( length = 50)
    private String icon;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(name = "build_in" ,columnDefinition = "boolean default false")
    private boolean builtIn;
    @NotNull
    @Column(columnDefinition = "int default 0")
    private int seq;
    private String slug;
    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean isActive;
    @NotNull
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;





}
