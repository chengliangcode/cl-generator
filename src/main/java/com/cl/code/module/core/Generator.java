package com.cl.code;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author chengliang
 * @since 1.0.0
 */
public class Generator {

    public static void main(String[] args) {
        String databaseUrl = "jdbc:mysql://43.138.153.212:3306?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai";
        Connection connection = DatabaseConnection.getConnection(databaseUrl, "root", "123456");

        DatabaseMetaData metaData = null;
        try {
            String schemaPattern = "cl_test";
            String tableNamePattern = "user_item";
            metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, schemaPattern, tableNamePattern, null);
            ResultSet columns = metaData.getColumns(null, schemaPattern, tableNamePattern, null);

            System.out.println("Table Structure for: " + tableNamePattern);

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                String tableType = tables.getString("TABLE_TYPE");
                String tableComment = tables.getString("REMARKS");
                System.out.println("Table Name: " + tableName);
                System.out.println("Table Type: " + tableType);
                System.out.println("Table Comment: " + tableComment);
            }
            System.out.println("Column Name | Data Type | Is Nullable");
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String dataType = columns.getString("TYPE_NAME");
                String isNullable = columns.getString("IS_NULLABLE");
                System.out.println(columnName + " | " + dataType + " | " + isNullable);
            }

            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
