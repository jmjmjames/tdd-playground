package tdd.chap08.testable;

import tdd.chap08.payinfo.PayInfo;
import tdd.chap08.payinfo.PayInfoDao;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MemoryPayInfoDao extends PayInfoDao {
    private final LinkedHashMap<String, PayInfo> infos = new LinkedHashMap<>();

    @Override
    public void insert(PayInfo payInfo) {
        infos.put(payInfo.getTrNumber(), payInfo);
    }

    public List<PayInfo> getAll() {
        return new ArrayList<>(infos.values());
    }
}
