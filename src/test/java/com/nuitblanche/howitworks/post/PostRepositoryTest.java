package com.nuitblanche.howitworks.post;

import com.nuitblanche.howitworks.domain.post.Post;
import com.nuitblanche.howitworks.domain.post.PostRepository;
import org.junit.After;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @After
    public void cleanUp(){
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
}
