package pl.com.dawidm.spring.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("pl.com.dawidm.spring")
@ComponentScan("pl.com.dawidm.spring")
@EntityScan("pl.com.dawidm.spring.infrastructure.*")
public class DbConfiguration {
}
