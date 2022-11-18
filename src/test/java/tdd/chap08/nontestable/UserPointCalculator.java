package tdd.chap08.nontestable;

import static tdd.chap08.subs.Grade.GOLD;

import tdd.chap08.subs.NoSubscriptionException;
import tdd.chap08.subs.Product;
import tdd.chap08.subs.ProductDao;
import tdd.chap08.subs.Subscription;
import tdd.chap08.subs.SubscriptionDao;
import tdd.chap08.subs.User;
import java.time.LocalDate;

public class UserPointCalculator {
    private final SubscriptionDao subscriptionDao;
    private final ProductDao productDao;

    public UserPointCalculator(SubscriptionDao subscriptionDao,
                               ProductDao productDao) {
        this.subscriptionDao = subscriptionDao;
        this.productDao = productDao;
    }

    public int calculatePoint(User user) {
        Subscription subscription = subscriptionDao.findById(user.getId());
        if (subscription == null) {
            throw new NoSubscriptionException();
        }
        Product product = productDao.findById(subscription.getProductId());
        LocalDate now = LocalDate.now();
        int point = 0;
        if (subscription.isFinished(now)) {
            point += product.getDefaultPoint();
        } else {
            point += product.getDefaultPoint() + 10;
        }
        if (subscription.getGrade() == GOLD) {
            point += 100;
        }
        return point;
    }
}
