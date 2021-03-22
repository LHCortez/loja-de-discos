package com.luiz.lhcdiscos.exporters;

import com.luiz.lhcdiscos.models.entities.Banda;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BandaExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<Banda> bandList;

    public BandaExcelExporter(List<Banda> bandList) {
        this.bandList = bandList;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        String safeName = WorkbookUtil.createSafeSheetName("Bandas");
        sheet = workbook.createSheet(safeName);

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
//        font.setFontHeight((short) 16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);

        createCell(row, 0, "Banda ID", style);
        createCell(row, 1, "Nome", style);
        createCell(row, 2, "Gênero", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
//        font.setFontHeight((short) 14);
        style.setFont(font);

        for (Banda banda : bandList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, banda.getId(), style);
            createCell(row, columnCount++, banda.getNome(), style);
            createCell(row, columnCount, banda.getGenero().toString(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
