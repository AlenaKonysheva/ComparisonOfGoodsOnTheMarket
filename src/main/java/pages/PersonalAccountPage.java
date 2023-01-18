package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAccountPage extends BasePage{
	public PersonalAccountPage(WebDriver driver) {
		super(driver);
	}

	/**
	 *WebElements
	 */
	@FindBy(css = "[title=\"О себе\"]")
	private WebElement aboutMeTitle;

	/**
	 * Methods
	 */
	public void gotoAboutMe() {
		aboutMeTitle.click();
	}
}
