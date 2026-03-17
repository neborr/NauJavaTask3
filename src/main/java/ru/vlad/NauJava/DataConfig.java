package ru.vlad.NauJava;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.vlad.NauJava.entity.Session;

import java.util.List;

import java.util.ArrayList;

@Configuration
public class DataConfig {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public List<Session> sessionContainer() {
        return new ArrayList<>();
    }
}
