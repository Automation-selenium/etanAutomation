package testAutomation.ScreenShotListner;

import java.io.*;
import java.util.*;
import java.text.*;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.*;

import org.testng.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import testAutomation.DriverManager.DriverManager;
import testAutomation.testdata.TestConfig;

import javax.imageio.ImageIO;

public class ScreenshotListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String methodName = result.getName();
        if(!result.isSuccess()){
            Screenshot fpScreenshot = new AShot()
                    .shootingStrategy(ShootingStrategies.viewportPasting(100))
                    .takeScreenshot(DriverManager.getDriver());
           try {
               String reportDirectory = TestConfig.OUTPUT_DIRECTORY;
               File destFile = new File((String) reportDirectory+"/failure_screenshots/"+methodName+"_"+formater.format(calendar.getTime())+".png");
               ImageIO.write(fpScreenshot.getImage(), "PNG", destFile);
           }catch (Exception e) {
           }
        }
    }
}
