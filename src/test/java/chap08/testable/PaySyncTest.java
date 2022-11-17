package chap08.testable;

import static org.assertj.core.api.Assertions.assertThat;

import chap08.payinfo.PayInfo;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PaySyncTest {
    private MemoryPayInfoDao memoryDao = new MemoryPayInfoDao();

    @Test
    void allDataSaved() throws IOException {
        PaySync paySync = new PaySync();
        paySync.setPayInfoDao(memoryDao);
        paySync.setFilePath("src/test/resources/c0111.csv");

        paySync.sync();

        List<PayInfo> savedInfos = memoryDao.getAll();
        assertThat(savedInfos.size()).isEqualTo(2);
    }
}
