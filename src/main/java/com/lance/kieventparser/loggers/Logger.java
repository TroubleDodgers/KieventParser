package com.lance.kieventparser.loggers;

import java.io.*;
import java.util.Date;

/**
 * Created by Corwin on 19.05.2015.
 */

@Deprecated
public class Logger {
    private File logFile;
    private Writer out;

    public void destroy() {
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Logger() {
        String fileName = "D:\\MySQL log " + new java.sql.Date(new java.util.Date().getTime()).toString() + ".log";
        logFile = new File(fileName);
        try {
            out = new PrintWriter(logFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void log(String info) {
        try {
            out.append(new Date().toString() + ": " + info + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
