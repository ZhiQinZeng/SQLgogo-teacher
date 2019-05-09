package service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
 
 import org.apache.poi.hssf.usermodel.HSSFCell;
 import org.apache.poi.hssf.usermodel.HSSFRow;
 import org.apache.poi.hssf.usermodel.HSSFSheet;
 import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import bean.StuUser;


public class ReadExcel {
	public List<StuUser> readXls(String path) throws IOException {
        InputStream is = new FileInputStream(path);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        StuUser student = null;
        List<StuUser> list = new ArrayList<StuUser>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow != null) {
                    student = new StuUser();
                    HSSFCell name = hssfRow.getCell(0);
                    HSSFCell username = hssfRow.getCell(1);
                    //HSSFCell classname = hssfRow.getCell(2);
                    
                    String username2 = getValue(username);
                    BigDecimal bd = new BigDecimal(username2);
                    //String username1 = String.valueOf(getValue(username));
                    student.setRealname(getValue(name));
                    student.setUsername(bd.toPlainString());
                    //student.setClassname(getValue(classname));
                    
                    list.add(student);
                }
            }
        }
        return list;
    }
    
     @SuppressWarnings("static-access")
    private String getValue(HSSFCell hssfCell) {
    	 if (hssfCell.getCellType() == CellType.BOOLEAN) {        
    		 // 返回布尔类型的值              
    		 return String.valueOf(hssfCell.getBooleanCellValue());       
    	} else if (hssfCell.getCellType() == CellType.NUMERIC) {    
    			 // 返回数值类型的值            
    		 return String.valueOf(hssfCell.getNumericCellValue());       
    	} else {                
    				 // 返回字符串类型的值            
    		 return String.valueOf(hssfCell.getStringCellValue());             }
    	 }

    		
        }


