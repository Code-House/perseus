package org.connectorio.helenus.context.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();

        String path = Main.class
                .getClassLoader()
                .getResource("hbase-site.xml")
                .getPath();
        config.addResource(new Path(path));

        TableName table1 = TableName.valueOf("Table1");
        String family1 = "Family1";
        String family2 = "Family2";

        Connection connection = ConnectionFactory.createConnection(config);
        Admin admin = connection.getAdmin();

        HTableDescriptor desc = new HTableDescriptor(table1);
        desc.addFamily(new HColumnDescriptor(family1));
        desc.addFamily(new HColumnDescriptor(family2));
        admin.createTable(desc);

        byte[] row1 = Bytes.toBytes("row1");
        Put p = new Put(row1);
        byte[] qualifier1 = Bytes.toBytes("col1");
        p.addImmutable(family1.getBytes(), qualifier1, Bytes.toBytes("cell_data"));
        //table1.put(p);
    }



}
