package com.polarbookshop.catalogservice.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class BookValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldCorrectThenValidationSucceeds() {
        var book = Book.of("1234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = Book.of("a234567890", "Title", "Author", 9.90);
        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        // violations 집합의 크기가 1인지 확인하여 하나의 유효성 검사 오류가 있음을 확인
        assertThat(violations).hasSize(1);

        // 첫 번째 유효성 검사 오류의 메시지가 예상한 대로 "The ISBN format must be valid."인지 확인
        assertThat(violations.iterator().next().getMessage()).isEqualTo("The ISBN format must be valid.");
    }

}