package com.efa73.charleecollector.domain.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;
import com.efa73.charleecollector.domain.repository.CycleDataRepository;
import com.efa73.charleecollector.domain.repository.CycleInfoRepository;
import com.efa73.charleecollector.interfaces.dto.request.EventType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class CollectServiceTest {

    @MockitoBean
    private CycleInfoRepository cycleInfoRepository;
    @MockitoBean
    private CycleDataRepository cycleDataRepository;

    @Autowired
    private CollectService collectService;

    private CycleInfo cycleInfo;
    private CycleInfo eventInfo;
    private List<CycleData> cycleDataList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        cycleInfo = CycleInfo.createCycleEntity("default-mdn", "tid", "mid", "did", "pv", LocalDateTime.now(),
                EventType.CYCLE.getType());

        eventInfo = CycleInfo.createEventEntity("default-mdn", "tid", "mid", "did", "pv", LocalDateTime.now(), null,
                EventType.ON.getType());

        for (int i = 0; i < 60; i++) {
            var data = CycleData.createEntity(String.valueOf(i), "A", "37.1234", "127.5678", "45", "60", "100", "80",
                    cycleInfo);
            cycleDataList.add(data);
        }
    }

    @Test
    @DisplayName("collectCycle()에서 CycleInfo가 잘 저장되고 올바른 mdn을 반환함을 검증")
    void collectCycleSuccess() {
        // when
        when(cycleInfoRepository.save(cycleInfo)).thenReturn(cycleInfo);

        // then
        String result = collectService.collectCycle(cycleInfo, cycleDataList);
        Assertions.assertThat(result).isEqualTo(cycleInfo.getMdn());
        verify(cycleInfoRepository, times(1)).save(cycleInfo);
        verify(cycleDataRepository, times(1)).saveAll(cycleDataList);
    }

    @Test
    @DisplayName("collectCycle()에서 cycle_info 테이블에 중복 저장이 실행될 경우 예외처리 로직을 검증")
    void collectCycleDuplicated() {
        // when
        when(cycleInfoRepository.save(cycleInfo))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        // then
        String result = collectService.collectCycle(cycleInfo, cycleDataList);
        Assertions.assertThat(result).isEqualTo("중복저장됨");
        verify(cycleDataRepository, never()).saveAll(any());
    }

    @Test
    @DisplayName("collectEvent()에서 EventInfo가 잘 저장되고 default-mdn을 반환함을 검증")
    void collectEvent_success() {
        // when
        when(cycleInfoRepository.save(eventInfo)).thenReturn(eventInfo);

        // then
        String result = collectService.collectEvent(eventInfo);
        Assertions.assertThat(result).isEqualTo(eventInfo.getMdn());
        verify(cycleInfoRepository, times(1)).save(eventInfo);
    }

    @Test
    @DisplayName("collectEvent()에서 cycle_info 테이블에 중복 저정 되었을때의 예외처리 로직을 검증")
    void collectEvent_duplicate() {
        // when
        when(cycleInfoRepository.save(eventInfo))
                .thenThrow(new DataIntegrityViolationException("duplicate"));

        // then
        String result = collectService.collectEvent(eventInfo);
        Assertions.assertThat(result).isEqualTo("중복저장됨");
        verify(cycleInfoRepository, times(1)).save(eventInfo);
    }
}
