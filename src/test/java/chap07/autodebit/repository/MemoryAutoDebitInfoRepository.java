package chap07.autodebit.repository;

import chap07.autodebit.AutoDebitInfo;
import java.util.HashMap;
import java.util.Map;

/**
 * DB 대신 맵을 이용해서 자동이체 정보를 저장한다.
 * 메모리에만 데이터가 저장되므로 DB와 같은 영속성을 제공하지는 않지만, 테스트에서
 * 사용할 수 있을 만큼의 기능은 제공한다.
 */
public class MemoryAutoDebitInfoRepository implements AutoDebitInfoRepository {

    private Map<String, AutoDebitInfo> infos = new HashMap<>();

    @Override
    public void save(AutoDebitInfo autoDebitInfo) {
        infos.put(autoDebitInfo.getUserId(), autoDebitInfo);
    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return infos.get(userId);
    }
}
