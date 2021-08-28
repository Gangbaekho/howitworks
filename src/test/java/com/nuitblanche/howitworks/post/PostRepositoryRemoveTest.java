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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRepositoryRemoveTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    @Before
    public void setup(){
        Tag tagOne = Tag.builder()
                .tagType(TagType.IT)
                .title("it-tag")
                .build();

        Tag tagTwo = Tag.builder()
                .tagType(TagType.GAME)
                .title("game-tag")
                .build();

        tagRepository.save(tagOne);
        tagRepository.save(tagTwo);

        Post post = Post.builder()
                .title("title")
                .content("content")
                .build();

        postRepository.save(post);

        PostTag postTagOne = PostTag.builder()
                .tag(tagOne)
                .post(post)
                .something("something")
                .build();

        PostTag postTagTwo = PostTag.builder()
                .tag(tagTwo)
                .post(post)
                .something("something")
                .build();

        postTagRepository.save(postTagOne);
        postTagRepository.save(postTagTwo);
    }

    @After
    public void cleanup(){
        postTagRepository.deleteAll();
        tagRepository.deleteAll();
        postRepository.deleteAll();
    }

    @Test
    public void deletePostWithOrphanRemoval(){

        Post post = postRepository.findAll().get(0);
        postRepository.delete(post);


        List<PostTag> postTags = postTagRepository.findAll();
        assertThat(postTags.size()).isEqualTo(0);
    }
}
