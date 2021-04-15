package com.amazon.mlsTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//{@literal @RunWith(JUnit4.class)}
public class mlsRemove extends App {
  private static ChromeDriverService service;
  private WebDriver driver;

  // {@literal @BeforeClass}
  public static void createAndStartService() throws IOException {
    service = new ChromeDriverService.Builder().usingDriverExecutable(new File("Resources/chromedriver.exe"))
        .usingAnyFreePort().build();
    service.start();
  }

  // {@literal @AfterClass}
  public static void createAndStopService() {
    service.stop();
  }

  // {@literal @Before}
  public void createDriver() {
    driver = new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
  }

  // {@literal @After}
  public void quitDriver() {
    driver.quit();
  }

  Sheet sheet;
  Cell cell;
  String ASIN, marketplace, MercID, MID, region;
  double m1, m2;

  public int ketData() throws IOException {

    FileInputStream finput = null;
    int k;

    finput = new FileInputStream(new File("MLSTool.xlsm"));
    Workbook workbook = WorkbookFactory.create(finput);

    sheet = workbook.getSheetAt(0);

    k = sheet.getLastRowNum();

    /*
     * driver.get(
     * "https://paragon-eu.amazon.com/hz/diagnostic/show?resourcePath=RemoveSKU");
     * try { Thread.sleep(10000); } catch (InterruptedException e) {
     * e.printStackTrace(); }
     */

    return k;
  }

  public void getValues(int j) {

    cell = sheet.getRow(j).getCell(0);
    ASIN = cell.getStringCellValue();
    cell = sheet.getRow(j).getCell(1);
    m1 = cell.getNumericCellValue();
    MercID = String.format("%d", (long) m1);
    cell = sheet.getRow(j).getCell(2);
    region = cell.getStringCellValue();
    cell = sheet.getRow(j).getCell(3);
    marketplace = cell.getStringCellValue();
    cell = sheet.getRow(j).getCell(4);
    m2 = cell.getNumericCellValue();
    MID = String.format("%d", (long) m2);
  }

  // {@literal @Test}
  public void removeTheDumb() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(this.driver, 30);
    this.driver.navigate().to("https://paragon-eu.amazon.com/hz/diagnostic/show?resourcePath=RemoveSKU");
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='user_name_btn']")));
    } catch (NoSuchElementException | TimeoutException e) {
      e.printStackTrace();
    }
    Map<String, String> map = new HashMap<>();
    map.put("NA",
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]/option[2]");
    map.put("EU",
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/div[1]/select[1]/option[3]");
    map.put("FE", "//option[contains(text(),'FE')]");
    map.put("CN", "//option[contains(text(),'CN')]");
    map.put("UK", "//option[contains(text(),'www.amazon.co.uk')]");
    map.put("DE", "//option[contains(text(),'www.amazon.de')]");
    map.put("FR", "//option[contains(text(),'www.amazon.fr')]");
    map.put("ES", "//option[contains(text(),'www.amazon.es')]");
    map.put("AE", "//option[contains(text(),'www.amazon.ae')]");
    map.put("SA", "//option[contains(text(),'www.amazon.sa')]");
    map.put("EG", "//option[contains(text(),'www.amazon.eg')]");
    map.put("junglee", "//option[contains(text(),'www.junglee.com')]");
    map.put("IN", "//option[contains(text(),'www.amazon.in')]");
    map.put("IT", "//option[contains(text(),'www.amazon.it')]");
    map.put("TR", "//option[contains(text(),'www.amazon.com.tr')]");
    map.put("NL", "//option[contains(text(),'www.amazon.com.nl')]");
    map.put("SE", "//option[contains(text(),'www.amazon.com.se')]");
    map.put("PL", "//option[contains(text(),'www.amazon.com.pl')]");
    map.put("CA", "//option[contains(text(),'www.amazon.ca')]");
    map.put("MX", "//option[contains(text(),'www.amazon.com.mx')]");
    map.put("BR", "//option[contains(text(),'www.amazon.com.br')]");
    map.put("US",
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/div[1]/div[1]/select[1]/option[1]");
    map.put("JP", "//option[contains(text(),'www.amazon.jp')]");
    map.put("AU", "//option[contains(text(),'www.amazon.com.au')]");
    map.put("SG", "//option[contains(text(),'www.amazon.com.sg')]");
    map.put("CN", "//option[contains(text(),'www.amazon.cn')]");
    /*try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }*/
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("region")));
    this.driver.findElement(By.xpath(map.get(region))).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]")));
    this.driver.findElement(By.xpath(
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"))
        .sendKeys(new CharSequence[] { this.ASIN });
    this.driver.findElement(By.xpath(
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[2]/div[1]/div[1]/div[1]/input[1]"))
        .sendKeys(new CharSequence[] { this.MercID });
    this.driver.findElement(By.xpath(map.get(marketplace))).click();
    this.driver.findElement(By.xpath(
        "//body/div[@id='diagnosticAppBase']/div[2]/div[1]/div[1]/div[2]/div[1]/step[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/form[1]/div[4]/div[1]/div[1]/div[1]/input[1]"))
        .sendKeys(new CharSequence[] { this.MID });
    this.driver.findElement(By.id("submit")).click();
    try {
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Success')]")));
    } catch (NoSuchElementException | TimeoutException e) {
      e.printStackTrace();
    }
    // this.driver.findElement(By.xpath("//span[contains(text(),'Input the SKU,
    // Merchant ID and Marketplace ID')]")).click();

  }

  public void closewindow() {
    driver.close();
  }
}