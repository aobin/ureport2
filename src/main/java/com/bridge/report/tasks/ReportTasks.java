package com.bridge.report.tasks;

import com.bridge.report.com.bridge.report.config.ApplicationContextProvider;
import com.bridge.report.com.bridge.report.report.HelloReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component("reportTasks")
public class ReportTasks
{

    @Autowired
    private HelloReport helloReport;

    private ApplicationContext applicationContext;

    @Autowired
    public ReportTasks(ApplicationContextProvider applicationContextProvider)
    {
        this.applicationContext = applicationContextProvider.getContext();
    }

    public void sayHello()
    {
        helloReport.run();
    }

    public void retry()
    {
        System.out.println("==============retry begin==================");

        List<String> failedReportNameList = new ArrayList<>();
        failedReportNameList.add("HelloReport");

        ExecutorService executorService = Executors.newFixedThreadPool(failedReportNameList.size());

        try
        {
            executorService.submit((Runnable) applicationContext.getBean(failedReportNameList.get(0)));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println("==============retry end==================");

    }

}
