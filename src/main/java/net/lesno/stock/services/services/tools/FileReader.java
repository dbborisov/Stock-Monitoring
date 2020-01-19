package net.lesno.stock.services.services.tools;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FileReader {

    private String fileName;
    private BufferedReader reader;

    public FileReader(String fileName) {
        this.fileName = fileName;
    }

    public FileReader() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Map<String, String> readFileToMap(String fileName) {
        Map<String,String> stockMap = new TreeMap<>();
        File file = new File(fileName);

        try {
            reader = new BufferedReader( new java.io.FileReader(file.getAbsoluteFile()));

            String st;
            while ((st = reader.readLine()) != null) {
                String[] str = st.split("\t");
                stockMap.put(str[0], Arrays.stream(str).filter(e->!e.equals(str[0])).collect(Collectors.joining(" ")));
            }

//                System.out.println(stockMap);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockMap;
    }
}
