package com.bridge.report.tasks;

import com.bridge.report.com.bridge.report.report.HelloReport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("reportTasks")
public class ReportTasks
{

    public void sayHello()
    {
        new HelloReport().run();
    }


    public void retry()
    {
        System.out.println("==============retry begin==================");

        List<String> failedReportNameList = new ArrayList<>();
        failedReportNameList.add("com.bridge.report.com.bridge.report.report.HelloReport");

        ExecutorService executorService = Executors.newFixedThreadPool(failedReportNameList.size());
        try
        {
            executorService.submit((Runnable) Class.forName(failedReportNameList.get(0)).newInstance());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("==============retry end==================");

    }

}
