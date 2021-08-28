package com.nuitblanche.howitworks.post;

import com.nuitblanche.howitworks.domain.post.Post;
import com.nuitblanche.howitworks.domain.post.PostRepository;
import com.nuitblanche.howitworks.domain.posttag.PostTag;
import com.nuitblanche.howitworks.domain.posttag.PostTagRepository;
import com.nuitblanche.howitworks.domain.project.Project;
import com.nuitblanche.howitworks.domain.project.ProjectRepository;
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

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostRepositoryEagerFetchTest {

    @PersistenceUnit
    private EntityManagerFactory factory;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProjectRepository projectRepository;

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

        Project project = Project.builder()
                .title("project")
                .build();

        projectRepository.save(project);

        Post post = Post.builder()
                .title("title")
                .content("content")
                .project(project)
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
        projectRepository.deleteAll();
    }

    @Test
    public void postFetchTest(){

        Post post = postRepository.findAll().get(0);
        Post selected = postRepository.custonFindById(post.getId());

        assertThat(selected.getProject()).isNotNull();
        assertThat(selected.getPostTags()).isNotNull();
    }
}
