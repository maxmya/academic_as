package com.academic.as.demo;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


@SpringBootApplication
@EnableScheduling
public class AcademicAutomatedSystemApplication {

    // init BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // init Firebase
    @Bean("initFirebase")
    public FirebaseApp initFirebase() throws IOException {

        String fileName = "FirebaseKeys.json";
        File file = ResourceUtils.getFile("classpath:static/" + fileName);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(file)))
                .setDatabaseUrl("https://academic-as.firebaseio.com/")
                .build();

        return FirebaseApp.initializeApp(options);
    }

    public static void main(String[] args) {

        SpringApplication.run(AcademicAutomatedSystemApplication.class, args);
    }


}
