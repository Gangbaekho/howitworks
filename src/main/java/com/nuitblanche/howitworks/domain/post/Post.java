package com.nuitblanche.howitworks.domain.post;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String content;

    @Builder
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Post 와 Tag를 만들었는데,
    // 저장을 할 때, 그럼 Post에 달린 Tag의 수 만큼, PostTag라는 row가 쌓이는 것이고,
    // 어떤 태그를 눌렀을 떄, 관련된 Post를 다 보여주면 된다는건가.
    // 일단은 그러면은 고정된 Tag를 만들도록 하자.
    // 그러면은 ENUM을 쓰는게 좋을 것 같다.

}
