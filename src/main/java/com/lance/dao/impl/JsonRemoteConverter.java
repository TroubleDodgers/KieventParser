package com.lance.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lance.dao.JsonConverter;
import com.lance.entities.Category;
import com.lance.entities.EventList;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by Corwin on 05.02.2018.
 */

public class JsonRemoteConverter implements JsonConverter {
    private final static String BASEFOLDER =
            "C:\\WebServers\\home\\192.168.1.82\\www\\";

    public EventList toJavaObject(Category category) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(BASEFOLDER.concat(category.toString()).concat(".json")), new TypeReference<EventList>() {
        });
    }

    public void toJson(EventList list, Category category) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            ObjectWriter objWriter = mapper.writerWithDefaultPrettyPrinter();
            String jsonString = objWriter.writeValueAsString(list);
            File localFile = File.createTempFile(category.toString(), "json");
            localFile.setWritable(true);
//            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(localFile));
            OutputStreamWriter fileWriter =
                    new OutputStreamWriter(new FileOutputStream(localFile), StandardCharsets.UTF_8);
            fileWriter.write(jsonString);
            fileWriter.close();
            upload(localFile, category.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void upload(File localFile, String category) throws IOException {
        String server = "files.000webhost.com";
        int port = 21;
        String user = "rollallover";
        String password = "Beseda_93";
        String fileName = category.concat(".json");
        InputStream is = new FileInputStream(localFile);
        FTPClient ftp = new FTPClient();
        ftp.connect(server, port);
        ftp.login(user, password);
        ftp.changeWorkingDirectory("/public_html");
        ftp.storeFile(fileName, is);
        is.close();
        ftp.logout();
    }
}
