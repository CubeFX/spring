package pl.com.dawidm.spring.infrastructure.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.dawidm.spring.infrastructure.persistence.entity.CarEntity;

public interface CarRepositoryDao extends JpaRepository<CarEntity, Long> {
}
