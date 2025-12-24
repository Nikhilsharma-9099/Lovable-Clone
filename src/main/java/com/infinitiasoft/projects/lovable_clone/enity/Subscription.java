package com.infinitiasoft.projects.lovable_clone.enity;


import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscription {
    Long id;

    @ManyToOne
    User user;
    Plan plan;
    String stripeCustomerId;
    String stripeSubscriptionId;
    Instant currentPeriodStart;
    Instant currentPeriodEnd;
    Boolean cancelAtPeriodEnd = false;
    Instant createdAt;
    Instant updatedAt;
    Instant deletedAt;
}
