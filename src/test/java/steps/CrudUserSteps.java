package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import definition.CRUDProcess;

import org.junit.Assert;
import support.RESTSupport;

public class CrudUserSteps {

    private String url = "";

    @Given("^User is on Agapito web page$")
    public void userIsOnAgapitoWebPage() {
       CRUDProcess.clearFields();
    }

    @And("^User fields the input \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void userFieldsTheInputWithValue(String field, String value) throws Throwable {
        CRUDProcess.addFields(field, value);
    }

    @When("^User clicks on button save User$")
    public void userClicksOnButtonSaveUser() {
        RESTSupport.executePost(CRUDProcess.getUrl(), CRUDProcess.getFields());
        CRUDProcess.setLastId(RESTSupport.key("id").toString());
    }

    @When("^User clicks on button save User without ID$")
    public void userClicksOnButtonSaveUserSemId() {
        RESTSupport.executePost(CRUDProcess.getUrl(), CRUDProcess.getFields());
    }

    @When("^user search the last saved$")
    public void userSearchTheLastSaved() {
        RESTSupport.executeGet(CRUDProcess.getUrl() + "/" + CRUDProcess.getLastId());
    }


    @Then("^User should see the field \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void userShouldSeeTheFieldWithValue(String field, String value){
        Assert.assertEquals(value, RESTSupport.key(field).toString());
    }

    @And("^the user update \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void theUserUpdateWithValue(String field, String value) throws Throwable {
        CRUDProcess.addFields(field, value);
    }

    @When("^the user clicks on update button$")
    public void theUserClicksOnUpdateButton() {
        String url = CRUDProcess.getUrl() + "/" + CRUDProcess.getLastId() ;
        RESTSupport.executePut(url, CRUDProcess.getFields());
    }

    @When("^the user reads the last update$")
    public void theUserReadsTheLastUpdate() {
        String url = CRUDProcess.getUrl() + "/" + CRUDProcess.getLastId() ;
        RESTSupport.executeGet(url);
    }

    @Then("^the user should be \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void theUserShouldBeWithValue(String field, String value) throws Throwable {
        Assert.assertEquals(value, RESTSupport.key(field).toString());
    }

    @When("^the user clicks on update button with invalid id$")
    public void theUserClicksOnupdateButtonWithInvalidId() {
        RESTSupport.executePut(CRUDProcess.getUrl() + "/43214234", CRUDProcess.getFields());
    }

    @And("^user clicks on delete button$")
    public void userClicksOnDeleteButton() {
        String url = CRUDProcess.getUrl() + "/" + CRUDProcess.getLastId() ;
        RESTSupport.executeDelete(url);
    }

    @When("^the user clicks on delete butto with invalid id$")
    public void theUserClicksOnDeleteButtoWithInvalidId() {
        String url = CRUDProcess.getUrl() + "/43242345345" ;
        RESTSupport.executeDelete(url);
    }
}
