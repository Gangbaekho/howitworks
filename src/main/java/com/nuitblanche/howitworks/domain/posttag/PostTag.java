package com.nuitblanche.howitworks.domain.posttag;

import com.nuitblanche.howitworks.domain.post.Post;
import com.nuitblanche.howitworks.domain.tag.Tag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private Post post;

    @ManyToOne
    @JoinColumn(name="TAG_ID")
    private Tag tag;

    private String something;

    @Builder
    public PostTag(Post post, Tag tag, String something) {
        this.post = post;
        this.tag = tag;
        this.something = something;
    }
}
