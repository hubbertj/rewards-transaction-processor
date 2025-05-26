package com.example.rewards.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepositoryContextTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    TransactionItemRepository transactionItemRepository;
    @Autowired
    AwardNumberRepository awardNumberRepository;
    @Autowired
    RewardPointsRepository rewardPointsRepository;
    @Autowired
    LoginRepository loginRepository;

    @Test
    void contextLoads() {
        // Just ensure the repositories are loaded
        assert userRepository != null;
        assert roleRepository != null;
        assert transactionRepository != null;
        assert transactionItemRepository != null;
        assert awardNumberRepository != null;
        assert rewardPointsRepository != null;
        assert loginRepository != null;
    }
}
