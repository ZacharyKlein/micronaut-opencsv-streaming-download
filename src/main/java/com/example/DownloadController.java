package com.example;

import com.opencsv.CSVWriter;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.types.files.StreamedFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller("/")
public class DownloadController {


    @Get
    @Produces("text/csv")
    StreamedFile download() throws Exception {


        List<String[]> data = new ArrayList<>();
        String[] stringA = {"a", "b", "c"};
        String[] stringB = {"1", "2", "3"};
        data.add(stringA);
        data.add(stringB);

        InputStream inputStream = csvWriterAll(data);

        return new StreamedFile(inputStream, MediaType.TEXT_PLAIN_TYPE).attach("export.csv");
    }


    public InputStream csvWriterAll(List<String[]> stringArray) throws Exception {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        OutputStreamWriter streamWriter = new OutputStreamWriter(stream);

        CSVWriter writer = new CSVWriter(streamWriter);
        writer.writeAll(stringArray);

        streamWriter.flush();

        return new ByteArrayInputStream(stream.toByteArray());
    }

}
