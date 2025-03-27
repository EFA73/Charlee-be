package com.efa73.charleecollector.domain.repository;

import com.efa73.charleecollector.domain.entity.CycleData;
import com.efa73.charleecollector.domain.entity.CycleInfo;
import com.efa73.charleecollector.interfaces.dto.request.EventType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CollectRepositoryTest {

    @Autowired
    private CycleDataRepository cycleDataRepository;
    @Autowired
    private CycleInfoRepository cycleInfoRepository;

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
    @DisplayName("CycleInfo가 DB에 잘 저장되는지 확인")
    void saveCycleInfo() {

        var saved = cycleInfoRepository.save(cycleInfo);

        Assertions.assertThat(saved.getMdn()).isEqualTo(cycleInfo.getMdn());
        Assertions.assertThat(saved.getOTime()).isEqualTo(cycleInfo.getOTime());
        Assertions.assertThat(saved.getEventType()).isEqualTo(cycleInfo.getEventType());
    }

    @Test
    @DisplayName("시동 ON EventInfo가 DB에 잘 저장되는지 확인")
    void saveEventInfo() {

        var saved = cycleInfoRepository.save(eventInfo);

        Assertions.assertThat(saved.getMdn()).isEqualTo(eventInfo.getMdn());
        Assertions.assertThat(saved.getOnTime()).isEqualTo(eventInfo.getOnTime());
        Assertions.assertThat(saved.getEventType()).isEqualTo(eventInfo.getEventType());
    }

    @Test
    @DisplayName("CycleData의 List가 DB에 순서대로 저장되는지 확인")
    void saveCycleData() {
        var savedList = cycleDataRepository.saveAll(cycleDataList);

        Assertions.assertThat(savedList).isNotEmpty();
        Assertions.assertThat(savedList.size()).isEqualTo(cycleDataList.size());

        IntStream.range(0, savedList.size()).forEach(i -> {
            var expected = cycleDataList.get(i);
            var actual = savedList.get(i);
            Assertions.assertThat(actual.getId()).isEqualTo(expected.getId());
            Assertions.assertThat(actual.getSec()).isEqualTo(expected.getSec());
        });
    }
}
