package com.bridge.report.controller;

import com.bstek.ureport.Utils;
import com.bstek.ureport.export.ExportManager;
import com.bstek.ureport.export.html.HtmlReport;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.InetAddress;
import java.net.UnknownHostException;
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

    @RequestMapping("/search")
    public String search()
    {
        System.out.println("=========search=============");
        try
        {
            /*Settings settings = Settings.builder()
                    .put("cluster.name", "myClusterName").build();
            TransportClient client = new PreBuiltTransportClient(settings);*/



            TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));


            /*SearchResponse searchResponse =
                    client.prepareSearch("music").setTypes("lyrics").execute().actionGet();*/

            /*SearchResponse searchResponse = client.prepareSearch("logstash-2015.05.20")
                    .setTypes("")
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.termQuery("multi", "test"))                 // Query
                    .setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                    .setFrom(0).setSize(60).setExplain(true)
                    .get();*/

            SearchResponse searchResponse = client.prepareSearch("logstash-2015.05.20")
                    .setTypes("log")
                    .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                    .setQuery(QueryBuilders.fuzzyQuery("url","borisenko"))
                    .get();

            /*
             .setQuery(QueryBuilders.matchQuery("extension", "jpg"))
            .setQuery(QueryBuilders.matchQuery("utc_time", "2015-05-20T11:02:49.427Z"))
            .setQuery(QueryBuilders.matchQuery("extension", "jpg"))
                .setQuery(QueryBuilders.queryStringQuery("borisenko"))
            */

            SearchHit[] hits = searchResponse.getHits().getHits();
            for(int i=0;i<hits.length;i++)
            {
                System.out.println("======"+i+"===begin====");
                Map<String, Object> source = hits[i].getSource();
                System.out.println("request="+source.get("request"));
                System.out.println("======"+i+"===end====");
            }
            System.out.println("finish=============");


        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }

        return "search";
    }
}
