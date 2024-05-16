package test.builder;

import java.util.List;
import java.util.Objects;

/**
 * description  ExcelData <BR>
 * <p>
 * author: zhao.song
 * date: created in 16:57  2021/6/22
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class ExcelData<T> {

    private String fileName;

    private List<String> cellTitle;

    private List<List<T>> cellData;

    public String getFileName() {
        return fileName;
    }

    public ExcelData<T> setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public List<String> getCellTitle() {
        return cellTitle;
    }

    public ExcelData<T> setCellTitle(List<String> cellTitle) {
        this.cellTitle = cellTitle;
        return this;
    }

    public List<List<T>> getCellData() {
        return cellData;
    }

    public ExcelData<T> setCellData(List<List<T>> cellData) {
        this.cellData = cellData;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExcelData<?> excelData = (ExcelData<?>) o;
        return Objects.equals(fileName, excelData.fileName) && Objects.equals(cellTitle, excelData.cellTitle) && Objects.equals(cellData, excelData.cellData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, cellTitle, cellData);
    }
}
