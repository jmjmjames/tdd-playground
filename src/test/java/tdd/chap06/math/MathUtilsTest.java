package tdd.chap06.math;

import static org.assertj.core.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class MathUtilsTest {

    @Test
    void dateFileSumTest() {
        File dataFile = new File("src/test/resources/datafile.txt");
        long sum = MathUtils.sum(dataFile);
        assertThat(sum).isEqualTo(10L);
    }

    @Test
    void dataFileSumTest2() {
        givenDataFile("src/test/resources/datafile.txt", "1", "2", "3", "4");
        File dataFile = new File("src/test/resources/datafile.txt");
        long sum = MathUtils.sum(dataFile);
        assertThat(sum).isEqualTo(10L);
    }

    private void givenDataFile(String path, String... lines) {
        try {
            Path dataPath = Paths.get(path);
            if (Files.exists(dataPath)) {
                Files.delete(dataPath);
            }
            Files.write(dataPath, Arrays.asList(lines));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void noDataFile_Then_Exception() {
        givenNoFile("src/test/resources/badpath.txt");

        File dataFile = new File("badpath.txt");
        assertThatIllegalArgumentException().isThrownBy(() ->
                MathUtils.sum(dataFile));
    }

    private void givenNoFile(String path) {
        File file = new File(path);
        if (file.exists()) {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new RuntimeException("fail givenNoFile: " + path);
            }
        }
    }

}
