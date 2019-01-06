package room;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcReader {
	// ��ó: http://hellogk.tistory.com/14 [IT Code Storage]
	public List<Employee> xlsxToEmployeeList(String filePath) {
		List<Employee> list = new ArrayList<Employee>();
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;

		try {
		fis=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		//��Ʈ �� (ù��°���� �����ϹǷ� 0�� �ش�)
		//���� �� ��Ʈ�� �б����ؼ��� FOR���� �ѹ��� �����ش�
		XSSFSheet sheet=workbook.getSheetAt(0);
		//���� ��
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){
		    //�����д´�
		    XSSFRow row=sheet.getRow(rowindex);
            Employee emp = new Employee();
		    if(row !=null){
		        //���� ��
		        int cells=row.getPhysicalNumberOfCells();
		        for(columnindex=0;columnindex<=cells;columnindex++){
		            //������ �д´�
		            XSSFCell cell=row.getCell(columnindex);

		            String value="";
		            //���� ���ϰ�츦 ���� ��üũ
		            if(cell==null){
		                continue;
		            }else{
		                //Ÿ�Ժ��� ���� �б�
		                switch (cell.getCellType()){
			                case XSSFCell.CELL_TYPE_FORMULA:
			                    value=cell.getCellFormula();
			                    break;
			                case XSSFCell.CELL_TYPE_NUMERIC:
			                    value=(int)cell.getNumericCellValue() +"";
			                    break;
			                case XSSFCell.CELL_TYPE_STRING:
			                    value=cell.getStringCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_BLANK:
			                    value=cell.getBooleanCellValue()+"";
			                    break;
			                case XSSFCell.CELL_TYPE_ERROR:
			                    value=cell.getErrorCellValue()+"";
			                    break;
		                }
		                switch (columnindex){
		                case 0:
		                    emp.setEmpNum(Integer.parseInt(value));
		                    break;
		                case 1:
		                    emp.setEmpName(value);;
		                    break;
		                case 2:
		                    emp.setGender(value);;
		                    break;
		                default :
		                	break;
		                }
		            }
		        }
		        emp.setAssignedRoom(new ArrayList<String>());
		        list.add(emp);
		    }
		}
		} catch(Exception e) {
			
		}
		return list;
	}
	
	public void employeeListToXlsx(List<Employee> list) {
        XSSFWorkbook workbook = new XSSFWorkbook(); // �� ���� ����
        XSSFSheet sheet = workbook.createSheet("��Ʈ��"); // �� ��Ʈ(Sheet) ����
        XSSFRow row = sheet.createRow(0); // ������ ���� 0������ ����
        row.createCell(0).setCellValue("����");; // ���� ���� 0������ ����
        row.createCell(1).setCellValue("�̸�");; // ���� ���� 0������ ����
        row.createCell(2).setCellValue("����");; // ���� ���� 0������ ����
        row.createCell(3).setCellValue("���ȣ");; // ���� ���� 0������ ����
        
        Iterator<Employee> it = list.iterator();
        while(it.hasNext()) {
        	Employee emp = it.next();
        	row = sheet.createRow(emp.getEmpNum());
        	row.createCell(0).setCellValue(emp.getEmpNum());
        	row.createCell(1).setCellValue(emp.getEmpName());
        	row.createCell(2).setCellValue(emp.getGender());
        	for(int i=0; i<emp.getAssignedRoom().size(); i++) {
            	row.createCell(3+i).setCellValue(emp.getAssignedRoom().get(i));
        	}
        }
        try {
        	String temp = System.getProperty ( "user.home" );
            FileOutputStream fileoutputstream = new FileOutputStream(temp + "\\Documents\\out.xlsx");
            workbook.write(fileoutputstream);
            fileoutputstream.close();
            System.out.println("�������ϻ�������");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�������ϻ�������");
        }


	}
	
	public static void loadTest(List<Employee> list) {
		Iterator<Employee> it = list.iterator();
		while(it.hasNext()) {
			Employee temp = (Employee) it.next();
			System.out.println(temp.toString());
		}
	}
}