package koodali.service;

import koodali.model.Student;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StudentExcelService {


    public StudentExcelService() {
    }


    public static void exportStudentsToExcel(List<Student> students, String outputFilename) throws IOException {
        // create a new file
        //create columns given the student's constructer, one constructor field is one column
        //for each student, a seperate row.
        //fill the values accordingly?

        //code taken and adapted from https://github.com/apache/poi/blob/trunk/poi-examples/src/main/java/org/apache/poi/examples/hssf/usermodel/HSSFReadWrite.java
        /*try (HSSFWorkbook wb = new HSSFWorkbook();
             FileOutputStream out = new FileOutputStream(outputFilename)) {

            HSSFSheet sheet = wb.createSheet();
            HSSFCellStyle cs = wb.createCellStyle();
            HSSFCellStyle cs2 = wb.createCellStyle();
            HSSFFont f = wb.createFont();
            HSSFFont f2 = wb.createFont();

            f.setFontHeightInPoints((short) 12);
            f.setColor((short) 0xA);
            f.setBold(true);

            f2.setFontHeightInPoints((short) 10);
            f2.setColor((short) 0xf);
            f2.setBold(true);

            cs.setFont(f);
            cs.setDataFormat(HSSFDataFormat.getBuiltinFormat("($#,##0_);[Red]($#,##0)"));
            cs2.setBorderBottom(BorderStyle.THIN);
            cs2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cs2.setFillForegroundColor((short) 0xA);
            cs2.setFont(f2);

            wb.setSheetName(0, "Homework points overview for Section"+section.getName());

            int rownum;
            sheet.createRow(0).createCell(0).setCellValue(new HSSFRichTextString("Export"));

            for (rownum = 1; rownum < students.size() + 2; rownum++) {
                HSSFRow r = sheet.createRow(rownum);
                r.setHeight((short) 0x249);
            }
            //here comes more

            wb.write(out);
        }*/
    }
    //Imports/exports the students from excel file to StudentRepository

    //importStudents(File excelfile) -> studentService.createStudent(Student s)
    //exportStudents(File excelFile)
}
