Feature: VideoGAme API Features

   @videoGameApi
  Scenario: Verify GET /videogames endpoint

    Given the baseURI is initialized and the header "Accept" , "application/json" is set
    When I send a GET request to "/videogames" endpoint
    Then The status code should be 200, the header should be "Content-Type", "application/json" and the returned response should have 10 items




