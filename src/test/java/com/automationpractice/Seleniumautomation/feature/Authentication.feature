Feature: Authentication
  Scenario: TC4 – Sign into the website using a username and password stored as a global
  variable.
    When User login with the email "thapelo.mohotsi@interfront.co.za" and password "@Psalm34v7"
    Then Verify the following text links are displayed
         |links|
         |Sign out|
         |Thapelo Daniel Mohotsi|
