package chap07.autodebit.repository;

import chap07.autodebit.AutoDebitInfo;

public class StubAutoDebitInfoRepository implements AutoDebitInfoRepository {

    @Override
    public void save(AutoDebitInfo autoDebitInfo) {

    }

    @Override
    public AutoDebitInfo findOne(String userId) {
        return null;
    }
}