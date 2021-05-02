package com.test.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.test.common.BaseFunctions;

public class ToDoPage extends BaseFunctions{
	private WebDriver driver;
	
	@FindBy(id="outstanding_tasks")	
	WebElement outstandingTasks;
	
	@FindBy(xpath="//ul[@id=\'listOfTasks\']") 
	WebElement taskList;
	
	@FindBy(xpath="//ul[@id=\'listOfTasks\' and li[input[@type='checkbox' and not(@checked)] and label[text()=\'Required\']]]")
	List<WebElement> pendingRequiredTasks;

	@FindBy(xpath="//ul[@id=\'listOfTasks\' and li[input[@type='checkbox' and @checked] and label[text()=\'Required\']]]")
	List<WebElement> completedRequiredTasks;

	@FindBy(xpath="//ul[@id=\'listOfTasks\' and li[input[@type='checkbox' and not(@checked)] and not(label[text()=\'Required\'])]]")
	List<WebElement> pendingOptionalTasks;

	@FindBy(xpath="//ul[@id=\'listOfTasks\' and li[input[@type='checkbox' and @checked] and not(label[text()=\'Required\'])]]")
	List<WebElement> completedOptionalTasks;
	

	@FindBy(xpath="//*[@id='requiredCompletionPercent']")
	WebElement requiredCompletionPercent;
	
	@FindBy(xpath="//*[@id='optionalCompletionPercent']")
	WebElement optionalCompletionPercent;
	
	//constructor
	public ToDoPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isOutstandingTextVisible() {
		return isElementVisible(outstandingTasks);
	}
	public boolean isOutstandingTextMatching() {
		return isTextMatching(outstandingTasks, Integer.toString(getCountOfPendingRequiredTasks()));
	}
	public boolean openURL(String url) {
		boolean retVal=false;
		try {
			driver.get(url);
			retVal = true;
		}catch(Exception e) {
			retVal=false;
			e.printStackTrace();
		}
		return retVal;
	}
	
	public boolean isPageLoaded(String pageTitle) {
		return isPageVisible(pageTitle);
	}
	
	//check if a task is pending
	public boolean isTaskPending(String taskName) {
		String elementXpath = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='checkbox' and not(@checked)] and text()='" + taskName + "' and @style='color: orange' ]]]");
		return isVisibleByXpath(elementXpath);
	}
	//check if a task is optional
	public boolean isTaskCompleted(String taskName) {
		String elementXpath = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='checkbox' and @checked] and text()='" + taskName + "' and @style='color: gray']]]");
		return isVisibleByXpath(elementXpath);
	}
	//check if check Box for a task is visible
	public boolean isRequiredTagVisible(String required, String taskName) {
		String elementXpath ="";
		if(required.equalsIgnoreCase("true")) {
			elementXpath = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='text' and text() = 'Required'] and text() = '" + taskName + "']]");
		}else {
			elementXpath = String.format("//ul[@id=\\'listOfTasks\\' and li[not(input[@type='text' and text() = 'Required']) and text() = '" + taskName + "']]");
		}
		return isVisibleByXpath(elementXpath);
	}
	//check if check Box for a task is visible
	public boolean isDueDateVisible(String dueDate, String taskName) {
		String elementXpath  = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='text' and text() ='"+ dueDate +"' ] and text() = '" + taskName + "']]");
		return isVisibleByXpath(elementXpath);
	}
	//check if check Box for a task is visible
	public boolean isCompletionCheckBoxVisible(String taskName) {
		String elementXpath  = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='checkbox'] and text() = '" + taskName + "']]");
		return isVisibleByXpath(elementXpath);
	}
	//check if check Box for a task is unchecked
	public boolean isCompletionCheckBoxUnchecked(String taskName) {
		String elementXpath  = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='checkbox' and not(@checked)] and text() = '" + taskName + "']]");
		return isEnabledByXpath(elementXpath);
	}
	//check if a given task exists
	public boolean isTaskExist(String taskName) {
		String elementXpath  = String.format("//ul[@id=\\'listOfTasks\\' and li[text() = '" + taskName + "']]");
		return isVisibleByXpath(elementXpath);
	}	
	//Check box to complete or pending for a specific task
	public void checkOnCompletionCheckBox(String taskName) {
		String elementXpath = String.format("//ul[@id=\\'listOfTasks\\' and li[input[@type='checkbox'] and text() = '" + taskName + "']]");
		clickByXpath(elementXpath);
	}
	//get count of Pending Required Tasks
	private int getCountOfPendingRequiredTasks() {
		return pendingRequiredTasks.size();
	}
	
	//get count of Pending optional Tasks
	private int getCountOfPendingOptionalTasks() {
		return pendingOptionalTasks.size();
	}
	//get count of Completed Required Tasks
	private int getCountOfCompletedRequiredTasks() {
		return completedRequiredTasks.size();
	}
	
	//get count of Completed optional Tasks
	private int getCountOfCompletedOptionalTasks() {
		return completedOptionalTasks.size();
	}	
	//get Required Percent
	private double getRequiredPercent() {
		int totalRequiredTasks = getCountOfPendingRequiredTasks() + getCountOfCompletedRequiredTasks();
		return (getCountOfCompletedRequiredTasks() / totalRequiredTasks) * 100;
	}
	//get Optional Percent
	private double getOptionalPercent() {
		int totalOptionalTasks = getCountOfPendingOptionalTasks() + getCountOfCompletedOptionalTasks();
		return (getCountOfCompletedOptionalTasks()/ totalOptionalTasks) * 100;
	}
	//match required percent with UI
	public boolean isMatchingRequiredPercent() {
		return isTextMatching(requiredCompletionPercent, Double.toString(getRequiredPercent()));
	}
	//match optional percent with UI
	public boolean isMatchingOptionalPercent() {
		return isTextMatching(optionalCompletionPercent, Double.toString(getOptionalPercent()));
	}
}
