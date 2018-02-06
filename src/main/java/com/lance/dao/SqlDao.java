package com.lance.dao;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Corwin on 19.05.2015.
 */

/**First idea was to use database for data storage. But it is resource-intensive and inconvenient to access on Android devices*/
@Deprecated
public interface SqlDao {
    boolean connect(String url, String schema, String user, String password);
    void disconnect();
    void truncateTable(String table);
    boolean insert(Object obj, String table);
    boolean insert(Collection list, String table);
    Object deleteById(int id, String table);
    Object selectById(int id, String table);
    ArrayList selectAll(String table);
    ArrayList selectByDate(String date, String table);
    ArrayList selectByCategory(String category, String table);
}
