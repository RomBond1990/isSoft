package com.rbondarovich;

import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderDecorator {

    public static <T> List<T> getListObjects(String source, Class <T> typeClass) {
        List<T> objects = new ArrayList<>();

        try(FileReader reader = new FileReader(source)){
            objects = new CsvToBeanBuilder(reader)
                    .withType(typeClass)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
