package tdd.chap07.autodebit.repository;

import tdd.chap07.autodebit.dto.AutoDebitInfo;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {

    @Override
    public void save(AutoDebitInfo autoDebitInfo) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}
