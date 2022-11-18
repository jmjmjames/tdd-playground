package chap08.subs;

public interface SubscriptionDao {
    Subscription findById(String userId);

    void insert(Subscription subscription);
}
