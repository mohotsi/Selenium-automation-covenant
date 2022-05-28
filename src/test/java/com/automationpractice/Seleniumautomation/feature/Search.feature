Feature: Search Criteria
  Scenario: TC1 search results
  When    : User searches for "Printed Summer Dress"
  Then    : Verify the first result matches "Printed Summer Dress"

  Scenario: TC2 search comma separated
  When   : search comma separated text "Blouse,Faded Short Sleeve T-shirts,Printed Summer Dress"
    Scenario Outline: TC3 – Repeat TC1 again this time using a data driven approach using an external
    datafile such as an excel spreadsheet or text file.
    When : User searches for "<Search>"
      Then    : Verify the first result matches '<Search>'
      Examples:@sources:src/main/resources/data.csv








