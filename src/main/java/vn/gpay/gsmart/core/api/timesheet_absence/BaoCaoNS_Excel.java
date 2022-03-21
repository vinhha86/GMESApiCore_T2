package vn.gpay.gsmart.core.api.timesheet_absence;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import vn.gpay.gsmart.core.timesheet_absence.TimesheetAbsence_Binding;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class BaoCaoNS_Excel {

    public static File createBaoCaoNS(String filePath, List<?> listOfData, String date) throws IOException, IllegalAccessException {
        double unitPriceValue = 20000;
        File excelFile = new File(filePath);


        //// Setup
        var workbook = new XSSFWorkbook();
        var sheet = workbook.createSheet("Báo Cáo Nhân Sự");
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
        sheet.setColumnWidth(1, 8200);
        sheet.setColumnWidth(2, 4000);
        sheet.setColumnWidth(3, 4000);
        sheet.setColumnWidth(4, 4000);
        sheet.setColumnWidth(5, 4000);
        sheet.setColumnWidth(6, 4000);
        sheet.setColumnWidth(7, 4000);

        //// Title part
        int rowNum = 0;
        Row firstTitleRow = sheet.createRow(rowNum);
        CellStyle firstTitleStyle = workbook.createCellStyle();
        XSSFFont firstTitleFont = workbook.createFont();
        firstTitleFont.setFontName("Times New Roman");
        firstTitleFont.setFontHeightInPoints((short) 20);
        firstTitleFont.setItalic(true);
        firstTitleFont.setBold(true);
        firstTitleStyle.setFont(firstTitleFont);
        firstTitleStyle.setAlignment(HorizontalAlignment.CENTER);
        firstTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell firstTitleCell = firstTitleRow.createCell(0);
        firstTitleCell.setCellValue("BÁO CÁO NHÂN SỰ"+"("+ date+")");
        firstTitleCell.setCellStyle(firstTitleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));


//        rowNum++;
//        Row thirdTitleRow = sheet.createRow(rowNum);
//        CellStyle thirdTitleStyle = workbook.createCellStyle();
//        DataFormat thirdTitleFormat = workbook.createDataFormat();
//        XSSFFont thirdTitleFont = workbook.createFont();
//        thirdTitleFont.setFontName("Times New Roman");
//        thirdTitleFont.setFontHeightInPoints((short) 16);
//        thirdTitleFont.setItalic(true);
//        thirdTitleFont.setBold(true);
//        thirdTitleStyle.setDataFormat(thirdTitleFormat.getFormat("\"Ngày\" dd \"tháng\" mm \"năm\" yyyy"));
//        thirdTitleStyle.setFont(thirdTitleFont);
//        thirdTitleStyle.setAlignment(HorizontalAlignment.CENTER);
//        thirdTitleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//
//        Cell thirdTitleCell = thirdTitleRow.createCell(0);
//        thirdTitleCell.setCellValue(date);
//        thirdTitleCell.setCellStyle(thirdTitleStyle);
//        sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 7));



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

//        System.out.print("return 0");

        Cell cardinalNumber = headerTableRow.createCell(0);
        cardinalNumber.setCellValue("STT");
        cardinalNumber.setCellStyle(headerTableStyle);
        Cell donvi = headerTableRow.createCell(1);
        donvi.setCellValue("ĐƠN VỊ");
        donvi.setCellStyle(headerTableStyle);
        Cell TongLaodong = headerTableRow.createCell(2);
        TongLaodong.setCellValue("TỔNG LAO ĐỘNG");
        TongLaodong.setCellStyle(headerTableStyle);
        Cell soCoMat = headerTableRow.createCell(3);
        soCoMat.setCellValue("SỐ CÓ MẶT");
        soCoMat.setCellStyle(headerTableStyle);
        Cell nghiPhep = headerTableRow.createCell(4);
        nghiPhep.setCellValue("NGHỈ PHÉP");
        nghiPhep.setCellStyle(headerTableStyle);
        Cell khongPhep = headerTableRow.createCell(5);
        khongPhep.setCellValue("KHÔNG PHÉP");
        khongPhep.setCellStyle(headerTableStyle);
        Cell cachLy = headerTableRow.createCell(6);
        cachLy.setCellValue("CÁCH LY");
        cachLy.setCellStyle(headerTableStyle);
        Cell khac = headerTableRow.createCell(7);
        khac.setCellValue("KHÁC");
        khac.setCellStyle(headerTableStyle);

//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 0, 0));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 1, 1));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 2, 2));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 3, 3));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 4, 4));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 5, 5));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 6, 6));
//        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum + 1, 7, 7));


//        rowNum++;
//        Row nullHeaderRow = sheet.createRow(rowNum);
//        for (int i = 0; i < 8; i++) nullHeaderRow.createCell(i).setCellStyle(headerTableStyle);
//        System.out.print("return 111");

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

//        CellStyle sundayStyle = workbook.createCellStyle();
//        DataFormat sundayFormat = workbook.createDataFormat();
//        XSSFFont sundayFont = workbook.createFont();
//        sundayFont.setColor(new XSSFColor(Color.BLUE, colorMap));
//        sundayFont.setFontName("Times New Roman");
//        sundayFont.setFontHeightInPoints((short) 14);
//        sundayStyle.setDataFormat(sundayFormat.getFormat("#,###"));
//        sundayStyle.setFont(sundayFont);
//        sundayStyle.setAlignment(HorizontalAlignment.CENTER);
//        sundayStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//        sundayStyle.setFillBackgroundColor(IndexedColors.CORAL.index);
//        sundayStyle.setFillForegroundColor(IndexedColors.CORAL.index);
//        sundayStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//        sundayStyle.setBorderTop(BorderStyle.THIN);
//        sundayStyle.setBorderBottom(BorderStyle.THIN);
//        sundayStyle.setBorderLeft(BorderStyle.THIN);
//        sundayStyle.setBorderRight(BorderStyle.THIN);


        int firstRowDataTable = rowNum + 1;
        for (int i = 0; i < listOfData.size(); i++) {
            rowNum++;
            Row newRow = sheet.createRow(rowNum);

            TimesheetAbsence_Binding data = (TimesheetAbsence_Binding) listOfData.get(i);


            Cell cardinalNumberData = newRow.createCell(0);
            cardinalNumberData.setCellStyle(normalDayStyle);
            Cell donViData = newRow.createCell(1);
            donViData.setCellStyle(normalDayStyle);
            Cell tongLaoDongData = newRow.createCell(2);
            tongLaoDongData.setCellStyle(normalDayStyle);
            Cell soCoMatData = newRow.createCell(3);
            soCoMatData.setCellStyle(normalDayStyle);
            Cell nghiPhepData = newRow.createCell(4);
            nghiPhepData.setCellStyle(normalDayStyle);
            Cell khongPhepData = newRow.createCell(5);
            khongPhepData.setCellStyle(normalDayStyle);
            Cell cachLyData = newRow.createCell(6);
            cachLyData.setCellStyle(normalDayStyle);
            Cell khacData = newRow.createCell(7);
            khacData.setCellStyle(normalDayStyle);

//   truyền data vào cell
            cardinalNumberData.setCellValue(i + 1);
            donViData.setCellValue(data.getOrgName());
            if(data.getTongLaoDong() == 0){
                tongLaoDongData.setCellValue("-");
            }
            else tongLaoDongData.setCellValue(data.getTongLaoDong());

            if(data.getSoCoMat() == 0){
                soCoMatData.setCellValue("-");
            }
            else soCoMatData.setCellValue((data.getSoCoMat()));

            if(data.getNghiPhep() == 0){
                nghiPhepData.setCellValue("-");
            }
            else nghiPhepData.setCellValue((data.getNghiPhep()));

            if(data.getNghiKhongPhep() == 0){
                khongPhepData.setCellValue("-");
            }
            else khongPhepData.setCellValue((data.getNghiKhongPhep()));

            if(data.getNghiCachLy() == 0){
                cachLyData.setCellValue("-");
            }
            else cachLyData.setCellValue((data.getNghiCachLy()));

            if(data.getNghiConLai() == 0){
                khacData.setCellValue("-");
            }
            else khacData.setCellValue((data.getNghiConLai()));



        }
//        System.out.print("return 2");
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
//        footerStyle.setDataFormat(sundayFormat.getFormat("#,###"));
        footerStyle.setAlignment(HorizontalAlignment.CENTER);
        footerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        footerStyle.setBorderTop(BorderStyle.THIN);
        footerStyle.setBorderBottom(BorderStyle.THIN);
        footerStyle.setBorderLeft(BorderStyle.THIN);
        footerStyle.setBorderRight(BorderStyle.THIN);

        List<Cell> nullCell = new ArrayList<>(){{
            add(footerRow.createCell(0));
            add(footerRow.createCell(7));
        }};

        nullCell.forEach(cell -> cell.setCellStyle(footerStyle));

        Cell sumText = footerRow.createCell(1);
        sumText.setCellValue("Tổng");
        sumText.setCellStyle(footerStyle);

        Cell sumTongLaoDong = footerRow.createCell(2);
        sumTongLaoDong.setCellFormula(String.format("SUM(C%d:C%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumTongLaoDong);
        sumTongLaoDong.setCellStyle(footerStyle);

        Cell sumSoCoMat = footerRow.createCell(3);
        sumSoCoMat.setCellFormula(String.format("SUM(D%d:D%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumSoCoMat);
        sumSoCoMat.setCellStyle(footerStyle);

        Cell sumNghiPhep = footerRow.createCell(4);
        sumNghiPhep.setCellFormula(String.format("SUM(E%d:E%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumNghiPhep);
        sumNghiPhep.setCellStyle(footerStyle);

        Cell sumKhongPhep = footerRow.createCell(5);
        sumKhongPhep.setCellFormula(String.format("SUM(F%d:F%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumKhongPhep);
        sumKhongPhep.setCellStyle(footerStyle);

        Cell sumCachLy = footerRow.createCell(6);
        sumCachLy.setCellFormula(String.format("SUM(G%d:G%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumCachLy);
        sumCachLy.setCellStyle(footerStyle);

        Cell sumKhac = footerRow.createCell(7);
        sumKhac.setCellFormula(String.format("SUM(H%d:H%d)", firstRowDataTable + 1, lastRowDataTable + 1));
        formulaEvaluator.evaluateFormulaCell(sumKhac);
        sumKhac.setCellStyle(footerStyle);

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
        billToTextCell.setCellValue("Ghi chú ");
        billToTextCell.setCellStyle(billToTextStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 7));

        rowNum += 5;
        Row signatureDayRow = sheet.createRow(rowNum);

        CellStyle signatureDayStyle = workbook.createCellStyle();
        XSSFFont signatureDayFont = workbook.createFont();
        signatureDayFont.setFontName("Times New Roman");
        signatureDayFont.setFontHeightInPoints((short) 12);
        signatureDayFont.setItalic(true);
        signatureDayStyle.setFont(signatureDayFont);
        signatureDayStyle.setAlignment(HorizontalAlignment.RIGHT);
        signatureDayStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        Cell signatureDayCell = signatureDayRow.createCell(3);
        signatureDayCell.setCellValue("Bắc Ninh, ngày  tháng  năm   ");
        signatureDayCell.setCellStyle(signatureDayStyle);

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 3, 7));

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

        sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, 7));

        //// Write listOfData to workbook
        var fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();
//        System.out.print("return return");
        return excelFile;
    }


}
