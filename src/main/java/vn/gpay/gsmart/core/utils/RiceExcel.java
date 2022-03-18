package vn.gpay.gsmart.core.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

public final class RiceExcel {

    public static File createGuestRice(String filePath, List<?> listOfData) throws IOException, IllegalAccessException {
        double unitPriceValue = 20000;

        File excelFile = new File(filePath);

        //// Setup
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Cơm Khách");
        var formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        var colorMap = workbook.getStylesSource().getIndexedColors();

        // Setup margin for print
        var printSetup = sheet.getPrintSetup();
        printSetup.setTopMargin(0.43);
        printSetup.setBottomMargin(0.75);
        printSetup.setLeftMargin(0.59);
        printSetup.setRightMargin(0.2);
        printSetup.setHeaderMargin(0.3);
        printSetup.setFooterMargin(0.3);

        //// Setup width of column
        sheet.setColumnWidth(0, 1425);
        sheet.setColumnWidth(1, 4640);
        sheet.setColumnWidth(2, 3160);
        sheet.setColumnWidth(3, 2925);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 8200);

        //// Title part
        int rowNum = 0;
        Row firstTitleRow = sheet.createRow(rowNum);
        CellStyle firstTitleStyle = workbook.createCellStyle();
        XSSFFont firstTitleFont = workbook.createFont();
        firstTitleFont.setFontName("Times New Roman");
        firstTitleFont.setFontHeightInPoints((short) 12);
        firstTitleFont.setItalic(true);
        firstTitleFont.setBold(true);
        firstTitleStyle.setFont(firstTitleFont);
        firstTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell firstTitleCell = firstTitleRow.createCell(0);
        firstTitleCell.setCellValue("CÔNG TY TNHH MTV DHA BẮC NINH");
        firstTitleCell.setCellStyle(firstTitleStyle);

        rowNum++;
        Row secTitleRow = sheet.createRow(rowNum);
        CellStyle secTitleStyle = workbook.createCellStyle();
        XSSFFont secTitleFont = workbook.createFont();
        secTitleFont.setFontName("Times New Roman");
        secTitleFont.setFontHeightInPoints((short) 16);
        secTitleFont.setBold(true);
        secTitleStyle.setFont(secTitleFont);
        secTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        secTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell secTitleCell = secTitleRow.createCell(0);
        secTitleCell.setCellValue("BẢNG TỔNG HỢP CƠM KHÁCH");
        secTitleCell.setCellStyle(secTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));

        rowNum++;
        Row thirdTitleRow = sheet.createRow(rowNum);
        CellStyle thirdTitleStyle = workbook.createCellStyle();
        DataFormat thirdTitleFormat = workbook.createDataFormat();
        XSSFFont thirdTitleFont = workbook.createFont();
        thirdTitleFont.setFontName("Times New Roman");
        thirdTitleFont.setFontHeightInPoints((short) 16);
        thirdTitleFont.setItalic(true);
        thirdTitleFont.setBold(true);
        thirdTitleStyle.setDataFormat(thirdTitleFormat.getFormat("\"Tháng\" mm \"năm\" yyyy"));
        thirdTitleStyle.setFont(thirdTitleFont);
        thirdTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        thirdTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell thirdTitleCell = thirdTitleRow.createCell(0);
        thirdTitleCell.setCellValue("Tháng " + " năm ");
        thirdTitleCell.setCellStyle(thirdTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 5));

        rowNum++;
        Row forthTitleRow = sheet.createRow(rowNum);
        CellStyle forthTitleStyle = workbook.createCellStyle();
        XSSFFont forthTitleFont = workbook.createFont();
        forthTitleFont.setFontName("Times New Roman");
        forthTitleFont.setFontHeightInPoints((short) 10);
        forthTitleFont.setItalic(true);
        forthTitleStyle.setFont(forthTitleFont);
        forthTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        forthTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell forthTitleCell = forthTitleRow.createCell(4);
        forthTitleCell.setCellValue("ĐVT: Đồng");
        forthTitleCell.setCellStyle(forthTitleStyle);

        //// Header table
        rowNum++;
        Row headerTableRow = sheet.createRow(rowNum);

        CellStyle headerTableStyle = workbook.createCellStyle();
        XSSFFont headerTableFont = workbook.createFont();
        headerTableFont.setFontName("Times New Roman");
        headerTableFont.setFontHeightInPoints((short) 12);
        headerTableFont.setBold(true);
        headerTableStyle.setFont(headerTableFont);
        headerTableStyle.setAlignment(HorizontalAlignment.CENTER);
        headerTableStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerTableStyle.setWrapText(true);
        headerTableStyle.setBorderTop(BorderStyle.THIN);
        headerTableStyle.setBorderBottom(BorderStyle.THIN);
        headerTableStyle.setBorderLeft(BorderStyle.THIN);
        headerTableStyle.setBorderRight(BorderStyle.THIN);

        Cell cardinalNumber = headerTableRow.createCell(0);
        cardinalNumber.setCellValue("STT");
        cardinalNumber.setCellStyle(headerTableStyle);
        Cell date = headerTableRow.createCell(1);
        date.setCellValue("NGÀY TRONG THÁNG");
        date.setCellStyle(headerTableStyle);
        Cell numberOfMeals = headerTableRow.createCell(2);
        numberOfMeals.setCellValue("SỐ SUẤT ĂN");
        numberOfMeals.setCellStyle(headerTableStyle);
        Cell unitPrice = headerTableRow.createCell(3);
        unitPrice.setCellValue("ĐƠN GIÁ");
        unitPrice.setCellStyle(headerTableStyle);
        Cell bill = headerTableRow.createCell(4);
        bill.setCellValue("THÀNH TIỀN");
        bill.setCellStyle(headerTableStyle);
        Cell note = headerTableRow.createCell(5);
        note.setCellValue("GHI CHÚ");
        note.setCellStyle(headerTableStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 3, 3));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 4, 4));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 5, 5));

        rowNum++;
        Row nullHeaderRow = sheet.createRow(rowNum);
        for (int i = 0; i < 6; i++) nullHeaderRow.createCell(i).setCellStyle(headerTableStyle);

        //// Table data
        CellStyle normalDayStyle = workbook.createCellStyle();
        DataFormat normalDayFormat = workbook.createDataFormat();
        XSSFFont normalDayFont = workbook.createFont();
        normalDayFont.setFontName("Times New Roman");
        normalDayFont.setFontHeightInPoints((short) 14);
        normalDayStyle.setDataFormat(normalDayFormat.getFormat("#,###"));
        normalDayStyle.setFont(normalDayFont);
        normalDayStyle.setAlignment(HorizontalAlignment.CENTER);
        normalDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        normalDayStyle.setBorderTop(BorderStyle.THIN);
        normalDayStyle.setBorderBottom(BorderStyle.THIN);
        normalDayStyle.setBorderLeft(BorderStyle.THIN);
        normalDayStyle.setBorderRight(BorderStyle.THIN);

        CellStyle sundayStyle = workbook.createCellStyle();
        DataFormat sundayFormat = workbook.createDataFormat();
        XSSFFont sundayFont = workbook.createFont();
        sundayFont.setColor(new XSSFColor(Color.BLUE, colorMap));
        sundayFont.setFontName("Times New Roman");
        sundayFont.setFontHeightInPoints((short) 14);
        sundayStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        sundayStyle.setFont(sundayFont);
        sundayStyle.setAlignment(HorizontalAlignment.CENTER);
        sundayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        sundayStyle.setFillBackgroundColor(IndexedColors.CORAL.index);
        sundayStyle.setFillForegroundColor(IndexedColors.CORAL.index);
        sundayStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        sundayStyle.setBorderTop(BorderStyle.THIN);
        sundayStyle.setBorderBottom(BorderStyle.THIN);
        sundayStyle.setBorderLeft(BorderStyle.THIN);
        sundayStyle.setBorderRight(BorderStyle.THIN);

        int firstRowDataTable = rowNum + 1;
        for (int i = 0; i < listOfData.size(); i++) {
            rowNum++;
            Row newRow = sheet.createRow(rowNum);

            List<String> data = getObjectData(listOfData.get(i));

            Cell cardinalNumberData = newRow.createCell(0);
            Cell dateData = newRow.createCell(1);
            Cell numberOfMealsData = newRow.createCell(2);
            Cell unitPriceData = newRow.createCell(3);
            Cell billData = newRow.createCell(4);
            Cell noteData = newRow.createCell(5);

            cardinalNumberData.setCellValue(i + 1);
            dateData.setCellValue(data.get(0));
            numberOfMealsData.setCellValue(Double.parseDouble(data.get(1)));
            unitPriceData.setCellValue(unitPriceValue);
            billData.setCellFormula(String.format("C%d*D%d", rowNum + 1, rowNum + 1));
            formulaEvaluator.evaluateFormulaCell(billData);

            if (isSunday(data.get(0))) {
                cardinalNumberData.setCellStyle(sundayStyle);
                dateData.setCellStyle(sundayStyle);
                numberOfMealsData.setCellStyle(sundayStyle);
                unitPriceData.setCellStyle(sundayStyle);
                billData.setCellStyle(sundayStyle);
                noteData.setCellStyle(sundayStyle);
            } else {
                cardinalNumberData.setCellStyle(normalDayStyle);
                dateData.setCellStyle(normalDayStyle);
                numberOfMealsData.setCellStyle(normalDayStyle);
                unitPriceData.setCellStyle(normalDayStyle);
                billData.setCellStyle(normalDayStyle);
                noteData.setCellStyle(normalDayStyle);
            }
        }
        int lastRowDataTable = rowNum;

        //// Footer table
        rowNum++;
        Row footerRow = sheet.createRow(rowNum);

        CellStyle footerStyle = workbook.createCellStyle();
        XSSFFont footerFont = workbook.createFont();
        footerFont.setFontName("Times New Roman");
        footerFont.setFontHeightInPoints((short) 14);
        footerFont.setBold(true);
        footerStyle.setFont(footerFont);
        footerStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        footerStyle.setAlignment(HorizontalAlignment.CENTER);
        footerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        footerStyle.setBorderTop(BorderStyle.THIN);
        footerStyle.setBorderBottom(BorderStyle.THIN);
        footerStyle.setBorderLeft(BorderStyle.THIN);
        footerStyle.setBorderRight(BorderStyle.THIN);

        List<Cell> nullCell = new ArrayList<>(){{
            add(footerRow.createCell(0));
            add(footerRow.createCell(5));
        }};

        nullCell.forEach(cell -> cell.setCellStyle(footerStyle));

        Cell sumText = footerRow.createCell(1);
        sumText.setCellValue("Tổng");
        sumText.setCellStyle(footerStyle);

        Cell sumNOM = footerRow.createCell(2);
        sumNOM.setCellFormula(String.format("SUM(C%d:C%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumNOM);
        sumNOM.setCellStyle(footerStyle);

        Cell unitPriceFooter = footerRow.createCell(3);
        unitPriceFooter.setCellValue(unitPriceValue);
        unitPriceFooter.setCellStyle(footerStyle);

        Cell sumBill = footerRow.createCell(4);
        sumBill.setCellFormula(String.format("SUM(E%d:E%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumBill);
        sumBill.setCellStyle(footerStyle);

        //// Signature
        rowNum++;
        Row billToTextRow = sheet.createRow(rowNum);

        CellStyle billToTextStyle = workbook.createCellStyle();
        XSSFFont billToTextFont = workbook.createFont();
        billToTextFont.setFontName("Times New Roman");
        billToTextFont.setFontHeightInPoints((short) 14);
        billToTextFont.setBold(true);
        billToTextStyle.setFont(billToTextFont);
        billToTextStyle.setAlignment(HorizontalAlignment.LEFT);
        billToTextStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell billToTextCell = billToTextRow.createCell(0);
        billToTextCell.setCellValue("Bằng chữ: ");
        billToTextCell.setCellStyle(billToTextStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));

        rowNum += 2;
        Row signatureDayRow = sheet.createRow(rowNum);

        CellStyle signatureDayStyle = workbook.createCellStyle();
        XSSFFont signatureDayFont = workbook.createFont();
        signatureDayFont.setFontName("Times New Roman");
        signatureDayFont.setFontHeightInPoints((short) 12);
        signatureDayFont.setItalic(true);
        signatureDayStyle.setFont(signatureDayFont);
        signatureDayStyle.setAlignment(HorizontalAlignment.CENTER);
        signatureDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureDayCell = signatureDayRow.createCell(3);
        signatureDayCell.setCellValue("Bắc Ninh, ngày  tháng  năm");
        signatureDayCell.setCellStyle(signatureDayStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 5));

        rowNum++;
        Row signatureRow = sheet.createRow(rowNum);

        CellStyle signatureStyle = workbook.createCellStyle();
        XSSFFont signatureFont = workbook.createFont();
        signatureFont.setFontName("Times New Roman");
        signatureFont.setFontHeightInPoints((short) 14);
        signatureFont.setBold(true);
        signatureStyle.setFont(signatureFont);
        signatureStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureCell = signatureRow.createCell(0);
        signatureCell.setCellValue("   Giám đốc                      Kế toán trưởng                    Quản đốc                    Lập biểu            ");
        signatureCell.setCellStyle(signatureStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 5));

        //// Write listOfData to workbook
        var fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        return excelFile;
    }

    public static File createTotalRice(String filePath, List<?> listOfData) throws IllegalAccessException, IOException {
        double unitPriceValue = 20000;
        File excelFile = new File(filePath);

        //// Setup
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Tổng hợp cơm ca");
        var formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        var colorMap = workbook.getStylesSource().getIndexedColors();

        var printSetup = sheet.getPrintSetup();
        printSetup.setTopMargin(0.43);
        printSetup.setBottomMargin(0.75);
        printSetup.setLeftMargin(0.59);
        printSetup.setRightMargin(0.2);
        printSetup.setHeaderMargin(0.3);
        printSetup.setFooterMargin(0.3);

        //// Setup width of column
        sheet.setColumnWidth(0, 1425);
        sheet.setColumnWidth(1, 4640);
        sheet.setColumnWidth(2, 3160);
        sheet.setColumnWidth(3, 2925);
        sheet.setColumnWidth(4, 4000);

        //// Title part
        int rowNum = 0;
        Row firstTitleRow = sheet.createRow(rowNum);
        CellStyle firstTitleStyle = workbook.createCellStyle();
        XSSFFont firstTitleFont = workbook.createFont();
        firstTitleFont.setFontName("Times New Roman");
        firstTitleFont.setFontHeightInPoints((short) 12);
        firstTitleFont.setItalic(true);
        firstTitleFont.setBold(true);
        firstTitleStyle.setFont(firstTitleFont);
        firstTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell firstTitleCell = firstTitleRow.createCell(0);
        firstTitleCell.setCellValue("CÔNG TY TNHH MTV DHA BẮC NINH");
        firstTitleCell.setCellStyle(firstTitleStyle);

        rowNum++;
        Row secTitleRow = sheet.createRow(rowNum);
        CellStyle secTitleStyle = workbook.createCellStyle();
        XSSFFont secTitleFont = workbook.createFont();
        secTitleFont.setFontName("Times New Roman");
        secTitleFont.setFontHeightInPoints((short) 16);
        secTitleFont.setBold(true);
        secTitleStyle.setFont(secTitleFont);
        secTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        secTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell secTitleCell = secTitleRow.createCell(0);
        secTitleCell.setCellValue("BẢNG TỔNG HỢP CƠM CA");
        secTitleCell.setCellStyle(secTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));

        rowNum++;
        Row thirdTitleRow = sheet.createRow(rowNum);
        CellStyle thirdTitleStyle = workbook.createCellStyle();
        DataFormat thirdTitleFormat = workbook.createDataFormat();
        XSSFFont thirdTitleFont = workbook.createFont();
        thirdTitleFont.setFontName("Times New Roman");
        thirdTitleFont.setFontHeightInPoints((short) 16);
        thirdTitleFont.setItalic(true);
        thirdTitleFont.setBold(true);
        thirdTitleStyle.setDataFormat(thirdTitleFormat.getFormat("\"Tháng\" mm \"năm\" yyyy"));
        thirdTitleStyle.setFont(thirdTitleFont);
        thirdTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        thirdTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell thirdTitleCell = thirdTitleRow.createCell(0);
        thirdTitleCell.setCellValue("Tháng " + " năm ");
        thirdTitleCell.setCellStyle(thirdTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));

        rowNum++;
        Row forthTitleRow = sheet.createRow(rowNum);
        CellStyle forthTitleStyle = workbook.createCellStyle();
        XSSFFont forthTitleFont = workbook.createFont();
        forthTitleFont.setFontName("Times New Roman");
        forthTitleFont.setFontHeightInPoints((short) 10);
        forthTitleFont.setItalic(true);
        forthTitleStyle.setFont(forthTitleFont);
        forthTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        forthTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell forthTitleCell = forthTitleRow.createCell(4);
        forthTitleCell.setCellValue("ĐVT: Đồng");
        forthTitleCell.setCellStyle(forthTitleStyle);

        //// Header table
        rowNum++;
        Row headerTableRow = sheet.createRow(rowNum);

        CellStyle headerTableStyle = workbook.createCellStyle();
        XSSFFont headerTableFont = workbook.createFont();
        headerTableFont.setFontName("Times New Roman");
        headerTableFont.setFontHeightInPoints((short) 12);
        headerTableFont.setBold(true);
        headerTableStyle.setFont(headerTableFont);
        headerTableStyle.setAlignment(HorizontalAlignment.CENTER);
        headerTableStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerTableStyle.setWrapText(true);
        headerTableStyle.setBorderTop(BorderStyle.THIN);
        headerTableStyle.setBorderBottom(BorderStyle.THIN);
        headerTableStyle.setBorderLeft(BorderStyle.THIN);
        headerTableStyle.setBorderRight(BorderStyle.THIN);

        Cell cardinalNumber = headerTableRow.createCell(0);
        cardinalNumber.setCellValue("STT");
        cardinalNumber.setCellStyle(headerTableStyle);
        Cell date = headerTableRow.createCell(1);
        date.setCellValue("NGÀY TRONG THÁNG");
        date.setCellStyle(headerTableStyle);
        Cell numberOfMeals = headerTableRow.createCell(2);
        numberOfMeals.setCellValue("SỐ SUẤT ĂN");
        numberOfMeals.setCellStyle(headerTableStyle);
        Cell unitPrice = headerTableRow.createCell(3);
        unitPrice.setCellValue("ĐƠN GIÁ");
        unitPrice.setCellStyle(headerTableStyle);
        Cell bill = headerTableRow.createCell(4);
        bill.setCellValue("THÀNH TIỀN");
        bill.setCellStyle(headerTableStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 3, 3));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 4, 4));

        rowNum++;
        Row nullHeaderRow = sheet.createRow(rowNum);
        for (int i = 0; i < 5; i++) nullHeaderRow.createCell(i).setCellStyle(headerTableStyle);

        //// Table data
        CellStyle normalDayStyle = workbook.createCellStyle();
        DataFormat normalDayFormat = workbook.createDataFormat();
        XSSFFont normalDayFont = workbook.createFont();
        normalDayFont.setFontName("Times New Roman");
        normalDayFont.setFontHeightInPoints((short) 14);
        normalDayStyle.setDataFormat(normalDayFormat.getFormat("#,###"));
        normalDayStyle.setFont(normalDayFont);
        normalDayStyle.setAlignment(HorizontalAlignment.CENTER);
        normalDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        normalDayStyle.setBorderTop(BorderStyle.THIN);
        normalDayStyle.setBorderBottom(BorderStyle.THIN);
        normalDayStyle.setBorderLeft(BorderStyle.THIN);
        normalDayStyle.setBorderRight(BorderStyle.THIN);

        CellStyle sundayStyle = workbook.createCellStyle();
        DataFormat sundayFormat = workbook.createDataFormat();
        XSSFFont sundayFont = workbook.createFont();
        sundayFont.setColor(new XSSFColor(Color.BLUE, colorMap));
        sundayFont.setFontName("Times New Roman");
        sundayFont.setFontHeightInPoints((short) 14);
        sundayStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        sundayStyle.setFont(sundayFont);
        sundayStyle.setAlignment(HorizontalAlignment.CENTER);
        sundayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        sundayStyle.setFillBackgroundColor(IndexedColors.CORAL.index);
        sundayStyle.setFillForegroundColor(IndexedColors.CORAL.index);
        sundayStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        sundayStyle.setBorderTop(BorderStyle.THIN);
        sundayStyle.setBorderBottom(BorderStyle.THIN);
        sundayStyle.setBorderLeft(BorderStyle.THIN);
        sundayStyle.setBorderRight(BorderStyle.THIN);

        int firstRowDataTable = rowNum + 1;
        for (int i = 0; i < listOfData.size(); i++) {
            rowNum++;
            Row newRow = sheet.createRow(rowNum);

            List<String> data = getObjectData(listOfData.get(i));

            Cell cardinalNumberData = newRow.createCell(0);
            Cell dateData = newRow.createCell(1);
            Cell numberOfMealsData = newRow.createCell(2);
            Cell unitPriceData = newRow.createCell(3);
            Cell billData = newRow.createCell(4);

            cardinalNumberData.setCellValue(i + 1);
            dateData.setCellValue(data.get(0));
            numberOfMealsData.setCellValue(Double.parseDouble(data.get(1)));
            unitPriceData.setCellValue(unitPriceValue);
            billData.setCellFormula(String.format("C%d*D%d", rowNum + 1, rowNum + 1));
            formulaEvaluator.evaluateFormulaCell(billData);

            if (isSunday(data.get(0))) {
                cardinalNumberData.setCellStyle(sundayStyle);
                dateData.setCellStyle(sundayStyle);
                numberOfMealsData.setCellStyle(sundayStyle);
                unitPriceData.setCellStyle(sundayStyle);
                billData.setCellStyle(sundayStyle);
            } else {
                cardinalNumberData.setCellStyle(normalDayStyle);
                dateData.setCellStyle(normalDayStyle);
                numberOfMealsData.setCellStyle(normalDayStyle);
                unitPriceData.setCellStyle(normalDayStyle);
                billData.setCellStyle(normalDayStyle);
            }
        }
        int lastRowDataTable = rowNum;

        //// Footer table
        rowNum++;
        Row footerRow = sheet.createRow(rowNum);

        CellStyle footerStyle = workbook.createCellStyle();
        XSSFFont footerFont = workbook.createFont();
        footerFont.setFontName("Times New Roman");
        footerFont.setFontHeightInPoints((short) 14);
        footerFont.setBold(true);
        footerStyle.setFont(footerFont);
        footerStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        footerStyle.setAlignment(HorizontalAlignment.CENTER);
        footerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        footerStyle.setBorderTop(BorderStyle.THIN);
        footerStyle.setBorderBottom(BorderStyle.THIN);
        footerStyle.setBorderLeft(BorderStyle.THIN);
        footerStyle.setBorderRight(BorderStyle.THIN);

        List<Cell> nullCell = new ArrayList<>(){{
            add(footerRow.createCell(0));
        }};

        nullCell.forEach(cell -> cell.setCellStyle(footerStyle));

        Cell sumText = footerRow.createCell(1);
        sumText.setCellValue("Tổng");
        sumText.setCellStyle(footerStyle);

        Cell sumNOM = footerRow.createCell(2);
        sumNOM.setCellFormula(String.format("SUM(C%d:C%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumNOM);
        sumNOM.setCellStyle(footerStyle);

        Cell unitPriceFooter = footerRow.createCell(3);
        unitPriceFooter.setCellValue(unitPriceValue);
        unitPriceFooter.setCellStyle(footerStyle);

        Cell sumBill = footerRow.createCell(4);
        sumBill.setCellFormula(String.format("SUM(E%d:E%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumBill);
        sumBill.setCellStyle(footerStyle);

        //// Signature
        rowNum++;
        Row billToTextRow = sheet.createRow(rowNum);

        CellStyle billToTextStyle = workbook.createCellStyle();
        XSSFFont billToTextFont = workbook.createFont();
        billToTextFont.setFontName("Times New Roman");
        billToTextFont.setFontHeightInPoints((short) 14);
        billToTextFont.setBold(true);
        billToTextStyle.setFont(billToTextFont);
        billToTextStyle.setAlignment(HorizontalAlignment.LEFT);
        billToTextStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell billToTextCell = billToTextRow.createCell(0);
        billToTextCell.setCellValue("Bằng chữ: ");
        billToTextCell.setCellStyle(billToTextStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 4));

        rowNum += 2;
        Row signatureDayRow = sheet.createRow(rowNum);

        CellStyle signatureDayStyle = workbook.createCellStyle();
        XSSFFont signatureDayFont = workbook.createFont();
        signatureDayFont.setFontName("Times New Roman");
        signatureDayFont.setFontHeightInPoints((short) 12);
        signatureDayFont.setItalic(true);
        signatureDayStyle.setFont(signatureDayFont);
        signatureDayStyle.setAlignment(HorizontalAlignment.CENTER);
        signatureDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureDayCell = signatureDayRow.createCell(2);
        signatureDayCell.setCellValue("Bắc Ninh, ngày  tháng  năm");
        signatureDayCell.setCellStyle(signatureDayStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 2, 4));

        rowNum++;
        Row signatureRow = sheet.createRow(rowNum);

        CellStyle signatureStyle = workbook.createCellStyle();
        XSSFFont signatureFont = workbook.createFont();
        signatureFont.setFontName("Times New Roman");
        signatureFont.setFontHeightInPoints((short) 14);
        signatureFont.setBold(true);
        signatureStyle.setFont(signatureFont);
        signatureStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureCell = signatureRow.createCell(0);
        signatureCell.setCellValue("   Giám đốc                             Kế toán trưởng                               Lập biểu            ");
        signatureCell.setCellStyle(signatureStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 4));

        //// Write listOfData to workbook
        var fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        return excelFile;
    }

    public static File createTotalExtraFile(String filePath, List<?> listOfData) throws IOException, IllegalAccessException {
        double unitPriceValue = 20000;
        File excelFile = new File(filePath);

        //// Setup
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Tổng hợp cơm tăng ca");
        var formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
        var colorMap = workbook.getStylesSource().getIndexedColors();

        var printSetup = sheet.getPrintSetup();
        printSetup.setTopMargin(0.43);
        printSetup.setBottomMargin(0.75);
        printSetup.setLeftMargin(0.59);
        printSetup.setRightMargin(0.2);
        printSetup.setHeaderMargin(0.3);
        printSetup.setFooterMargin(0.3);

        //// Setup width of column
        sheet.setColumnWidth(0, 1425);
        sheet.setColumnWidth(1, 4640);
        sheet.setColumnWidth(2, 3160);
        sheet.setColumnWidth(3, 2925);
        sheet.setColumnWidth(4, 4000);

        //// Title part
        int rowNum = 0;
        Row firstTitleRow = sheet.createRow(rowNum);
        CellStyle firstTitleStyle = workbook.createCellStyle();
        XSSFFont firstTitleFont = workbook.createFont();
        firstTitleFont.setFontName("Times New Roman");
        firstTitleFont.setFontHeightInPoints((short) 12);
        firstTitleFont.setItalic(true);
        firstTitleFont.setBold(true);
        firstTitleStyle.setFont(firstTitleFont);
        firstTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell firstTitleCell = firstTitleRow.createCell(0);
        firstTitleCell.setCellValue("CÔNG TY TNHH MTV DHA BẮC NINH");
        firstTitleCell.setCellStyle(firstTitleStyle);

        rowNum++;
        Row secTitleRow = sheet.createRow(rowNum);
        CellStyle secTitleStyle = workbook.createCellStyle();
        XSSFFont secTitleFont = workbook.createFont();
        secTitleFont.setFontName("Times New Roman");
        secTitleFont.setFontHeightInPoints((short) 16);
        secTitleFont.setBold(true);
        secTitleStyle.setFont(secTitleFont);
        secTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        secTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell secTitleCell = secTitleRow.createCell(0);
        secTitleCell.setCellValue("BẢNG TỔNG HỢP CƠM TĂNG CA");
        secTitleCell.setCellStyle(secTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 4));

        rowNum++;
        Row thirdTitleRow = sheet.createRow(rowNum);
        CellStyle thirdTitleStyle = workbook.createCellStyle();
        DataFormat thirdTitleFormat = workbook.createDataFormat();
        XSSFFont thirdTitleFont = workbook.createFont();
        thirdTitleFont.setFontName("Times New Roman");
        thirdTitleFont.setFontHeightInPoints((short) 16);
        thirdTitleFont.setItalic(true);
        thirdTitleFont.setBold(true);
        thirdTitleStyle.setDataFormat(thirdTitleFormat.getFormat("\"Tháng\" mm \"năm\" yyyy"));
        thirdTitleStyle.setFont(thirdTitleFont);
        thirdTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        thirdTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell thirdTitleCell = thirdTitleRow.createCell(0);
        thirdTitleCell.setCellValue("Tháng " + " năm ");
        thirdTitleCell.setCellStyle(thirdTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));

        rowNum++;
        Row forthTitleRow = sheet.createRow(rowNum);
        CellStyle forthTitleStyle = workbook.createCellStyle();
        XSSFFont forthTitleFont = workbook.createFont();
        forthTitleFont.setFontName("Times New Roman");
        forthTitleFont.setFontHeightInPoints((short) 10);
        forthTitleFont.setItalic(true);
        forthTitleStyle.setFont(forthTitleFont);
        forthTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        forthTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell forthTitleCell = forthTitleRow.createCell(4);
        forthTitleCell.setCellValue("ĐVT: Đồng");
        forthTitleCell.setCellStyle(forthTitleStyle);

        //// Header table
        rowNum++;
        Row headerTableRow = sheet.createRow(rowNum);

        CellStyle headerTableStyle = workbook.createCellStyle();
        XSSFFont headerTableFont = workbook.createFont();
        headerTableFont.setFontName("Times New Roman");
        headerTableFont.setFontHeightInPoints((short) 12);
        headerTableFont.setBold(true);
        headerTableStyle.setFont(headerTableFont);
        headerTableStyle.setAlignment(HorizontalAlignment.CENTER);
        headerTableStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerTableStyle.setWrapText(true);
        headerTableStyle.setBorderTop(BorderStyle.THIN);
        headerTableStyle.setBorderBottom(BorderStyle.THIN);
        headerTableStyle.setBorderLeft(BorderStyle.THIN);
        headerTableStyle.setBorderRight(BorderStyle.THIN);

        Cell cardinalNumber = headerTableRow.createCell(0);
        cardinalNumber.setCellValue("STT");
        cardinalNumber.setCellStyle(headerTableStyle);
        Cell date = headerTableRow.createCell(1);
        date.setCellValue("NGÀY TRONG THÁNG");
        date.setCellStyle(headerTableStyle);
        Cell numberOfMeals = headerTableRow.createCell(2);
        numberOfMeals.setCellValue("SỐ SUẤT ĂN");
        numberOfMeals.setCellStyle(headerTableStyle);
        Cell unitPrice = headerTableRow.createCell(3);
        unitPrice.setCellValue("ĐƠN GIÁ");
        unitPrice.setCellStyle(headerTableStyle);
        Cell bill = headerTableRow.createCell(4);
        bill.setCellValue("THÀNH TIỀN");
        bill.setCellStyle(headerTableStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 2, 2));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 3, 3));
        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 4, 4));

        rowNum++;
        Row nullHeaderRow = sheet.createRow(rowNum);
        for (int i = 0; i < 5; i++) nullHeaderRow.createCell(i).setCellStyle(headerTableStyle);

        //// Table data
        CellStyle normalDayStyle = workbook.createCellStyle();
        DataFormat normalDayFormat = workbook.createDataFormat();
        XSSFFont normalDayFont = workbook.createFont();
        normalDayFont.setFontName("Times New Roman");
        normalDayFont.setFontHeightInPoints((short) 14);
        normalDayStyle.setDataFormat(normalDayFormat.getFormat("#,###"));
        normalDayStyle.setFont(normalDayFont);
        normalDayStyle.setAlignment(HorizontalAlignment.CENTER);
        normalDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        normalDayStyle.setBorderTop(BorderStyle.THIN);
        normalDayStyle.setBorderBottom(BorderStyle.THIN);
        normalDayStyle.setBorderLeft(BorderStyle.THIN);
        normalDayStyle.setBorderRight(BorderStyle.THIN);

        CellStyle sundayStyle = workbook.createCellStyle();
        DataFormat sundayFormat = workbook.createDataFormat();
        XSSFFont sundayFont = workbook.createFont();
        sundayFont.setColor(new XSSFColor(Color.BLUE, colorMap));
        sundayFont.setFontName("Times New Roman");
        sundayFont.setFontHeightInPoints((short) 14);
        sundayStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        sundayStyle.setFont(sundayFont);
        sundayStyle.setAlignment(HorizontalAlignment.CENTER);
        sundayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        sundayStyle.setFillBackgroundColor(IndexedColors.CORAL.index);
        sundayStyle.setFillForegroundColor(IndexedColors.CORAL.index);
        sundayStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        sundayStyle.setBorderTop(BorderStyle.THIN);
        sundayStyle.setBorderBottom(BorderStyle.THIN);
        sundayStyle.setBorderLeft(BorderStyle.THIN);
        sundayStyle.setBorderRight(BorderStyle.THIN);

        int firstRowDataTable = rowNum + 1;
        for (int i = 0; i < listOfData.size(); i++) {
            rowNum++;
            Row newRow = sheet.createRow(rowNum);

            List<String> data = getObjectData(listOfData.get(i));

            Cell cardinalNumberData = newRow.createCell(0);
            Cell dateData = newRow.createCell(1);
            Cell numberOfMealsData = newRow.createCell(2);
            Cell unitPriceData = newRow.createCell(3);
            Cell billData = newRow.createCell(4);

            cardinalNumberData.setCellValue(i + 1);
            dateData.setCellValue(data.get(0));
            numberOfMealsData.setCellValue(Double.parseDouble(data.get(1)));
            unitPriceData.setCellValue(unitPriceValue);
            billData.setCellFormula(String.format("C%d*D%d", rowNum + 1, rowNum + 1));
            formulaEvaluator.evaluateFormulaCell(billData);

            if (isSunday(data.get(0))) {
                cardinalNumberData.setCellStyle(sundayStyle);
                dateData.setCellStyle(sundayStyle);
                numberOfMealsData.setCellStyle(sundayStyle);
                unitPriceData.setCellStyle(sundayStyle);
                billData.setCellStyle(sundayStyle);
            } else {
                cardinalNumberData.setCellStyle(normalDayStyle);
                dateData.setCellStyle(normalDayStyle);
                numberOfMealsData.setCellStyle(normalDayStyle);
                unitPriceData.setCellStyle(normalDayStyle);
                billData.setCellStyle(normalDayStyle);
            }
        }
        int lastRowDataTable = rowNum;

        //// Footer table
        rowNum++;
        Row footerRow = sheet.createRow(rowNum);

        CellStyle footerStyle = workbook.createCellStyle();
        XSSFFont footerFont = workbook.createFont();
        footerFont.setFontName("Times New Roman");
        footerFont.setFontHeightInPoints((short) 14);
        footerFont.setBold(true);
        footerStyle.setFont(footerFont);
        footerStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        footerStyle.setAlignment(HorizontalAlignment.CENTER);
        footerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        footerStyle.setBorderTop(BorderStyle.THIN);
        footerStyle.setBorderBottom(BorderStyle.THIN);
        footerStyle.setBorderLeft(BorderStyle.THIN);
        footerStyle.setBorderRight(BorderStyle.THIN);

        List<Cell> nullCell = new ArrayList<>(){{
            add(footerRow.createCell(0));
        }};

        nullCell.forEach(cell -> cell.setCellStyle(footerStyle));

        Cell sumText = footerRow.createCell(1);
        sumText.setCellValue("Tổng");
        sumText.setCellStyle(footerStyle);

        Cell sumNOM = footerRow.createCell(2);
        sumNOM.setCellFormula(String.format("SUM(C%d:C%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumNOM);
        sumNOM.setCellStyle(footerStyle);

        Cell unitPriceFooter = footerRow.createCell(3);
        unitPriceFooter.setCellValue(unitPriceValue);
        unitPriceFooter.setCellStyle(footerStyle);

        Cell sumBill = footerRow.createCell(4);
        sumBill.setCellFormula(String.format("SUM(E%d:E%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumBill);
        sumBill.setCellStyle(footerStyle);

        //// Signature
        rowNum++;
        Row billToTextRow = sheet.createRow(rowNum);

        CellStyle billToTextStyle = workbook.createCellStyle();
        XSSFFont billToTextFont = workbook.createFont();
        billToTextFont.setFontName("Times New Roman");
        billToTextFont.setFontHeightInPoints((short) 14);
        billToTextFont.setBold(true);
        billToTextStyle.setFont(billToTextFont);
        billToTextStyle.setAlignment(HorizontalAlignment.LEFT);
        billToTextStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell billToTextCell = billToTextRow.createCell(0);
        billToTextCell.setCellValue("Bằng chữ: ");
        billToTextCell.setCellStyle(billToTextStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 4));

        rowNum += 2;
        Row signatureDayRow = sheet.createRow(rowNum);

        CellStyle signatureDayStyle = workbook.createCellStyle();
        XSSFFont signatureDayFont = workbook.createFont();
        signatureDayFont.setFontName("Times New Roman");
        signatureDayFont.setFontHeightInPoints((short) 12);
        signatureDayFont.setItalic(true);
        signatureDayStyle.setFont(signatureDayFont);
        signatureDayStyle.setAlignment(HorizontalAlignment.CENTER);
        signatureDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureDayCell = signatureDayRow.createCell(2);
        signatureDayCell.setCellValue("Bắc Ninh, ngày  tháng  năm");
        signatureDayCell.setCellStyle(signatureDayStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 2, 4));

        rowNum++;
        Row signatureRow = sheet.createRow(rowNum);

        CellStyle signatureStyle = workbook.createCellStyle();
        XSSFFont signatureFont = workbook.createFont();
        signatureFont.setFontName("Times New Roman");
        signatureFont.setFontHeightInPoints((short) 14);
        signatureFont.setBold(true);
        signatureStyle.setFont(signatureFont);
        signatureStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureCell = signatureRow.createCell(0);
        signatureCell.setCellValue("   Giám đốc                             Kế toán trưởng                               Lập biểu            ");
        signatureCell.setCellStyle(signatureStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 4));

        //// Write listOfData to workbook
        var fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
        return excelFile;
    }

    private static List<String> getObjectData(Object obj) throws IllegalAccessException {
        Field[] allFields = obj.getClass().getDeclaredFields();
        List<String> data = new ArrayList<>();
        for (Field field : allFields) {
            field.setAccessible(true);
            data.add(field.get(obj).toString());
        }
        return data;
    }

    private static boolean isSunday(String date) {
        String[] dateArray = date.split("/");
        LocalDate localDate = LocalDate.of(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
        DayOfWeek day = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));

        return day == DayOfWeek.SUNDAY;
    }

}
