package com.bridge.report.controller;

import com.bstek.ureport.Utils;
import com.bstek.ureport.export.ExportManager;
import com.bstek.ureport.export.html.HtmlReport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/report")
public class ReportController
{
    @RequestMapping("/test")
    public String index()
    {
        System.out.println("==========test============");
        return "test";
    }

    @RequestMapping("/printer_number")
    public String printerNumber()
    {
        System.out.println("=========printer_number=============");

        ExportManager exportManager=(ExportManager) Utils.getApplicationContext().getBean(ExportManager.BEAN_ID);
        Map<String,Object> parameters=new HashMap<String,Object>();
        HtmlReport htmlReport = exportManager.exportHtml("file:printer_number.ureport.xml","/",parameters);
        String html = htmlReport.getContent();
        String style = htmlReport.getStyle();
        System.out.println("html="+html);
        System.out.println("style="+style);

        return "printer_number";
    }
}
