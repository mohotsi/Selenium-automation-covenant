Feature: Transaction

  Scenario: TC5 – This test case needs to have the following steps:
    When User Add an item(s) to shopping your cart.
    |Item|
    |Printed Summer Dress|
    And navigate to cart
    Then Verify that the Item(s) displayed on cart are the one that were added
     When Set quantity amount to "3" for product "Printed Summer Dress"; verify displayed total matches calculated total.




