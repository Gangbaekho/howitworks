package com.nuitblanche.howitworks.domain.project;

import com.nuitblanche.howitworks.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Project(String title, List<Post> posts) {
        this.title = title;
        this.posts = posts;
    }
}
