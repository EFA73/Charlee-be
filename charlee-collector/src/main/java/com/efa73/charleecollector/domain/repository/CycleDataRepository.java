package com.efa73.charleecollector.domain.repository;

import com.efa73.charleecollector.domain.entity.CycleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleDataRepository extends JpaRepository<CycleData, Long> {

}
