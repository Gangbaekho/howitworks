package com.nuitblanche.howitworks.post;

import com.nuitblanche.howitworks.domain.post.Post;
import com.nuitblanche.howitworks.domain.post.PostRepository;
import com.nuitblanche.howitworks.domain.posttag.PostTag;
import com.nuitblanche.howitworks.domain.posttag.PostTagRepository;
import com.nuitblanche.howitworks.domain.tag.Tag;
import com.nuitblanche.howitworks.domain.tag.TagRepository;
import com.nuitblanche.howitworks.domain.tag.TagType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class postSelectTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @Before
    public void setUp(){

        // given
        String title = "title";
        String content = "content";

        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        postRepository.save(post);

        String itTitle = "itTitle";
        String societyTitle = "societyTitle";
        String scienceTitle = "sienceTitle";

        Tag itTag = Tag.builder()
                .title(itTitle)
                .tagType(TagType.IT)
                .build();

        Tag societyTag = Tag.builder()
                .title(societyTitle)
                .tagType((TagType.SOCIETY))
                .build();

        Tag scienceTag = Tag.builder()
                .title(scienceTitle)
                .tagType(TagType.SCIENCE)
                .build();

        tagRepository.save(itTag);
        tagRepository.save(societyTag);
        tagRepository.save(scienceTag);

        postTagRepository.save(PostTag.builder()
                .something("something")
                .post(post)
                .tag(itTag)
                .build());

        postTagRepository.save(PostTag.builder()
                .something("something")
                .post(post)
                .tag(societyTag)
                .build());

        postTagRepository.save(PostTag.builder()
                .something("something")
                .post(post)
                .tag(scienceTag)
                .build());
    }

    @After
    public void cleanUp(){
        postTagRepository.deleteAll();
        tagRepository.deleteAll();
        postRepository.deleteAll();
    }


    @Test
    @Transactional
    public void getPostWithMultipleTags(){

        Post post = postRepository.findAll().get(0);
        assertThat(post.getPostTags().size()).isEqualTo(3);
    }

}
