package chap07.autodebit.repository;

import chap07.autodebit.dto.AutoDebitInfo;

public interface AutoDebitInfoRepository {

    void save(AutoDebitInfo autoDebitInfo);

    AutoDebitInfo findOne(String userId);
}
