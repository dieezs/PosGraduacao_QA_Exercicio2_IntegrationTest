package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import definition.CRUDUser;

import org.junit.Assert;
import support.RESTSupport;

public class CrudUserSteps {

    private String url = "";

    @Given("^User is on Agapito web page$")
    public void userIsOnAgapitoWebPage() {
       CRUDUser.clearFields();
    }

    @And("^User fields the input \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void userFieldsTheInputWithValue(String field, String value) throws Throwable {
        CRUDUser.addFields(field, value);
    }

    @When("^User clicks on button save User$")
    public void userClicksOnButtonSaveUser() {
        RESTSupport.executePost(CRUDUser.getUrl(), CRUDUser.getFields());
        CRUDUser.setLastId(RESTSupport.key("id").toString());
    }

    @When("^User clicks on button save User without ID$")
    public void userClicksOnButtonSaveUserSemId() {
        RESTSupport.executePost(CRUDUser.getUrl(), CRUDUser.getFields());
    }

    @When("^user search the last saved$")
    public void userSearchTheLastSaved() {
        RESTSupport.executeGet(CRUDUser.getUrl() + "/" + CRUDUser.getLastId());
    }


    @Then("^User should see the field \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void userShouldSeeTheFieldWithValue(String field, String value){
        Assert.assertEquals(value, RESTSupport.key(field).toString());
    }

    @And("^the user update \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void theUserUpdateWithValue(String field, String value) throws Throwable {
        CRUDUser.addFields(field, value);
    }

    @When("^the user clicks on update button$")
    public void theUserClicksOnUpdateButton() {
        String url = CRUDUser.getUrl() + "/" +CRUDUser.getLastId() ;
        RESTSupport.executePut(url, CRUDUser.getFields());
    }

    @When("^the user reads the last update$")
    public void theUserReadsTheLastUpdate() {
        String url = CRUDUser.getUrl() + "/" +CRUDUser.getLastId() ;
        RESTSupport.executeGet(url);
    }

    @Then("^the user should be \"([^\"]*)\" with value \"([^\"]*)\"$")
    public void theUserShouldBeWithValue(String field, String value) throws Throwable {
        Assert.assertEquals(value, RESTSupport.key(field).toString());
    }

    @When("^the user clicks on update button with invalid id$")
    public void theUserClicksOnupdateButtonWithInvalidId() {
        RESTSupport.executePut(CRUDUser.getUrl() + "/43214234", CRUDUser.getFields());
    }

    @And("^user clicks on delete button$")
    public void userClicksOnDeleteButton() {
        String url = CRUDUser.getUrl() + "/" +CRUDUser.getLastId() ;
        RESTSupport.executeDelete(url);
    }

    @When("^the user clicks on delete butto with invalid id$")
    public void theUserClicksOnDeleteButtoWithInvalidId() {
        String url = CRUDUser.getUrl() + "/43242345345" ;
        RESTSupport.executeDelete(url);
    }
}
