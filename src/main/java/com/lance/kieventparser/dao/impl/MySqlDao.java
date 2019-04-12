package com.lance.kieventparser.dao.impl;

import com.lance.kieventparser.dao.SqlDao;
import com.lance.kieventparser.entities.Event;
import com.lance.kieventparser.loggers.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Corwin on 19.05.2015.
 */

/**First idea was to use database for data storage. But it is resource-intensive and inconvenient to access on Android devices*/
@Deprecated
public class MySqlDao implements SqlDao {
    private List<Event> events;
    private Connection connection;
    private Logger logger;
    private String url, schema, user, password;

    @Override
    public Object selectById(int id, String table) {
        Event event = new Event();
        String query = "select * from `"+schema+"`.`"+table+"` where `id`="+id+";";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                event.setAddress(result.getString("address"));
                //event.setCategory(result.getString("category"));
                event.setDate(result.getString("date"));
                event.setImage(result.getString("image"));
                event.setPrice(result.getString("price"));
                event.setTime(result.getString("time"));
                event.setTitle(result.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }
    @Override
    public void truncateTable(String table) {
        String query = "truncate table `"+schema+"`.`"+table+"`;";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(query);
            stmt.execute();
        } catch (SQLException e) {
            logger.log("Table truncation failed;");
            e.printStackTrace();
        }
    }


    public MySqlDao() {
    }
    @Override
    public boolean connect(String url, String schema,  String user, String password) {
        events = new ArrayList();
        logger = new Logger();
        this.url = url;
        this.schema = schema;
        this.user = user;
        this.password = password;
        String DBurl = "jdbc:mysql://"+url+"/"+schema;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DBurl, user, password);
            logger.log("Connection to \"" + url + "\" established;");
            return true;
        } catch (SQLException e) {
            logger.log("Failed to connect to \"" + url + "\";");
            //e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            logger.log("Failed to implement jdbc driver;");
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public void disconnect() {
        try {
            logger.log("Disconnecting from " + url+";");
            connection.close();
        } catch (SQLException e) {
            logger.log("Disconnection failed;");
            e.printStackTrace();
        } finally {
            logger.destroy();
        }
    }

    @Override
    public boolean insert(Collection list, String table) {
        boolean success = true;
        for(Object e: list) {
            Event event = (Event)e;
            success &= insert(e, table);
        }
        return success;
    }
    
    @Override
    public boolean insert(Object obj, String table) {
        try {
            Event event = (Event)obj;
            String query = "insert into `"+schema+"`.`"+table+"` set `title`='"+event.getTitle().toString()+"', `date`='"+event.getDate().toString()+"', `time`='"+event.getTime().toString()+"', `address`='"+event.getAddress()+"', `price`='"+event.getPrice()+"', `image`='"+event.getImage()+"', `category`='"+event.getCategory()+"';";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.execute();
            stmt.close();
            logger.log("Successful insert;");
            return true;
        } catch(SQLException e) {
            logger.log("Insert failed;");
            //e.printStackTrace();
            return false;
        }
    }

    @Override
    public Event deleteById(int id, String table) {
        Event event = (Event) selectById(id, table);
        String query = "delete from `"+schema+"`.`"+table+"` where `id`="+id+";";
        try {
            PreparedStatement stmt = connection.prepareStatement("select * from `"+schema+"`.`"+table+"` where `id`="+id+";");
            ResultSet result = stmt.executeQuery();
            if(result.next()) {
                event.setAddress(result.getString("address"));
                //event.setCategory(result.getString("category"));
                event.setDate(result.getString("date"));
                event.setImage(result.getString("image"));
                event.setPrice(result.getString("price"));
                event.setTime(result.getString("time"));
                event.setTitle(result.getString("title"));
                stmt = connection.prepareStatement(query);
                stmt.execute();
                return event;
            } else {
                logger.log("Desirable item id="+id+" doesn't exist");
                System.out.println("Desirable item id="+id+" doesn't exist");
            }
        } catch (SQLException e) {
            System.out.println("Delete failed");
        }
        return new Event();
    }

    @Override
    public ArrayList selectAll(String table) {
        ArrayList<Event> events = new ArrayList<Event>();
        String query = "select * from `"+schema+"`.`"+table+"`;";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Event event = new Event();
                event.setAddress(result.getString("address"));
                //event.setCategory(result.getString("category"));
                event.setDate(result.getString("date"));
                event.setImage(result.getString("image"));
                event.setPrice(result.getString("price"));
                event.setTime(result.getString("time"));
                event.setTitle(result.getString("title"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public ArrayList selectByDate(String date, String table) {
        ArrayList<Event> events = new ArrayList();
        String query = "select * from `"+schema+"`.`"+table+"` where `date`="+date+";";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Event newEvent = new Event();
                newEvent.setAddress(result.getString("address"));
                //newEvent.setCategory(result.getString("category"));
                newEvent.setDate(result.getString("date"));
                newEvent.setImage(result.getString("image"));
                newEvent.setPrice(result.getString("price"));
                newEvent.setTime(result.getString("time"));
                newEvent.setTitle(result.getString("title"));
                events.add(newEvent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public ArrayList selectByCategory(String category, String table) {
        ArrayList<Event> events = new ArrayList();
        String query = "select * from `"+schema+"`.`"+table+"` where `category`="+category+";";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet result = stmt.executeQuery();
            while(result.next()) {
                Event newEvent = new Event();
                newEvent.setAddress(result.getString("address"));
                //newEvent.setCategory(result.getString("category"));
                newEvent.setDate(result.getString("date"));
                newEvent.setImage(result.getString("image"));
                newEvent.setPrice(result.getString("price"));
                newEvent.setTime(result.getString("time"));
                newEvent.setTitle(result.getString("title"));
                events.add(newEvent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}
