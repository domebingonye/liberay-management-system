package com.maids.cc.library_management_system.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware", dateTimeProviderRef = "auditingDateTimeProvider")
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mm = new ModelMapper();
        mm.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
                .setPropertyCondition(context ->
                        !(context.getSource() instanceof PersistentCollection));
        return mm;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "auditingDateTimeProvider")
    public DateTimeProvider dateTimeProvider() {
        return () -> Optional.of(OffsetDateTime.now());
    }


    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditorAware();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        return objectMapper;
    }
}
