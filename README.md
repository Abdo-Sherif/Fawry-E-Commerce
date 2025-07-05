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
Customer customer = new Customer(1000.0);

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
Remaining Balance	-4624
```

## Running the System

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build and Run

```bash
# Build the project
mvn clean compile

# Run the main application
mvn exec:java -Dexec.mainClass="org.ecommerce.Main"

# Run tests
mvn test
```

## Testing

The system includes comprehensive unit tests covering:

- Product creation and validation
- Cart operations
- Checkout processes
- Error scenarios
- Edge cases

Run tests with:
```bash
mvn test
```

## Design Patterns Used

1. **Strategy Pattern**: Different product types implement different interfaces
2. **Service Layer Pattern**: Business logic separated into service classes
3. **Optional Pattern**: Safe handling of optional product capabilities
4. **Exception Handling**: Comprehensive error management

## Assumptions

1. **Shipping Rate**: Fixed rate of 30 currency units per kg
2. **Weight Units**: Product weights stored in grams, displayed in grams and kg
3. **Currency**: All prices in the same currency unit
4. **Date Handling**: Uses system current date for expiration checks
5. **Inventory**: Inventory is reduced immediately upon successful checkout

## Future Enhancements

1. **Database Integration**: Persistent storage for products and customers
2. **User Authentication**: Customer login and session management
3. **Payment Gateway**: Integration with external payment systems
4. **Discount System**: Coupon codes and promotional pricing
5. **Order History**: Track customer order history
6. **Inventory Management**: Advanced stock management features
7. **Shipping Providers**: Multiple shipping service integrations
8. **Product Categories**: Organized product catalog
9. **Reviews and Ratings**: Customer feedback system
10. **Multi-language Support**: Internationalization

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License. 