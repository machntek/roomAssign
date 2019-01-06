package room;

import java.util.ArrayList;
import java.util.Queue;

public class Employee {
	private int empNum;
	private String empName;
	private String gender;
	private ArrayList<String> assignedRoom;
	
	
	public ArrayList<String> getAssignedRoom() {
		return assignedRoom;
	}
	public void setAssignedRoom(ArrayList<String> assignedRoom) {
		this.assignedRoom = assignedRoom;
	}
	
	public int getEmpNum() {
		return empNum;
	}
	public void setEmpNum(int empNum) {
		this.empNum = empNum;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("num : " + empNum);
		sb.append(", name : " + empName);
		sb.append(", gender : " + gender);
		for(int i=0; i<assignedRoom.size(); i++) {
			sb.append(", assignedRoom" + (i+1) + " :" + assignedRoom.get(i));
		} 
		return sb.toString();
	}

	
}
