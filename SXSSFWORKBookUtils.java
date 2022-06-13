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
			//������ʾSXSSFWorkbookֻ�ᱣ��100���������ڴ��У����������ݶ���д������������Ļ�ռ�õ��ڴ�ͻ����
			 sxssfWorkbook = new SXSSFWorkbook(getXSSFWorkbook(filePath),100);
			 //��ȡ��һ��Sheetҳ
			 SXSSFSheet sheet = sxssfWorkbook.getSheetAt(0);
			 
				 for (int z = 0; z < 100000; z++) {
					 SXSSFRow row = sheet.createRow(z);
					 
						 row.createCell(0).setCellValue("��ã�");
						 row.createCell(1).setCellValue("������д��demo");
					 
				 }
			 /*
			  * 1.��������������
			  * 2.�����������ж�ѭ������
			  * 3.
			  */
			
			 outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
			 sxssfWorkbook.write(outputStream);
			 outputStream.flush();
			 sxssfWorkbook.dispose();// �ͷ�workbook��ռ�õ�����windows��Դ
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
	 * �ȴ���һ��XSSFWorkbook����
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
			workbook.createSheet("����Sheet");
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
