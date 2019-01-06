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
	// 출처: http://hellogk.tistory.com/14 [IT Code Storage]
	public List<Employee> xlsxToEmployeeList(String filePath) {
		List<Employee> list = new ArrayList<Employee>();
		FileInputStream fis = null;
		XSSFWorkbook workbook = null;

		try {
		fis=new FileInputStream(filePath);
		workbook=new XSSFWorkbook(fis);
		int rowindex=0;
		int columnindex=0;
		//시트 수 (첫번째에만 존재하므로 0을 준다)
		//만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
		XSSFSheet sheet=workbook.getSheetAt(0);
		//행의 수
		int rows=sheet.getPhysicalNumberOfRows();
		for(rowindex=1;rowindex<rows;rowindex++){
		    //행을읽는다
		    XSSFRow row=sheet.getRow(rowindex);
            Employee emp = new Employee();
		    if(row !=null){
		        //셀의 수
		        int cells=row.getPhysicalNumberOfCells();
		        for(columnindex=0;columnindex<=cells;columnindex++){
		            //셀값을 읽는다
		            XSSFCell cell=row.getCell(columnindex);

		            String value="";
		            //셀이 빈값일경우를 위한 널체크
		            if(cell==null){
		                continue;
		            }else{
		                //타입별로 내용 읽기
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
        XSSFWorkbook workbook = new XSSFWorkbook(); // 새 엑셀 생성
        XSSFSheet sheet = workbook.createSheet("시트명"); // 새 시트(Sheet) 생성
        XSSFRow row = sheet.createRow(0); // 엑셀의 행은 0번부터 시작
        row.createCell(0).setCellValue("순번");; // 행의 셀은 0번부터 시작
        row.createCell(1).setCellValue("이름");; // 행의 셀은 0번부터 시작
        row.createCell(2).setCellValue("성별");; // 행의 셀은 0번부터 시작
        row.createCell(3).setCellValue("방번호");; // 행의 셀은 0번부터 시작
        
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
            System.out.println("엑셀파일생성성공");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("엑셀파일생성실패");
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