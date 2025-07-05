# Fawry E-Commerce System

A comprehensive e-commerce system built in Java that supports various product types, shopping cart functionality, and checkout processes with shipping and expiration handling.

## Features

### Product Management
- **Base Products**: Standard products with name, price, and quantity
- **Shippable Products**: Products that require shipping with weight information
- **Expirable Products**: Products with expiration dates (e.g., food items)
- **Shippable & Expirable Products**: Products that both require shipping and expire

### Shopping Cart
- Add products with specific quantities
- Automatic validation of available stock
- Calculate subtotals
- Collect shippable items for shipping processing

### Checkout System
- **Order Processing**: Complete checkout with inventory updates
- **Payment Processing**: Customer balance validation and deduction
- **Shipping Calculation**: Weight-based shipping fees
- **Receipt Generation**: Detailed checkout receipts with all costs

### Error Handling
- Empty cart validation
- Insufficient balance checks
- Out-of-stock product validation
- Expired product detection
- Invalid quantity validation

## System Architecture

### Core Classes

#### Products
- `Product`: Base product class
- `ShippableProduct`: Products requiring shipping
- `ExpirableProduct`: Products with expiration dates
- `ShippableExpirableProduct`: Products requiring both shipping and expiration handling

#### Shopping
- `Cart`: Shopping cart with item management
- `CartItem`: Individual items in cart
- `Customer`: Customer with balance management

#### Services
- `CheckoutService`: Main checkout processing
- `ShippingService`: Shipping calculation and notification

#### Exceptions
- `IllegalQuantityException`: Invalid quantity operations
- `InSufficientBalanceException`: Insufficient customer balance
- `InSufficientQuantityException`: Insufficient product stock

## Usage Examples

### Basic Usage

```java
// Create products
Product cheese = new ShippableExpirableProduct("Cheese", 100.0, 10, LocalDate.now().plusDays(30), 400.0);
Product tv = new ShippableProduct("TV", 5000.0, 3, 15000.0);
Product scratchCard = new Product("Mobile Scratch Card", 50.0, 20);

// Create customer
Customer customer = new Customer(6000.0);

// Create cart and add items
Cart cart = new Cart();
cart.add(cheese, 2);
cart.add(tv, 1);
cart.add(scratchCard, 3);

// Process checkout
CheckoutService checkoutService = new CheckoutServiceImpl();
checkoutService.checkout(customer, cart);
```

### Expected Output

```
** Shipment notice **
2x Cheese 400g
1x TV 15000g
Total package weight 15.8kg

** Checkout receipt **
2x Cheese	100
1x TV	5000
3x Mobile Scratch Card	50
----------------------
Subtotal	5150
Shipping	474
Amount	5624
Remaining Balance	376
```