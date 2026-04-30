package org.district.automation.utility;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static String takeScreenshot(WebDriver driver, String testName) {
        if (driver == null) return null;
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotsDir = System.getProperty("user.dir") + File.separator + "screenshots";
            Path dirPath = Paths.get(screenshotsDir);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
            String fileName = testName + ".png";
            Path dest = dirPath.resolve(fileName);
            Files.copy(src.toPath(), dest, StandardCopyOption.REPLACE_EXISTING);
            return dest.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
