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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @After
    public void cleanUp(){
        postTagRepository.deleteAll();
        tagRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    public void createPost(){

        // given
        String title = "title";
        String content = "content";

        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        // save
        postRepository.save(post);

        Post selectedPost = postRepository.findAll().get(0);

        // test
        assertThat(selectedPost.getTitle()).isEqualTo(title);
        assertThat(selectedPost.getContent()).isEqualTo(content);
    }

    @Transactional
    @Test
    public void createPostWithMultipleTags(){

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

        Post selectedPost = postRepository.findAll().get(0);
        assertThat(selectedPost.getPostTags().size()).isEqualTo(3);
    }
}
