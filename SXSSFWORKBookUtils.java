package zybMavenDemo;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SXSSFWORKBookUtils {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws FileNotFoundException, InvalidFormatException {
		long startTime = System.currentTimeMillis();
		String filePath = "E:\\txt\\333.xlsx";
		SXSSFWorkbook sxssfWorkbook = null;
		BufferedOutputStream outputStream = null;
		try {
			//这样表示SXSSFWorkbook只会保留100条数据在内存中，其它的数据都会写到磁盘里，这样的话占用的内存就会很少
			 sxssfWorkbook = new SXSSFWorkbook(getXSSFWorkbook(filePath),100);
			 //获取第一个Sheet页
			 SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);
			 
				 for (int z = 0; z < 100000; z++) {
					 SXSSFRow row = sheet.createRow(z);
					 
						 row.createCell(0).setCellValue("你好：");
						 row.createCell(1).setCellValue("这是乱写的demo");
					 
				 }
			 /*
			  * 1.计算数据总条数
			  * 2.根据条数，判断循环次数
			  * 3.
			  */
			
			 outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
			 sxssfWorkbook.write(outputStream);
			 outputStream.flush();
			 sxssfWorkbook.dispose();// 释放workbook所占用的所有windows资源
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
	}
	
	
	/**
	 * 先创建一个XSSFWorkbook对象
	 * @param filePath
	 * @return
	 */
	public static XSSFWorkbook getXSSFWorkbook(String filePath) {
		XSSFWorkbook workbook =  null;
		BufferedOutputStream outputStream = null;
		try {
			File fileXlsxPath = new File(filePath);
			outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
			workbook = new XSSFWorkbook();
			workbook.createSheet("测试Sheet");
			workbook.write(outputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}
	

}
