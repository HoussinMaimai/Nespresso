package nespresso;
import org.apache.poi.ss.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
public class ExcelReader {
    public static String readValueFromExcel(int nbrCellule) {
        String filePath = "C:/Users/ehmaimai/IdeaProjects/NespressoFDP/Data.xlsx";
        File file = new File(filePath);
        String cellValue = null;

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));

            try {
                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(1);
                Cell cell = row.getCell(nbrCellule);
                cellValue = cell.getStringCellValue();
            } catch (Throwable var8) {
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return cellValue;
    }


    public static int getProductQuantityFromExcel(int nbrCellule) {
        String filePath = "C:/Users/ehmaimai/IdeaProjects/NespressoFDP/Data.xlsx";
        File file = new File(filePath);
        int Qte=0;

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(file));

            try {
                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(1);
                Cell cell = row.getCell(nbrCellule);
                if (cell.getCellType() == CellType.NUMERIC) {
                    Qte = (int) cell.getNumericCellValue();
                }
            } catch (Throwable var8) {
                if (workbook != null) {
                    try {
                        workbook.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException var9) {
            var9.printStackTrace();
        }

        return Qte;
    }

}
