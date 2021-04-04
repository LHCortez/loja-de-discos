package com.luiz.lhcdiscos.exporters;

import com.luiz.lhcdiscos.models.entities.*;
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

        int columnCount = 0;

        if (!produtoList.isEmpty()) {
            if (produtoList.get(0) instanceof Camiseta) {
                createCell(rowHeader, columnCount++, "Camiseta ID", style);
            } else if (produtoList.get(0) instanceof Album) {
                createCell(rowHeader, columnCount++, "Album ID", style);
            } else if (produtoList.get(0) instanceof Patch) {
                createCell(rowHeader, columnCount++, "Patch ID", style);
            } else if (produtoList.get(0) instanceof Livro) {
                createCell(rowHeader, columnCount++, "Livro ID", style);
            }
        } else {
            createCell(rowHeader, columnCount++, "ID", style);
        }

        createCell(rowHeader, columnCount++, "Nome", style);
        createCell(rowHeader, columnCount++, "Descrição", style);
        createCell(rowHeader, columnCount++, "Preço (R$)", style);
        createCell(rowHeader, columnCount++, "Capa", style);
        createCell(rowHeader, columnCount++, "Lançamento", style);

        if (!produtoList.isEmpty()) {
            if (produtoList.get(0) instanceof Camiseta) {
                createCell(rowHeader, columnCount++, "Tamanho", style);
            } else if (produtoList.get(0) instanceof Album) {
                createCell(rowHeader, columnCount++, "Formato", style);
            } else if (produtoList.get(0) instanceof Livro) {
                createCell(rowHeader, columnCount++, "Autor", style);
            }
        }

        createCell(rowHeader, columnCount++, "Banda", style);
        createCell(rowHeader, columnCount++, "Banda ID", style);
        createCell(rowHeader, columnCount, "Banda Gênero", style);

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
                createCell(row, columnCount++, camiseta.getTamanho().toString(), style);
            } else if (produto instanceof Album) {
                Album album = (Album) produto;
                createCell(row, columnCount++, album.getFormato().toString(), style);
            } else if (produto instanceof Livro) {
                Livro livro = (Livro) produto;
                createCell(row, columnCount++, livro.getAutor(), style);
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
