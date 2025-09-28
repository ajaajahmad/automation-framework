# Automation Testing Framework

A comprehensive automation testing framework built with Java, Maven, TestNG, and Selenium WebDriver following the Page Object Model design pattern.

**Created by:** ajaajahmad
**Last Updated:** 2025-08-08
**Framework Version:** 1.0.0

## 🚀 Features

- **Page Object Model (POM)** - Organized and maintainable page objects
- **TestNG Integration** - Powerful testing framework with annotations and assertions
- **Maven Build Tool** - Dependency management and build automation
- **Selenium WebDriver** - Cross-browser automation support
- **WebDriverManager** - Automatic driver management
- **Extent Reports** - Beautiful and detailed test reports
- **Log4j2 Logging** - Comprehensive logging framework
- **Configuration Management** - Centralized configuration handling
- **Cross-browser Testing** - Support for Chrome, Firefox, and Edge
- **Data-driven Testing** - Excel-based test data management

## 📁 Project Structure

```
automation-testing-framework/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/automation/
│   │   │   ├── base/
│   │   │   │   ├── BasePage.java           // Common page methods
│   │   │   │   └── BaseTest.java           // Test setup/teardown
│   │   │   ├── pages/
│   │   │   │   └── LandingPage.java        // Single page object for landing page
│   │   │   ├── utils/
│   │   │   │   ├── ConfigReader.java       // Read properties
│   │   │   │   └── DriverManager.java      // Browser setup
│   │   │   └── listeners/
│   │   │       └── TestListener.java       // Test reporting
│   │   └── resources/
│   │       ├── config.properties           // Application configuration
│   │       ├── log4j2.xml                  // Logging configuration
│   │       └── testdata/
│   │           ├── landing-page-data.xlsx  // Test data for landing page
│   │           └── expected-content.properties // Expected text/content
│   └── test/
│       ├── java/com/automation/tests/
│       │   └── LandingPageTest.java        // All landing page tests
│       └── resources/
│           ├── testng.xml                  // Main TestNG suite
│           ├── testng-smoke.xml            // Smoke test suite
│           └── testng-regression.xml       // Regression test suite
└── reports/
    └── test-output/                        // TestNG default reports
```

## 🛠️ Prerequisites

- Java 11 or higher
- Maven 3.6 or higher
- IDE (IntelliJ IDEA or Eclipse recommended)

## 📦 Dependencies

- Selenium WebDriver 4.15.0
- TestNG 7.8.0
- WebDriverManager 5.6.2
- ExtentReports 5.1.1
- Log4j2 2.21.1
- Apache POI 5.2.4

## 🚀 Getting Started

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd automation-testing-framework
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run tests**
   ```bash
   # Run all tests
   mvn test
   
   # Run specific test suite
   mvn test -DsuiteXmlFile=testng.xml
   
   # Run with specific browser
   mvn test -Dbrowser=chrome
   
   # Run with specific environment
   mvn test -Denvironment=qa
   ```

## ⚙️ Configuration

### Browser Configuration
Update `config.properties` to change default browser:
```properties
browser=chrome  # chrome, firefox, edge
```

### Environment Configuration
```properties
environment=qa  # qa, staging, prod
url=https://example.com
```

### Test Data
Place test data files in `src/main/resources/testdata/` directory.

## 📊 Reports

- **Extent Reports**: Generated in `reports/extent-reports/`
- **Screenshots**: Captured on failure in `reports/screenshots/`
- **Logs**: Application logs with detailed execution information

## 🏗️ Framework Architecture

### Base Classes
- `BasePage`: Common page operations and WebDriver initialization
- `BaseTest`: Test setup, teardown, and WebDriver management

### Page Objects
- Follow Page Object Model pattern
- Use PageFactory for element initialization
- Implement fluent interface for method chaining

### Utilities
- `WaitHelper`: Explicit wait operations
- `ExtentReportManager`: Report generation and management
- `ConfigManager`: Configuration property management

## 📝 Writing Tests

### Create a Page Object
```java
public class SamplePage extends BasePage {
    @FindBy(id = "element-id")
    private WebElement element;
    
    public SamplePage(WebDriver driver) {
        super(driver);
    }
    
    public void performAction() {
        clickElement(element);
    }
}
```

### Create a Test Class
```java
public class SampleTest extends BaseTest {
    @Test
    public void testSample() {
        SamplePage page = new SamplePage(driver);
        page.performAction();
        // Add assertions
    }
}
```

## 🤝 Best Practices

1. **Follow naming conventions**
2. **Keep page objects focused and cohesive**
3. **Use meaningful test names and descriptions**
4. **Implement proper wait strategies**
5. **Add comprehensive logging**
6. **Use data providers for data-driven tests**
7. **Implement proper exception handling**

## 🔧 Customization

### Adding New Browsers
Extend the `initializeDriver()` method in `BaseTest.java`

### Adding New Report Types
Implement additional reporting in `ExtentReportManager.java`

### Environment-specific Configurations
Create environment-specific property files

## 📞 Support

For questions and support, please refer to the project documentation or create an issue in the repository.
