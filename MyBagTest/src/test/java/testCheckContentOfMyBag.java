import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by tescu on 10/1/15.
 */
public class testCheckContentOfMyBag {

    protected WebDriver driver;

    @Before
    public void setUp(){
        driver = new FirefoxDriver();
        driver.get("http://www.marksandspencer.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Given("I have added a shirt to my bag")
    public void I_have_added_a_shirt_to_my_bag(){

        Actions manLink = new Actions(driver);
        manLink.moveToElement(driver.findElement(By.cssSelector("#SC_Level_1_586 > span"))).perform();

        WebElement casualShirts = driver.findElement(By.linkText("Casual Shirts"));
        casualShirts.click();

        Actions firstShirt = new Actions(driver);
        firstShirt.moveToElement(driver.findElement(By.cssSelector("a.prodAnchor"))).click().perform();

        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='linear-route accordion-linear']")));

        Actions sizeS = new Actions(driver);
        sizeS.moveToElement(driver.findElement(By.cssSelector("div.size-info td:nth-child(1) label"))).click().perform();

        WebElement addToCartButton = driver.findElement(By.cssSelector("input[data-adding='Adding']"));
        addToCartButton.click();

        Assert.assertTrue("Popup 'Added to bag' is message displayed",driver.findElement(By.cssSelector("div.bagNotify div.content h4")).isDisplayed());
    }

    @When("I view the contents of my bag")
    public void I_view_the_contents_of_my_bag(){

        Actions basketHeader = new Actions(driver);
        basketHeader.moveToElement(driver.findElement(By.cssSelector("#headerSection > nav > ul > li:nth-child(3) > ul > li > a"))).click().perform();

        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.basket-title-area"))));

        Assert.assertTrue("Check 'Your secure bag' label is displayed",driver.findElement(By.cssSelector("div.basket-title-area")).isDisplayed());
        Assert.assertTrue("Check the content of my bag is contain at least an item",driver.findElement(By.cssSelector("#basket-page > li > div.order-details-wrapper > table > tbody > tr.order-item.no-child.is-parent.order-item--clothing")).isDisplayed());
    }

    @Then("I can see the contents of the bag include a shirt")
    public void I_can_see_the_contents_of_the_bag_include_a_shirt(){

        WebElement basketContent = driver.findElement(By.cssSelector("#basket-page > li > div.order-details-wrapper > table > tbody > tr.order-item.no-child.is-parent.order-item--clothing"));
        Assert.assertTrue("Check if my bag contain a shirt ",basketContent.findElement(By.cssSelector(" td:nth-child(1) > section > div.product-info-wrap > h3 > a")).getText().contains("Shirt"));
    }

    @After
    public void tearDown(){
        driver.close();
    }

}
