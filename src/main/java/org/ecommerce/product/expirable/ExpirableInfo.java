package org.ecommerce.product.expirable;

import java.time.LocalDate;

public class ExpirableInfo implements Expirable {

    private final LocalDate expirationDate;

    public ExpirableInfo(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean isExpired() {
        return LocalDate.now().isAfter(expirationDate);
    }
}
