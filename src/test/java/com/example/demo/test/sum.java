package com.example.demo.test;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class sum {

    @Test
    public void test() {
        assertThat(3+3).isEqualTo(6);
    }

    @Test
    public void test2() {
        assertThat(3+5).isEqualTo(8);
    }
}
