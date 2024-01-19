package com.project.real_estate_project03_team02.entity.concretes;



import com.project.real_estate_project03_team02.utilis.Messages;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 5, max = 150)
    @Column( length = 150)
    private String title;
    @NotNull
    @Column( length = 50)
    private String icon;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Column(columnDefinition = "boolean default false")
    private boolean built_in;
    @NotNull
    @Column(columnDefinition = "int default 0")
    private int seq;
    private String slug;
    @NotNull
    @Column(columnDefinition = "boolean default true")
    private boolean is_active;
    @NotNull
    private Date created_at;
    private Date updated_at;


    public void setTitle(String title) {
        this.title = title;
        // Automatically update the slug based on the URL-encoded title
        String encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8);
        // Ensure the length of the encoded title is within the specified range
        if (encodedTitle.length() >= 5 && encodedTitle.length() <= 200) {
            this.slug = encodedTitle;
        } else {
            // Handle invalid length (throw exception, log, etc.)
            throw new IllegalArgumentException(Messages.INVALID_SLUG_LENGTH);
        }
    }




}
