Feature:  As a user I should be able to add and checkout using shopping cart page



  Scenario: Verify shopping cart deatils
    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    When I increase the quantity to 3 and choose size "M" and color "White" and click on add to cart button
    And I click on Proceed to Checkout
    Then The quantity, size and color should be correct
    Then The other shopping cart details should be the following
      | Name   | SKU    | Unit Price | Shipping fee | Tax   |
      | Blouse | demo_2 | $27.00     | $2.00        | $0.00 |




  Scenario Outline: Verify shopping cart details using different sets of data
    Given I am on the homepage
    When I click on "<Name>"
    Then I should land on the product details page
    When I increase the quantity to <Quantity> and choose size "<Size>" and color "<Color>" and click on add to cart button
    And I click on Proceed to Checkout
    Then The quantity, size and color should be correct
    Then The other shopping cart details should be the following
      | Name   | SKU   | Unit Price   | Shipping fee   | Tax   |
      | <Name> | <SKU> | <Unit Price> | <Shipping fee> | <Tax> |

     Examples:
       | Name                        | Quantity | Size | Color  | SKU    | Unit Price | Shipping fee | Tax   |
       | Blouse                      | 3        | M    | White  | demo_2 | $27.00     | $2.00         | $0.00 |
       | Printed Dress               | 5        | S    | Orange | demo_3 | $26.00     | $2.00         | $0.00 |
       | Faded Short Sleeve T-shirts | 20       | L    | Blue   | demo_1 | $16.51     | $2.00         | $0.00 |
       | Printed Chiffon Dress       | 100      | M    | Green  | demo_7 | $16.40     | $2.00         | $0.00 |





