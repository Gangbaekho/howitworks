package com.nuitblanche.howitworks.domain.tag;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    @Builder
    public Tag(String title, TagType tagType) {
        this.title = title;
        this.tagType = tagType;
    }
}
