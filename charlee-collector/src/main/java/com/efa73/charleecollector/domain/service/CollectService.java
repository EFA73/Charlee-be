package com.efa73.charleecollector.domain.service;

import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;
import com.efa73.charleecollector.domain.repository.CycleDataRepository;
import com.efa73.charleecollector.domain.repository.CycleInfoRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectService {

    private final CycleInfoRepository cycleInfoRepository;
    private final CycleDataRepository cycleDataRepository;

    @Transactional
    public String collectCycle(CycleInfo cycleInfo, List<CycleData> cycleDataList) {
        try {
            var saved = cycleInfoRepository.save(cycleInfo);
            cycleDataRepository.saveAll(cycleDataList);

            return saved.getMdn();
        } catch (DataIntegrityViolationException e) {
            return "중복저장됨";
        }
    }

    public String collectEvent(CycleInfo eventInfo) {
        try {
            var saved = cycleInfoRepository.save(eventInfo);

            return saved.getMdn();
        } catch (DataIntegrityViolationException e) {
            return "중복저장됨";
        }
    }
}
