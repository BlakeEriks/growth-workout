package com.eriks.growth.dao;

import com.eriks.growth.domain.Set;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SetDao extends JpaRepository<Set, Long> {

    Set getByUuid(UUID setUuid);

    void deleteByUuid(UUID setUuid);
}
