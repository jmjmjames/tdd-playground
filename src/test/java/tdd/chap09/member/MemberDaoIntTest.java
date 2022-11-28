package tdd.chap09.member;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberDaoIntTest {
    private final MemberDao memberDao;

    public MemberDaoIntTest(@Autowired MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @Test
    void findAll() {
        List<Member> members = memberDao.selectAll();
        assertThat(members).isNotNull();
    }
}
