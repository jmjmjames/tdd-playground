package tdd.chap08.auth;

public interface CustomerRepository {
    Customer findById(String id);
}
