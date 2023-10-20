package com.example.demo.service;

import com.example.demo.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    private PostService underTest;

    @BeforeEach
    void setUp() {
        underTest = new PostService(postRepository);
    }

    @Test
    void canFindAll() {
        // when
        underTest.findAll();
        // then
        verify(postRepository).findAll();
    }
}