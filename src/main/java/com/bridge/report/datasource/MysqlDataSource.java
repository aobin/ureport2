package com.bridge.report.datasource;

import com.bstek.ureport.definition.datasource.BuildinDatasource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlDataSource implements BuildinDatasource
{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }

    @Override
    public String name()
    {
        return "Mysql";
    }

    @Override
    public Connection getConnection()
    {
        try
        {
            return this.dataSource.getConnection();
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
