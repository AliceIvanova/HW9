
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.common.collect.Table;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Проверка ZIP архива")
public class ZipParsingTest {
  private ClassLoader cl = ZipParsingTest.class.getClassLoader();

  @DisplayName("Проверка PDF файла")
  @Test
  void checkPdfFileContent() throws Exception {
    try (ZipInputStream zis = new ZipInputStream(
      cl.getResourceAsStream("learning.zip"))) {
      ZipEntry entry;

      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().equals("Lermontov.pdf")) {
          PDF pdf = new PDF(zis);
          assertThat(pdf.numberOfPages).isEqualTo(1);
          assertThat(pdf.text).contains("И скучно и грустно");
          break;
        }
      }
    }
  }

  @DisplayName("Проверка CSV файла")
  @Test
  void checkCSVFileContent() throws Exception {
    try (InputStream zis = new ZipInputStream(
      cl.getResourceAsStream("learning.zip"))) {
      ZipEntry entry;
      while ((entry = ((ZipInputStream) zis).getNextEntry()) != null) {
        if (entry.getName().equals("import_ou_csv.csv")) {
          CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
          final List<String[]> data = csvReader.readAll();
          assertThat(data).contains(
            new String[]{
              "1", "2"
            },
            new String[]
              {
                "Пример1", "Пример2"
              }
          );
        }
      }
    }
  }

  @Test
  @DisplayName("Проверка XLSX файла")
  void checkXlsxFileFromZipTest() throws Exception {
    try (ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("learning.zip"))) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().equals("import_ou_xlsx.xlsx")) {
          XLS xls = new XLS(zis);
          Table.Cell cell0 = (Table.Cell) xls.excel.getSheetAt(0).getRow(1).getCell(0);
          Table.Cell cell1 = (Table.Cell) xls.excel.getSheetAt(0).getRow(1).getCell(1);
          assertThat(cell0).isNotNull();
          assertThat(cell1).isNotNull();
          assertThat(cell0.toString()).isEqualTo("Пример1");
          assertThat(cell1.toString()).isEqualTo("Пример2");
        }
      }
    }
  }
}