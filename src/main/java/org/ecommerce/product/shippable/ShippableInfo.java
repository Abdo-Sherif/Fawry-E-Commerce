package org.ecommerce.product.shippable;

public record ShippableInfo(double getWeight, String getName) implements Shippable {
}
