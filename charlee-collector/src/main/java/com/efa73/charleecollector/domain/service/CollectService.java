package com.efa73.charleecollector.domain.service;

import com.efa73.charleecollector.domain.entity.CycleDataEntity;
import com.efa73.charleecollector.domain.entity.CycleInfoEntity;
import com.efa73.charleecollector.domain.repository.CycleDataRepository;
import com.efa73.charleecollector.domain.repository.CycleInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectService {

    private final CycleInfoRepository cycleInfoRepository;
    private final CycleDataRepository cycleDataRepository;

    public String collectCycle(CycleInfoEntity cycleInfo, List<CycleDataEntity> cycleDataList) {
        var saved = cycleInfoRepository.save(cycleInfo);
        cycleDataRepository.saveAll(cycleDataList);

        return saved.getMdn();
    }

    public String collectEvent(CycleInfoEntity eventInfo) {
        var saved = cycleInfoRepository.save(eventInfo);

        return saved.getMdn();
    }
}
