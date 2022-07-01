Feature: Duobank API Features

   @api
  Scenario: Verify GET /getmortagage.php endpoint

     Given I am logged in as a valid user with username "duotechb5@gmail.com"  and password "duotechb5" and I have the JWT token
     When I send a GET request to a "/getmortagage.php" endpoint
     Then I should be able to retrieve all the mortgage applications that belong to me








