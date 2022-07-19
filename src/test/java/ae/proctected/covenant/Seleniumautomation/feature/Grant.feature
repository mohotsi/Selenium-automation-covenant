Feature: Grant
  Scenario: Create the connection between the host powershell and the covenant
  Given Proceed to login page
   When Login into covenant with userName "thape.mohotsi" and password "@Psalm34v7"
   And  Navigate to "Listeners"
   And Create a Listener
   And Navigate to "Launchers"
   And select Launcher "PowerShell"
    And Download launcher
    And  Execute the launcher file on the target machine
    And Navigate to "Grants"
