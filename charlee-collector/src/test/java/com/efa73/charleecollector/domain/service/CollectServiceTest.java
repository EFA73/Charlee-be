package com.efa73.charleecollector.domain.service;

import static org.mockito.Mockito.*;

import com.efa73.charleecollector.domain.entity.CollectTestFactory;
import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;
import com.efa73.charleecollector.domain.repository.CycleDataRepository;
import com.efa73.charleecollector.domain.repository.CycleInfoRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CollectServiceTest {

    @Mock private CycleInfoRepository cycleInfoRepository;
    @Mock private CycleDataRepository cycleDataRepository;

    @Autowired private CollectService collectService;
    @Autowired private CollectTestFactory collectTestFactory;

    private final String MDN = "default-mdn";

    @BeforeEach
    void setUp() {
        collectService = new CollectService(cycleInfoRepository, cycleDataRepository);
    }

    @Test
    // collectCycle()에서 CycleInfo가 잘 저장되고 mdn을 반환함을 검증
    void collectCycleSuccess() {
        // given
        CycleInfo cycleInfo = collectTestFactory.createCycleInfo(MDN);
        List<CycleData> cycleDataList = collectTestFactory.createCycleData(MDN, cycleInfo);

        // when
        when(cycleInfoRepository.save(cycleInfo)).thenReturn(cycleInfo);

        // then
        String result = collectService.collectCycle(cycleInfo, cycleDataList);
        Assertions.assertThat(result).isEqualTo(cycleInfo.getMdn());
        verify(cycleInfoRepository, times(1)).save(cycleInfo);
        verify(cycleDataRepository, times(1)).saveAll(cycleDataList);
    }

    @Test
    // collectCycle()에서 cycle_info 테이블에 중복 저장이 실행될 경우 예외처리 로직을 검증
    void collectCycleDuplicated() {
        // given
        CycleInfo cycleInfo = collectTestFactory.createCycleInfo(MDN);
        List<CycleData> cycleDataList = collectTestFactory.createCycleData(MDN, cycleInfo);

        // when
        when(cycleInfoRepository.save(cycleInfo))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        // then
        String result = collectService.collectCycle(cycleInfo, cycleDataList);
        Assertions.assertThat(result).isEqualTo("중복저장됨");
        verify(cycleDataRepository, never()).saveAll(any());
    }

    @Test
    // collectEvent()에서 EventInfo가 잘 저장되고 mdn을 반환함을 검증
    void collectEvent_success() {
        // given
        CycleInfo cycleInfo = collectTestFactory.createCycleInfo(MDN);

        // when
        when(cycleInfoRepository.save(cycleInfo)).thenReturn(cycleInfo);

        // then
        String result = collectService.collectEvent(cycleInfo);
        Assertions.assertThat(result).isEqualTo(cycleInfo.getMdn());
        verify(cycleInfoRepository, times(1)).save(cycleInfo);
    }

    @Test
    // collectEvent()에서 cycle_info 테이블에 중복 저정 되었을때의 예외처리 로직을 검증
    void collectEvent_duplicate() {
        // given
        CycleInfo cycleInfo = collectTestFactory.createEventInfo(MDN);

        // when
        when(cycleInfoRepository.save(cycleInfo))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        // then
        String result = collectService.collectEvent(cycleInfo);
        Assertions.assertThat(result).isEqualTo("중복저장됨");
        verify(cycleInfoRepository, times(1)).save(cycleInfo);
    }
}