package com.transport.transportation.csv;

import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.dto.SendTransportDocument;
import com.transport.transportation.email.SendReports;
import com.transport.transportation.entity.TransRequestCustom;
import com.transport.transportation.entity.TransportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

@Service
public class GenerateCSV {

    String dirName = System.getProperty("java.io.tmpdir");

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private SendReports email;

    double total = 0;

    public void createCSV(SendTransportDocument pdfParam, Iterable<TransportRequest> allrequests) {
        String filename = getDateTime();
        filename = filename + ".csv";
        String finalFilename = dirName + filename;
        try {
            FileWriter writer = new FileWriter(finalFilename);
            CSVUtils.writeLine(writer, Arrays.asList("ID", "SOURCE", "DESTINATION", "REQUEST_DATE", "FARE", "STATUS"));

            total = 0;

            allrequests.forEach(req -> {

                java.util.List<String> list = new ArrayList();

                TransRequestCustom dest = commonUtil.copyRequest(req);
                list.add(String.valueOf(dest.getRequestid()));
                list.add(dest.getSource());
                list.add(dest.getDestination());
                list.add(dest.getDateTime().toString());
                total = total + dest.getCost();
                list.add(String.valueOf(dest.getCost()));
                list.add(dest.getRequestStatus());

                try {
                    CSVUtils.writeLine(writer, list);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            CSVUtils.writeLine(writer, Arrays.asList("", "", "", "", String.valueOf(total), ""));

            writer.flush();
            writer.close();

            email.sendMail(finalFilename, filename, pdfParam.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
        return LocalDateTime.now().format(formatter);
    }
}
