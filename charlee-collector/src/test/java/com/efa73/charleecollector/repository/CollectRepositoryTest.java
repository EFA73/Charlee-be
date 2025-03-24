package com.efa73.charleecollector.repository;

import com.efa73.charleecollector.domain.entity.CollectTestFactory;
import com.efa73.charleecollector.domain.repository.CycleDataRepository;
import com.efa73.charleecollector.domain.repository.CycleInfoRepository;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@DataJpaTest
@Import(CollectTestFactory.class)
public class CollectRepositoryTest {

    @Autowired private CycleDataRepository cycleDataRepository;
    @Autowired private CycleInfoRepository cycleInfoRepository;
    @Autowired private CollectTestFactory collectTestFactory;

    private final String MDN = "default-mdn";

    @Test
    @DisplayName("CycleInfo가 DB에 잘 저장되는지 확인")
    void saveCycleInfo() {

        var cycleInfo = collectTestFactory.createCycleInfo(MDN);

        var saved = cycleInfoRepository.save(cycleInfo);

        Assertions.assertThat(saved.getMdn()).isEqualTo(cycleInfo.getMdn());
        Assertions.assertThat(saved.getOTime()).isEqualTo(cycleInfo.getOTime());
        Assertions.assertThat(saved.getEventType()).isEqualTo(cycleInfo.getEventType());
    }

    @Test
    @DisplayName("CycleData의 List가 DB에 순서대로 저장되는지 확인")
    void saveCycleData() {

        var cycleDataList = collectTestFactory.createCycleData(MDN, collectTestFactory.createCycleInfo(MDN));

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
