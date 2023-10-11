package utilLayer;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import stepDefinitions.TestBase;

import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

public class Util extends TestBase {


        public static void photoCapture(String filename, WebDriver driver)
        {
            TakesScreenshot ts=(TakesScreenshot)driver;
            File src=ts.getScreenshotAs(OutputType.FILE);
//		String path=".//captures//";
            LocalTime time=LocalTime.now();
//		File des=new File(path+filename+"_"+time+".png");
            String path=".\\Captures\\";
            File des=new File(path+filename+"_"+System.currentTimeMillis()+".png");
            try {
                FileHandler.copy(src, des);

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();

            }
        }

    }


