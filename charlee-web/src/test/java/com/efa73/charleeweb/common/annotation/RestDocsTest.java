package com.efa73.charleeweb.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 통합 테스트를 위한 설정과 RestDocs 설정을 자동으로 적용하는 어노테이션입니다.
 *
 * @see AutoConfigureRestDocs
 * @see AutoConfigureMockMvc
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public @interface RestDocsTest {
}
