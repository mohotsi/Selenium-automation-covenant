Feature: Navigation

  Scenario:TC6 Navigate to T shirts
    When User Navigate to
      | link     |
      | T-shirts |
    Then verify user is on "T-shirts - My Store" page

  Scenario:TC6 Navigate to Casual Dresses
    When User Navigate to
      | link           |
      | Dresses        |
      | Casual Dresses |
    Then verify user is on "Casual Dresses - My Store" page
