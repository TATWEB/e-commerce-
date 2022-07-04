
Feature:  Product details


 @smoke @ui
  Scenario: Verify product details

    Given I am on the homepage
    When I click on "Printed Dress"
    Then I should land on the product details page
    And The default quantity should be 1

  @ui
  Scenario: Verify product details default quantity

    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    When I click on plus button 200 times
    Then The default quantity should be 201
    When I click on minus button 200 times
    Then The default quantity should be 1


  @smoke @ui
  Scenario: Verify product details price

    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    And The price should be 27.00


  @ui
  Scenario: Verify product details price

    Given I am on the homepage
    When I click on "Faded Short Sleeve T-shirts"
    Then I should land on the product details page
    And The price should be 16.51

#
#    @temp
#    Scenario: Verify product details page title
#      Given I am on the homepage
#      When I click on "Blouse"
#      Then I should land on the product details page
#
#  @temp
#  Scenario: Verify product details page title
#    Given I am on the homepage
#    When I click on "Printed Dress"
#    Then I should land on the product details page
#
#  @temp
#  Scenario: Verify product details page title
#    Given I am on the homepage
#    When I click on "Faded Short Sleeve T-shirts"
#    Then I should land on the product details page


  Scenario Outline: Verify product details page title for all products
      Given I am on the homepage
      When I click on "<Product>"
      Then I should land on the product details page
      And The product price should be <Price>
    Examples:
      | Product                     | Price |
      | Blouse                      | 27.00 |
      | Printed Dress               | 26.00 |
      | Printed Summer Dress        | 28.98 |
      | Printed Chiffon Dress       | 16.40 |
      | Faded Short Sleeve T-shirts | 16.51 |


#  Scenario Outline: Example
#    Given I am on the homepage
#    When I click on a <link>
##     when you do not parametrize the <>  the values from examples table will be directly replaced and you will
#       need to generate separate step definition for each dataset
#
#    Examples:
#       |link|
#       |contact us|
#       |customer services|
#       | login         |



  Scenario Outline: Verify product details default quantity with multiple dataset

    Given I am on the homepage
    When I click on "Blouse"
    Then I should land on the product details page
    When I click on plus button <plus_button_count> times
    Then The default quantity should be <expected_after_plus>
    When I click on minus button <minus_button_count> times
    Then The default quantity should be <expected_after_minus>





    Examples:
       | plus_button_count | expected_after_plus | minus_button_count | expected_after_minus |
       | 200               | 201                 | 200                | 1                    |
       | 0                 | 1                   | 1                  | 1                    |
       | 50                | 51                  | 10                 | 41                   |
       | 1                 | 2                   | 100                | 1                    |
       | 20                | 21                  | 19                 | 2                    |

