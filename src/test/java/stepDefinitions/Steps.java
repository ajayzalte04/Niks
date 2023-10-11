package stepDefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilLayer.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class Steps extends TestBase{


    @Given("launch browser name as br")
    public void launchBrowserNameAsBr() throws IOException {
        String file_path="./config.properties";
        File src=new File(file_path);
        FileInputStream fis=new FileInputStream(src);
        prop=new Properties();
        prop.load(fis);

         br=prop.getProperty("browser");
         //baseURL=prop.getProperty("baseUrl");
         baseURL=prop.getProperty("fbUrl");
         baseURL=prop.getProperty("gUrl");
         baseURL=prop.getProperty("myntraUrl");

        logger= Logger.getLogger("Selenium Practice");
        PropertyConfigurator.configure("log4j.properties");
        logger.info("Ganpati Bappa Morya");

        if(br.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver=new ChromeDriver(options);
            logger.info("browser start successfuly");
        }
        else if(br.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
            logger.info("browser start successfuly");
        }
        else if(br.equalsIgnoreCase("edge"))
        {
            System.setProperty("webdriver.http.factory", "jdk-http-client");
            WebDriverManager.edgedriver().setup();
            driver=new EdgeDriver();
            logger.info("browser start successfuly");
        }
        else
        {
            logger.info("enter proper browser name");
        }
        driver.manage().window().maximize();
        logger.info("browser maximized successfuly");
        driver.manage().deleteAllCookies();
        logger.info("browser cookies deleted successfuly");

    }

    @When("user enter url as {string}")
    public void userEnterUrlAs(String url) {
        driver.get(baseURL);
        logger.info("url start successfuly");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        logger.info("timeout provided successfuly");
    }

    @And("enter actress as {string}")
    public void enterActressAs(String actress) throws InterruptedException {
        WebElement googleSearch=driver.findElement(By.xpath("//textarea[@class='gLFyf']"));
        googleSearch.sendKeys(actress+ Keys.ENTER);
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='CIVrJb oLJ4Uc']/child::img[@id='rimg_3']")).click();
        driver.findElement(By.xpath("//img[@class='r48jcc pT0Scc iPVvYb']")).click();

        Set<String> ids=driver.getWindowHandles();
        Iterator<String> itr=ids.iterator();
        String p= itr.next();
        String c= itr.next();
        driver.switchTo().window(c);
        Thread.sleep(5000);

        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,900)");

        Util.photoCapture(actress, driver);

    }


    @Then("redirected to page title as {string}")
    public void redirectedToPageTitleAs(String title) {
        if(driver.getTitle().equalsIgnoreCase(title))
        {
            logger.info("Rashmika Found");
            Assert.assertTrue(true);

        }
        else {
            logger.info("Rashmika not found");
            Assert.fail();
        }
    }


    //search different actresses
    @And("enter actress_name as {string}")
    public void enterActress_nameAs(String actress) throws InterruptedException {
        WebElement googleSearch=driver.findElement(By.xpath("//textarea[@class='gLFyf']"));
        googleSearch.sendKeys(actress+ Keys.ENTER);
        Thread.sleep(10000);
        Util.photoCapture(actress, driver);
//        driver.findElement(By.xpath("//div[@class='CIVrJb oLJ4Uc']/child::img[@id='rimg_3']")).click();
//        driver.findElement(By.xpath("//img[@class='r48jcc pT0Scc iPVvYb']")).click();
    }

    @Then("close browser")
    public void closeBrowser() {
        driver.close();
    }

    //Myntra shopping
    @And("enter product name in search and click search")
    public void enterProductNameInSearchAndClickSearch() {
        WebElement search = driver.findElement(By.xpath("//input[@class='desktop-searchBar']"));
        search.sendKeys("skybags backpacks", Keys.ENTER);
        JavascriptExecutor js= (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1500)");
        WebElement omBag=driver.findElement(By.cssSelector("img.img-responsive[title='Skybags Unisex Blue Graphic Printed Backpack with Rain Cover']"));
        omBag.click();

    }

    @Then("redirected to page title as type")
    public void redirectedToPageTitleAsType() {
        if(driver.getTitle().equalsIgnoreCase("Skybags Backpacks - Buy Skybags Backpack Online in India | Myntra"))
        {
            Assert.assertTrue(true);
            logger.info("Bag found successfully");
            Util.photoCapture("Skybags", driver);
        }
        else
        {
            Assert.fail();
            logger.info("Myntra SkyBag not found");
        }
    }

    //FB Login
    @And("enter userid as {string} and password as {string}")
    public void enterUseridAsAndPasswordAs(String id, String pw) throws InterruptedException {
        WebElement uid=driver.findElement(By.cssSelector("input#email"));
        uid.sendKeys(id);
        WebElement pass=driver.findElement(By.cssSelector("input#pass"));
        pass.sendKeys(pw);
        WebElement login_Tab=driver.findElement(By.cssSelector("button[name='login']"));
        login_Tab.click();
        Thread.sleep(2000);
    }

    @Then("redirected to homepage")
    public void redirectedToHomepage() {
       Boolean status= driver.getPageSource().contains("You can't use this feature at the moment");
        if(!status)
        {
            logger.info("fb login successfully");
        }
        else
        {
            Assert.assertTrue(true);
            logger.info("fb login failed- incorrect id or password");
            Util.photoCapture("fb_invalid_id", driver);
        }
    }


}
