package com.transport.transportation.pdf;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.transport.transportation.common.CommonUtil;
import com.transport.transportation.dto.SendTransportDocument;
import com.transport.transportation.email.SendReports;
import com.transport.transportation.entity.TransRequestCustom;
import com.transport.transportation.entity.TransportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class GeneratePDF {

    @Autowired
    private SendReports email;

    String dirName = System.getProperty("java.io.tmpdir");
    //private static String FILE = dirName;
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    @Autowired
    private CommonUtil commonUtil;

    public static void main(String[] args) {
        GeneratePDF pdf = new GeneratePDF();
        pdf.sendPDF(null, null);
    }

    public void sendPDF(SendTransportDocument pdfParam, Iterable<TransportRequest> allrequests) {
        try {
            Document document = new Document();

            String filename = getDateTime();
            filename = filename + ".pdf";

            System.out.println("generating pdf file :" + filename);

            PdfWriter.getInstance(document, new FileOutputStream(dirName + "/" + filename));
            document.open();

            addTitlePage(document, pdfParam, allrequests);
            //addContent(document);
            document.close();

            System.out.println("generated file :" + filename);

            String finalFilename = filename;

            email.sendMail("",finalFilename, pdfParam.getEmail());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTitlePage(Document document, SendTransportDocument pdfParam, Iterable<TransportRequest> allrequests)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        Paragraph header = new Paragraph("Transport Requests Report", catFont);
        header.setAlignment(Element.ALIGN_CENTER);
        preface.add(header);

        addEmptyLine(preface, 1);

        preface.add(new Paragraph(
                "Report generated by: " + pdfParam.getEmail() + ", From: " + pdfParam.getFromDate()
                        + ", To: " + pdfParam.getToDate(),
                smallBold));
        addEmptyLine(preface, 1);

        Paragraph status = new Paragraph("A - Approved, R - Rejected, P - Pending", smallBold);
        status.setAlignment(Element.ALIGN_CENTER);
        preface.add(status);
        addEmptyLine(preface, 1);

        createTable(preface, allrequests);

        document.add(preface);
        // Start a new page
        document.newPage();
    }

    private void createTable(Paragraph subCatPart, Iterable<TransportRequest> allrequests)
            throws BadElementException {
        PdfPTable table = new PdfPTable(5);
        table.setTotalWidth(PageSize.A4.getWidth() - 5);
        try {
            table.setWidths(new float[]{7f, 30f, 30f, 23f, 10f});
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        table.setLockedWidth(true);
        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);

        PdfPCell c1 = new PdfPCell(new Phrase("ID"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("SOURCE"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("DESTINATION"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("REQUEST DATE"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("STATUS"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);

        allrequests.forEach(req -> {
            TransRequestCustom dest = commonUtil.copyRequest(req);
            table.addCell(String.valueOf(dest.getRequestid()));
            table.addCell(dest.getSource());
            table.addCell(dest.getDestination());
            table.addCell(dest.getDateTime().toString());
            table.addCell(dest.getRequestStatus());
        });

        subCatPart.add(table);
    }

    private void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
        return LocalDateTime.now().format(formatter);
    }
}