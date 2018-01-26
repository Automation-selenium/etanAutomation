package testAutomation.UIElements;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import testAutomation.DriverManager.DriverManager;
import testAutomation.testdata.TestCaseNameManager;
import testAutomation.testdata.TestConfig;
import testAutomation.utils.LocatorType;

import javax.imageio.ImageIO;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


public class UIElement {

    private String elementName;
    private String elementIdentifier;
    private LocatorType.Locator identifierType;
    private By by;
    private  int index=-1;

    public UIElement(String elementName, LocatorType.Locator locatorType, String locator) {

        this.elementName = elementName;
        elementIdentifier = locator;
        identifierType = locatorType;
        by = getByObjectForElementLocator(locatorType, locator);
        index = -1;
    }

    public UIElement(String elementName, LocatorType.Locator locatorType, String locator, int i) {

        this.elementName = elementName;
        elementIdentifier = locator;
        identifierType = locatorType;
        by = getByObjectForElementLocator(locatorType, locator);
        index = i;
    }

    private WebElement getWebElement(){
        if(index == -1){
            return DriverManager.getDriver().findElement(by);
        }else {
            List<WebElement>l = DriverManager.getDriver().findElements(by);
            if(l.size() < index + 1){
                return  null;
            }else {
                return l.get(index);
            }
        }
    }

    public String toString() {
        return "Element Name [ " + elementName + " ] Element Identifier [ " + elementIdentifier + " ] Locator Type [ " + identifierType.toString() + " ].";

    }

    public By getByObjectForElementLocator(LocatorType.Locator type, String locator) {
        switch (type)
        {
            case CLASSNAME: return By.className(locator);
            case CSS: return By.cssSelector(locator);
            case ID: return By.id(locator);
            case LINKTEXT: return By.linkText(locator);
            case NAME: return By.name(locator);
            case PARTIALLINKTEXT: return By.partialLinkText(locator);
            case TAG: return By.tagName(locator);
            case XPATH: return By.xpath(locator);
            default: throw new IllegalArgumentException("Invalid Element Locator Type Specified");
        }
    }

    public boolean isPresent(long timeToWait) {
        try {
            (new WebDriverWait(DriverManager.getDriver(), timeToWait))
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isPresent() {
        return this.isPresent(TestConfig.DEFAULT_WAIT);
    }


    public boolean sendKeys(String text, long timeToWait) {
        boolean result = isPresent(timeToWait);
        if(result) {
            getWebElement().sendKeys(text);
            return true;
        }
        return false;
    }

    public boolean sendKeys(String text) {
        return sendKeys(text, TestConfig.DEFAULT_WAIT);
    }

    public boolean isClickable(long timeToWait) {
        try {
            (new WebDriverWait(DriverManager.getDriver(), timeToWait))
                    .until(ExpectedConditions.elementToBeClickable(by));
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isClickable() {
       return isClickable(TestConfig.DEFAULT_WAIT);
    }

    public boolean isEnabled(long timeToWait)
    {
        try {
            return getWebElement().isEnabled();
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean isEnabled()
    {
       return this.isEnabled(TestConfig.DEFAULT_WAIT);
    }

    public boolean click(long timeToWait) {

            boolean clicked = false;
            boolean result = isClickable(timeToWait) && isEnabled(timeToWait);

            if(result) {
                try{
                    Calendar calendar = Calendar.getInstance();
                    SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
                    Screenshot fpScreenshot = new AShot()
                            .shootingStrategy(ShootingStrategies.viewportPasting(100))
                            .takeScreenshot(DriverManager.getDriver());
                    String reportDirectory = TestConfig.OUTPUT_DIRECTORY;
                    File destFile = new File((String) reportDirectory+"\\click_screenshots\\"+ TestCaseNameManager.getTestName()+"_"+this.elementName+"_"+formater.format(calendar.getTime())+".png");
                    ImageIO.write(fpScreenshot.getImage(), "PNG", destFile);
                    Thread.sleep(100);
                    scrollToElement(timeToWait);
                    getWebElement().click();
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
            return false;
    }

    public boolean click() {
        return click(TestConfig.DEFAULT_WAIT);
    }

    public boolean hasLoaded(long timeToWait)
    {
        try {
            (new WebDriverWait(DriverManager.getDriver(), timeToWait))
                    .until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean hasLoaded()
    {
        return hasLoaded(TestConfig.DEFAULT_WAIT);
    }

    public boolean isVisible(long timeToWait) {
        try {
            boolean result = hasLoaded(timeToWait);
            return result;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean isVisible() {
       return isVisible(TestConfig.DEFAULT_WAIT);
    }


    public String getText(long timeToWait) {

        if(isVisible(timeToWait)) {
            return getWebElement().getText();
        }else {
            return "";
        }
    }

    public String getText() {
        return getText(TestConfig.DEFAULT_WAIT);
    }


    public String getAttribute(String attribute, long timeToWait) {
        if(isVisible(timeToWait)) {
            return getWebElement().getAttribute(attribute);
        }else {
            return "";
        }
    }

    public String getAttribute(String attribute) {
        return getAttribute(attribute, TestConfig.DEFAULT_WAIT);
    }


    public boolean hasUnloaded(long timeToWait) {
        boolean result = isVisible( 0);
        //Element is not visible even after the time  we are required to wait to see if it becomes invisible
        //It is therefore in fact invisible and we can return true
        if(!result)
            return true;
        try {
            (new WebDriverWait(DriverManager.getDriver(), timeToWait))
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean hasUnloaded() {
        return hasLoaded(TestConfig.DEFAULT_WAIT);
    }



    public boolean clear(long timeToWait) {
        boolean result = isVisible(timeToWait);
        if(result) {
            //this will not generate the any keyboard or mouse event that fires on clearing the text field manually
            //use send key with backspace char sequence for such scenarios.
            getWebElement().clear();
            return true;
        }
        return false;
    }

    public boolean clear() {
       return clear(TestConfig.DEFAULT_WAIT);
    }

    public boolean scrollToElement(long timeToWait) {
        boolean result = isVisible(timeToWait);
        boolean isScrolled = false;
        try {
            if (result) {
                ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView(true);", getWebElement());
                return true;
            }
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public boolean scrollToElement() {
       return scrollToElement(TestConfig.DEFAULT_WAIT);
    }


    public Point getLocation(long timeToWait)
    {
        boolean result = isVisible(timeToWait);

        if(result) {
            return getWebElement().getLocation();
        }
        return null;
    }

    public Point getLocation()
    {
        return getLocation(TestConfig.DEFAULT_WAIT);
    }

    public Dimension getSize(long timeToWait)
    {
        boolean result = isVisible(timeToWait);

        if(result) {
            return getWebElement().getSize();
        }
        return null;

    }

    public Dimension getSize()
    {
        return getSize(TestConfig.DEFAULT_WAIT);

    }


    public int getHeight(long timeToWait) {
        Dimension d = getSize(timeToWait);
        if(d != null){
            return d.getHeight();
        }
        return -1;
    }

    public int getHeight() {
        return getHeight(TestConfig.DEFAULT_WAIT);
    }


    public int getWidth(long timeToWait) {
        Dimension d = getSize(timeToWait);
        if(d != null){
            return d.getWidth();
        }
        return -1;
    }

    public int getWidth() {
       return getWidth(TestConfig.DEFAULT_WAIT);
    }

    /*** Condition for presence of at least one of many elements.
     *
     * @param elementList: LIst of UIElements
     *            A list of UIElement to be sought.
     * @return Boolean T if at least one element is present, F otherwise.
     */
    private static ExpectedCondition<Boolean> somethingIsPresent(final List<UIElement> elementList) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                boolean found = false;
                for (UIElement element : elementList) {
                    if (element.isVisible(0)) {
                        found = true;
                        break;
                    }
                }
                return found;
            }
        };
    }

    public static boolean isOneOfTheElementsVisible(List<UIElement> elementList, long timeToWait)
    {
        try {
            new WebDriverWait(DriverManager.getDriver(), timeToWait)
                    .until(somethingIsPresent(elementList));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isOneOfTheElementsVisible(List<UIElement> elementList)
    {
      return isOneOfTheElementsVisible(elementList, TestConfig.DEFAULT_WAIT);
    }
}

