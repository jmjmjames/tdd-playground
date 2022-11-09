package chap07.autodebit;

import chap07.autodebit.repository.AutoDebitInfoRepository;
import chap07.autodebit.validator.CardNumberValidator;
import java.time.LocalDateTime;

public class AutoDebitRegister {

    private CardNumberValidator validator;
    private AutoDebitInfoRepository repository;

    public AutoDebitRegister(CardNumberValidator cardNumberValidator,
                             AutoDebitInfoRepository autoDebitInfoRepository) {
        this.validator = cardNumberValidator;
        this.repository = autoDebitInfoRepository;
    }

    public RegisterResult register(AutoDebitReq req) {
        CardValidity validity = validator.validate(req.getCardNumber());
        if (validity != CardValidity.VALID) {
            return RegisterResult.error(validity);
        }
        AutoDebitInfo info = repository.findOne(req.getUserId());
        if (info != null) {
            info.changeCardNumber(req.getCardNumber());
        } else {
            AutoDebitInfo newInfo = new AutoDebitInfo(req.getUserId(), req.getCardNumber(), LocalDateTime.now());
            repository.save(newInfo);
        }
        return RegisterResult.success();
    }
}
