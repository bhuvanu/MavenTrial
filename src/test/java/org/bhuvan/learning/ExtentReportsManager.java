package org.bhuvan.learning;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import org.apache.commons.lang3.SystemUtils;

import java.util.Date;

public class ExtentReportsManager {
    private static ExtentReports extent;

    public static ExtentReports getReport(){
        if (extent == null){
            Date date = new Date();
          //  String filename = date.toString() + ".html";
            String filename = date.toString().replace(" ", "-").replace(":", "-") + ".html";

            extent = new ExtentReports(SystemUtils.getUserDir() + "\\target\\extentReports\\" + filename, true, DisplayOrder.NEWEST_FIRST);

        }

        return extent;
    }
}
