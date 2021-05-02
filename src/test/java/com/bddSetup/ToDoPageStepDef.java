package com.bddSetup;


import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.test.pages.ToDoPage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

import cucumber.api.java.en.And;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ToDoPageStepDef {
	private WebDriver driver;
	private ToDoPage toDoPage =null;
	
	@Before
	public void setUp(Scenario scn) {
		System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		
		//launch chromdriver
		driver = new ChromeDriver();
    	toDoPage = new ToDoPage(driver);
	}
	//Given ThingsToDoPage is visible
	@Given("^ThingsToDoPage is visible$")
	  public void ToDo_Page_is_visible() throws Throwable {
		Assert.assertEquals(true, toDoPage.openURL("URL_TO_THINGS_TO_DO_PAGE"));		 
		Assert.assertEquals(true, toDoPage.isPageLoaded("Things to do"));
	}
	//And a text with number of pending required tasks is visible
	@And("^a text with number of pending required tasks is visible$")
	public void a_text_with_number_of_pending_required_tasks_is_visible() throws Throwable{
		Assert.assertEquals(true, toDoPage.isOutstandingTextVisible());
		Assert.assertEquals(true, toDoPage.isOutstandingTextMatching());
	}
	//And <Todo> item is visible
	@And("^(.*?) item is visible$")
	public void item_is_visible(String taskName) throws Throwable {
		Assert.assertEquals(true, toDoPage.isTaskExist(taskName));
	}
	//And "CheckBox" for <Todo> is visible
	@And("^\"CheckBox\" for (.*?) is visible$")
	public void checkBox_is_visible(String taskName) throws Throwable{
		Assert.assertEquals(true, toDoPage.isCompletionCheckBoxVisible(taskName));
	}
	//And "CheckBox" for <Todo> is unchecked 
	@And("^\"CheckBox\" for (.*?) is unchecked$")
	public void checkBox_is_unchecked(String taskName) throws Throwable{
		Assert.assertEquals(true, toDoPage.isCompletionCheckBoxUnchecked(taskName));
	}
	//And <Required> tag for <Todo> is visible as applicable
	@And("^(.*?) tag for (.*?) is visible as applicable$")
	public void required_tag_for_Todo_is_visible_as_applicable(String required, String taskName) throws Throwable{
		Assert.assertEquals(true, toDoPage.isRequiredTagVisible(required, taskName));
	}
	//And <DueDate> Due Date for <Todo> is visible
	@And("^(.*?) Due Date for (.*?) is visible$")
	public void duedate_for_is_visible(String dueDate, String taskName) throws Throwable{
		Assert.assertEquals(true, toDoPage.isDueDateVisible(dueDate, taskName));
	}
	//And <Todo> item is highlighted in orange
	@And("^(.*?) item is highlighted in orange")
	public void item_is_highlighted_in_orange(String taskName) throws Throwable{
		Assert.assertEquals(true, toDoPage.isTaskPending(taskName));
	}
	//When I click on "CheckBox" for <Todo> to complete it
	@When("^When I click on \"CheckBox\" for (.*?) to complete it$")
	public void click_on_checkbox(String taskName) throws Throwable{
		toDoPage.checkOnCompletionCheckBox(taskName);
	}
	//Then I should see <Todo> item getting greyed out
	@Then("^I should see (.*?) item getting greyed out")
	public void item_is_grayedout(String taskName) throws Throwable{
		Assert.assertEquals(true, toDoPage.isTaskCompleted(taskName));
	}
	//Then I should see "Percentage" getting updated on Progress graph
	@Then("^I should see \"Percentage\" getting updated on Progress graph")
	public void check_for_progress() throws Throwable{
		Assert.assertEquals(true, toDoPage.isMatchingRequiredPercent());
		Assert.assertEquals(true, toDoPage.isMatchingOptionalPercent());
	}
	@After
	public void tear() {
	  driver.quit();
	} 

}
