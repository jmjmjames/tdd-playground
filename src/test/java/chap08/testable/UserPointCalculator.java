package chap08.testable;

import chap08.subs.NoSubscriptionException;
import chap08.subs.Product;
import chap08.subs.ProductDao;
import chap08.subs.Subscription;
import chap08.subs.SubscriptionDao;
import chap08.subs.User;
import java.time.LocalDate;

public class UserPointCalculator {
    private PointRule pointRule = new PointRule();
    private final SubscriptionDao subscriptionDao;
    private final ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public void setPointRule(PointRule pointRule) {
        this.pointRule = pointRule;
    }

    public int calculatePoint(User user) {
        Subscription subscription = subscriptionDao.findById(user.getId());
        if (subscription == null) {
            throw new NoSubscriptionException();
        }
        Product product = productDao.findById(subscription.getProductId());
        LocalDate now = LocalDate.now();
        return pointRule.calculate(subscription, product, now);
    }
}
