package com.efa73.charleecollector.domain.repository;

import com.efa73.charleecollector.domain.entity.CycleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleInfoRepository extends JpaRepository<CycleInfo, Long> {

}
