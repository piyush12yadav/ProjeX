package com.piyush.projectManagmentSystem.service;

import com.piyush.projectManagmentSystem.entity.PlanType;
import com.piyush.projectManagmentSystem.entity.Subscription;
import com.piyush.projectManagmentSystem.entity.User;

public interface SubscriptionService {

    Subscription createSubscription(User user);

    Subscription getUserSubscription(Long userId) throws Exception;

    Subscription upgradeSubscription(Long userId, PlanType planType);

    boolean isValid(Subscription subscription);
}
