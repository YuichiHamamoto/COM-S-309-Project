package com.spring.backend.app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInfoRepository
        extends JpaRepository<LoginInfo, String> {
}
