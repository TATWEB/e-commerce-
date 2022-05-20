Feature: As a user, I should be able to perform a product search on the website


  Background: common steps for all scenarios
    Given I am on the homepage


  #@test or @temp and @second -> and has more precedence
  @second @regression
  Scenario: Search a product using valid product name1

    When I search for a Blouse
    Then I should land on the search page
    And the search term should be correct


  @sprint2 @regression
  Scenario: Search a product using valid product name2

    When I search for a Blouse
    Then I should land on the search page
    And the search term should be correct


  @test @regression
  Scenario: Search a product using valid product name3

    When I search for a Blouse
    Then I should land on the search page
    And the search term should be correct

  @sprint2 @regression
  Scenario: Search a product using empty search term

    When I do not enter any search term
    Then  I should land on the search page
    And the error message should be there

  @sprint2
  Scenario: Search a product using valid product name Dress

    When I search for a "Dress"
    Then I should land on the search page
    And the search term should be correct

  @sprint2
  Scenario: Search a product using valid product name Dress

    When I search for a "Summer"
    Then I should land on the search page
    And the search term should be correct


  @sprint2
  Scenario: Search a product using valid product name Dress

    When I search for a "Shirt"
    Then I should land on the search page
    And the search term should be correct


  Scenario: Demo of cucumber exception ambiguous step exception
    When I search for a Blouse





    When I pass this information
      | John                         |
      | Doe                          |
      | 12/09/1999                   |
      | 235-345-1234                 |
      | 322-09-6322                  |
      | 123 Main St, Vienna VA 22056 |
 # this is a syntax for List of Strings   -> List<String>





  Scenario: Example of Datatable List

    When I pass this information as List of Lists
      | John | Doe | 12/09/1999 | 235-345-1234 | 322-09-6322 | 123 Main St, Vienna VA 22056 |
 # this is a syntax for List of Lists   -> List< List<String> >



  Scenario: Example of Datatable List of Maps

    When I pass this information as List of Maps
      | First | Last | dob        | phone        | ssn         | address                      |
      | John  | Doe  | 12/09/1999 | 235-345-1234 | 322-09-6322 | 123 Main St, Vienna VA 22056 |

 # this is a syntax for List of Lists -> List<Maps <String, String> > --> need to have the header as the first row








