package com.andy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor())
                .excludePathPatterns("/image/**","/layui/**","/jquery/**");
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(
                new MappingJackson2HttpMessageConverter(
                        new ObjectMapper().setDateFormat(
                                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        )
                )
        );
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/editStudentForm").setViewName("student/editStudentForm");
        registry.addViewController("/listStudentInfoForm").setViewName("student/listStudentInfoForm");
        registry.addViewController("/attachFiles").setViewName("attachFiles");
        registry.addViewController("/listCourseInfoForm").setViewName("course/listCourseInfoForm");
        registry.addViewController("/addCourseForm").setViewName("course/addCourseForm");
        registry.addViewController("/assignCourseForm").setViewName("course/assignCourseForm");
        registry.addViewController("/classCourseForm").setViewName("tblClass/classCourseForm");

        registry.addViewController("/listHomeworkForm").setViewName("homework/listHomeworkForm");
        registry.addViewController("/listCourseHomeworkForm").setViewName("homework/listCourseHomeworkForm");
        registry.addViewController("/addHomeworkForm").setViewName("homework/addHomeworkForm");
        registry.addViewController("/addCourseHomeworkForm").setViewName("homework/addCourseHomeworkForm");
        registry.addViewController("/uploadCourseHomeworkForm").setViewName("homework/uploadCourseHomeworkForm");
        registry.addViewController("/listStudentHomeworkForm").setViewName("homework/listStudentHomeworkForm");
        registry.addViewController("/listHomeworkScoreForm").setViewName("homework/listHomeworkScoreForm");


        registry.addViewController("/listClassForm").setViewName("tblClass/listClassForm");
        registry.addViewController("/addClassForm").setViewName("tblClass/addClassForm");
        registry.addViewController("/editClassForm").setViewName("tblClass/editClassForm");

        registry.addViewController("/uploadStudentInfoForm").setViewName("uploadStudentInfoForm");

        registry.addViewController("/registerForm").setViewName("register");
        registry.addViewController("/DashBoard").setViewName("DashBoard");
        registry.addViewController("/").setViewName("mainLogin");
        registry.addViewController("/loginForm").setViewName("mainLogin");
        registry.addViewController("/mainLogin").setViewName("mainLogin");

        registry.addViewController("/listTermInfoForm").setViewName("/term/listTermInfoForm");
        registry.addViewController("/editTermForm").setViewName("/term/editTermForm");

    }
}
