Feature: List of things to do during onboarding
    As a user I should be able to
    ## This scenario contains steps for verifying list of things if they are visible with Required tag
    ## It also has steps to verify if completion of a task changes percentage in Graph
    Background:
    	Given ThingsToDoPage is visible
    Scenario Outline: Complete multiple <Todo> in list of things to do and verify progress
        Given a text with number of pending required tasks is visible
        And <Todo> item is visible
        And "CheckBox" for <Todo> is visible
        And "CheckBox" for <Todo> is unchecked 
        And <Required> tag for <Todo> is visible as applicable 
        And <DueDate> Due Date for <Todo> is visible 
        And <Todo> item is highlighted in orange  
        When I click on "CheckBox" for <Todo> to complete it
        Then I should see <Todo> item getting greyed out
        Then I should see "Percentage" getting updated on Progress graph

        Examples:
            | Todo                                        | Required  | DueDate    | 
            | Tell us about yourself                      | true      | 17/08/2016 | 
            | Tell us your tax file number                | true      | 17/08/2016 | 
            | Choice of super                             | true      | 17/08/2016 | 
            | Your contract                               | true      | 17/08/2016 | 
            | Bank account details                        | true      | 17/08/2016 | 
            | View our workspace Health and Safety Policy | true      | 17/08/2016 | 
            | Latest News                                 | false     | 17/08/2016 | 