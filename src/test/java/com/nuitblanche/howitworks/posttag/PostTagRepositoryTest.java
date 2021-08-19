package com.nuitblanche.howitworks.posttag;

import com.nuitblanche.howitworks.domain.post.Post;
import com.nuitblanche.howitworks.domain.post.PostRepository;
import com.nuitblanche.howitworks.domain.posttag.PostTag;
import com.nuitblanche.howitworks.domain.posttag.PostTagRepository;
import com.nuitblanche.howitworks.domain.tag.Tag;
import com.nuitblanche.howitworks.domain.tag.TagRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostTagRepositoryTest {

    @Autowired
    private PostTagRepository postTagRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TagRepository tagRepository;

    @After
    public void cleanUp(){
        postTagRepository.deleteAll();
    }

    @Transactional
    @Test
    public void createPostTag(){

        // given
        String postTitle = "postTitle";
        String postContent = "postContent";

        String tagTitle = "tagTitle";

        Post post = Post.builder()
                .title(postTitle)
                .content(postContent)
                .build();

        Tag tag = Tag.builder()
                .title(tagTitle)
                .build();

        postRepository.save(post);
        tagRepository.save(tag);

        PostTag postTag = PostTag.builder()
                .something("something")
                .build();

        postTag.setPost(post);
        postTag.setTag(tag);

        postTagRepository.save(postTag);

        // test
        PostTag selectedPostTag = postTagRepository.findAll().get(0);

        // for lazy fetching, use @Transactional annotation to maintain persistence context.
        // below code shows that lazy fetching uses persistence context.
        assertThat(selectedPostTag.getPost().getTitle()).isEqualTo(postTitle);
        assertThat(selectedPostTag.getPost().getContent()).isEqualTo(postContent);
        assertThat(selectedPostTag.getTag().getTitle()).isEqualTo(tagTitle);

    }
}
