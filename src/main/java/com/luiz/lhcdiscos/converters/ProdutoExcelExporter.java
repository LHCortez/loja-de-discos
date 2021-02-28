package com.luiz.lhcdiscos.converters;

import com.luiz.lhcdiscos.models.Album;
import com.luiz.lhcdiscos.models.Camiseta;
import com.luiz.lhcdiscos.models.Produto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoExcelExporter {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<? extends Produto> produtoList;

    public ProdutoExcelExporter(List<? extends Produto> produtoList) {
        this.produtoList = produtoList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName("Produtos"));
    }


    private void writeHeaderLine() {

        Row rowHeader = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);

        if (produtoList.get(0) instanceof Camiseta) {
            createCell(rowHeader, 0, "Camiseta ID", style);
        } else if (produtoList.get(0) instanceof Album) {
            createCell(rowHeader, 0, "Album ID", style);
        }

        createCell(rowHeader, 1, "Nome", style);
        createCell(rowHeader, 2, "Descrição", style);
        createCell(rowHeader, 3, "Preço (R$)", style);
        createCell(rowHeader, 4, "Capa", style);
        createCell(rowHeader, 5, "Lançamento", style);
        if (produtoList.get(0) instanceof Camiseta) {
            createCell(rowHeader, 6, "Tamanho", style);
        } else if (produtoList.get(0) instanceof Album) {
            createCell(rowHeader, 6, "Formato", style);
        }
        createCell(rowHeader, 7, "Banda", style);
        createCell(rowHeader, 8, "Banda ID", style);
        createCell(rowHeader, 9, "Banda Gênero", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        Cell cell = row.createCell(columnCount);
        cell.setCellStyle(style);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) value).doubleValue());
        } else if (value instanceof LocalDate) {
            cell.setCellValue((LocalDate) value);
        } else {
            cell.setCellValue((String) value);
        }
        sheet.autoSizeColumn(columnCount);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle styleData = workbook.createCellStyle();
        styleData.setDataFormat(createHelper.createDataFormat().getFormat("dd/MM/yyyy"));
        CellStyle styleCurrency = workbook.createCellStyle();
        styleCurrency.setAlignment(HorizontalAlignment.RIGHT);
        DataFormat datafrmt = workbook.createDataFormat();
        styleCurrency.setDataFormat(datafrmt.getFormat("#,##0.00"));

        for (Produto produto : produtoList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, produto.getId(), style);
            createCell(row, columnCount++, produto.getNome(), style);
            createCell(row, columnCount++, produto.getDescricao(), style);
            createCell(row, columnCount++, produto.getPreco(), styleCurrency);
            createCell(row, columnCount++, produto.getCapa(), style);
            createCell(row, columnCount++, produto.getLancamento(), styleData);
            if (produto instanceof Camiseta) {
                Camiseta camiseta = (Camiseta) produto;
                createCell(row, columnCount++, camiseta.getSize().toString(), style);
            } else if (produto instanceof Album) {
                Album album = (Album) produto;
                createCell(row, columnCount++, album.getFormato().toString(), style);
            }
            createCell(row, columnCount++, produto.getBanda().getNome(), style);
            createCell(row, columnCount++, produto.getBanda().getId(), style);
            createCell(row, columnCount, produto.getBanda().getGenero().toString(), style);
        }

//        Set the width (in units of 1/256th of a character width)
//        Para limitar a largura da coluna "Descrição", que contém um texto longo
        sheet.setColumnWidth(2, 30000);

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
