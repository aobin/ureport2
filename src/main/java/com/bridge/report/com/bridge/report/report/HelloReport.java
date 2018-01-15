package com.bridge.report.com.bridge.report.report;

import org.springframework.stereotype.Component;

@Component("HelloReport")
public class HelloReport implements Runnable
{
    @Override
    public void run()
    {
        System.out.println("=========sayHello====com.bridge.report.com.bridge.report.report.HelloReport======");
    }
}
