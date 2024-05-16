package test.builder;

import java.util.List;

/**
 * description  BuilderTest <BR>
 * <p>
 * author: zhao.song
 * date: created in 16:56  2021/6/22
 * company: TRS信息技术有限公司
 * version 1.0
 */
public class ExcelExportTest<T> {

    private ExcelData<T> data;

    public ExcelData<T> getData() {
        return data;
    }

    private ExcelExportTest(){}

    static class ExcelExportTestBuilder<T> {
        private ExcelExportTest<T> instance = new ExcelExportTest<>();

        private void init() {
            if (instance.data == null) {
                instance.data = new ExcelData<T>();
            }
        }

        public ExcelExportTestBuilder<T> buildFileName(String fileName) {
            init();
            instance.data.setFileName(fileName);
            return this;
        }

        public ExcelExportTestBuilder<T> buildCellTitle(List<String> cellTitle) {
            init();
            instance.data.setCellTitle(cellTitle);
            return this;
        }

        public ExcelExportTestBuilder<T> buildCellData(List<List<T>> cellData) {
            init();
            instance.data.setCellData(cellData);
            return this;
        }

        public ExcelExportTest<T> build() {
            return instance;
        }
    }


}
