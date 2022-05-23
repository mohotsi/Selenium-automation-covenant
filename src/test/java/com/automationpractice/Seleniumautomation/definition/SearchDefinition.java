package com.automationpractice.Seleniumautomation.definition;

import com.automationpractice.Seleniumautomation.Page.HomePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SearchDefinition {
    @Autowired
    @Lazy
    HomePage homePage;

    @When(": User searches for {string}")
    public void userSearchesFor(String searchText) {
        homePage.performASearchCriteria(searchText);
    }


    @Then(": Verify the first result matches {string}")
    public void verifyTheFirstResultMatches(String searchText) {
        val firstProductName = homePage.getProducts().getNames().stream().map(WebElement::getText).findFirst().orElse(null);

        assertThat("the first item name doesn't match the search criteria", firstProductName, equalToIgnoringCase(searchText));
    }


    @When(": Store search criteria in one variable comma separated {string}")
    public void storeSearchCriteriaInOneVariableCommaSeparated(String commaSeparatedText) throws Throwable {

    } // Write code here that turns the phrase above into concrete actions    throw new cucumber.api.PendingException();}

    @When(": search comma separated text {string}")
    public void searchCommaSeparatedText(String commaSeparatedText) {
        Arrays.stream(commaSeparatedText.split(",")).
                forEach(search -> {
                    userSearchesFor(search);
                    verifyTheFirstResultMatches(search);
                });
    }


}