package testAutomation.UIElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testAutomation.utils.LocatorType;

import java.util.ArrayList;
import java.util.List;

public class UIElements {

    private String elementName;
    private String elementIdentifier;
    private LocatorType.Locator identifierType;
    private By by;

    public UIElements(String elementName, LocatorType.Locator locatorType, String locator) {

        this.elementName = elementName;
        elementIdentifier = locator;
        identifierType = locatorType;
        by = getByObjectForElementLocator(locatorType, locator);
    }

    public String toString() {
        return "Element Name [ " + elementName + " ] Element Identifier [ " + elementIdentifier + " ] Locator Type [ " + identifierType.toString() + " ].";

    }

    public By getByObjectForElementLocator(LocatorType.Locator type, String locator)
    {
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

    public List<UIElement> getUIElementList(WebDriver driver, long timeToWait){
        List<WebElement> elements = driver.findElements(by);
        List<UIElement> uiElements = new ArrayList<>();
        for(int i = 0; i < elements.size(); i++){
            UIElement e = new UIElement(elementName, identifierType, elementIdentifier, i);
            uiElements.add(e);
        }
        return uiElements;
    }
}
