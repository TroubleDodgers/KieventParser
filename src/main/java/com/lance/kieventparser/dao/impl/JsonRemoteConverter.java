package com.lance.kieventparser.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lance.kieventparser.dao.JsonConverter;
import com.lance.kieventparser.entities.Category;
import com.lance.kieventparser.entities.Event;
import com.lance.kieventparser.entities.EventList;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by Corwin on 05.02.2018.
 */

public class JsonRemoteConverter implements JsonConverter {

    @Override
    public void toJson(List<Event> list, Category category) {
        ObjectMapper mapper = new ObjectMapper();
        try {
//            ObjectWriter objWriter = mapper.writerWithDefaultPrettyPrinter();
//            String jsonString = objWriter.writeValueAsString(list);
            File localFile = new File("C:\\KieventParserFiles\\" + category.toString() + ".json");
            OutputStreamWriter fileWriter =
                    new OutputStreamWriter(new FileOutputStream(localFile), StandardCharsets.UTF_8);
            fileWriter.write(mapper.writeValueAsString(list));
            fileWriter.close();
//            upload(localFile, category.toString());
            System.out.println(localFile.getAbsolutePath()+" complete");
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
        System.out.println(localFile.getAbsolutePath());
        FTPClient ftp = new FTPClient();
        ftp.connect(server, port);
        ftp.login(user, password);
        ftp.changeWorkingDirectory("/public_html");
        ftp.storeFile(fileName, is);
        is.close();
        ftp.logout();
        ftp.disconnect();
    }
}
