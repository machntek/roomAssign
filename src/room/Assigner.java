package room;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Assigner {
		boolean[][] check;
		int empSize;
	
	public void assignEntireTime(List<Employee> list, int weeks, List<Integer> opt) throws Exception {
		empSize = list.size();
		check = new boolean[empSize][empSize];
		for(int i=0; i<empSize; i++) {
			check[i][i] = true;
		}
		
		for(int i=0; i<weeks; i++) {
			assignOneTime(list, i, opt);
		}
		
	}
	
	public void assignOneTime(List<Employee> list, int week, List<Integer> opt) throws Exception{

		
		if(opt.size()<3) {
			int a = opt.get(0).intValue();
			int b = opt.get(1).intValue();
			
			Set<Integer> empSet = new HashSet<>();
			for(int i=0; i<b; i++) {
				int random = getRandomNum(empSet);
				empSet.add(random);
				list.get(random).getAssignedRoom().add(a+"인실 "+ (i+1) +"번방");
				
				Queue<Integer> roommate = new LinkedList<>();
				roommate.add(random);

					for(int j=0; j<a-1; j++) {
						int roommateNum = getFirstMeet(random, empSet, week, j);
						empSet.add(roommateNum);
						list.get(roommateNum).getAssignedRoom().add(a+"인실 "+ (i+1) +"번방");
						roommate.add(roommateNum);
						Iterator it =roommate.iterator();
						while(it.hasNext()) {
							int member = (int) it.next();
							check[member][roommateNum] = true;
							check[roommateNum][member] = true;
						}

						random = roommateNum;
					}
			}
			
		}else if(opt.size()<5) {
			int a = opt.get(0).intValue();
			int b = opt.get(1).intValue();
			int c = opt.get(2).intValue();
			int d = opt.get(3).intValue();
			
			Set<Integer> empSet = new HashSet<>();
			for(int i=0; i<b+d; i++) {
				int random = getRandomNum(empSet);
				empSet.add(random);
				if(i<b) {
					list.get(random).getAssignedRoom().add(a+"인실 "+ (i+1) +"번방");
				}
				else {
					list.get(random).getAssignedRoom().add(c+"인실 "+ (i+1) +"번방");
				}
				Queue<Integer> roommate = new LinkedList<>();
				roommate.add(random);
				if(i<b) {
					for(int j=0; j<a-1; j++) {
						int roommateNum = getFirstMeet(random, empSet, week, j);
						empSet.add(roommateNum);
						list.get(roommateNum).getAssignedRoom().add(a+"인실 "+ (i+1) +"번방");
						roommate.add(roommateNum);
						Iterator it =roommate.iterator();
						while(it.hasNext()) {
							int member = (int) it.next();
							check[member][roommateNum] = true;
							check[roommateNum][member] = true;
						}

						random = roommateNum;
					}
				} else {
					for(int j=0; j<c-1; j++) {
						int roommateNum = getFirstMeet(random, empSet, week, j+11);
						empSet.add(roommateNum);
						list.get(roommateNum).getAssignedRoom().add(c + "인실 "+ (i+1) +"번방");
						roommate.add(roommateNum);
						Iterator it =roommate.iterator();
						while(it.hasNext()) {
							int member = (int) it.next();
							check[member][roommateNum] = true;
							check[roommateNum][member] = true;
						}
						random = roommateNum;
					}
				}
				
			}
		}else if(opt.size()<7) {
			int a = opt.get(0).intValue();
			int b = opt.get(1).intValue();
			int c = opt.get(2).intValue();
			int d = opt.get(3).intValue();
			int e = opt.get(4).intValue();
			int f = opt.get(5).intValue();
			
			Set<Integer> empSet = new HashSet<>();
			for(int i=0; i<b+d+f; i++) {
				int random = getRandomNum(empSet);
				empSet.add(random);
				if(i<b) {
					list.get(random).getAssignedRoom().add(a+"인실 "+ (i+1) +"번방");
				}
				else if(i<b+d){
					list.get(random).getAssignedRoom().add(c+"인실 "+ (i+1) +"번방");
				} else {
					list.get(random).getAssignedRoom().add(e+"인실 "+ (i+1) +"번방");
				}
				Queue<Integer> roommate = new LinkedList<>();
				roommate.add(random);
				if(i<b) {
					for(int j=0; j<a-1; j++) {
						int roommateNum = getFirstMeet(random, empSet, week, j);
						empSet.add(roommateNum);
						list.get(roommateNum).getAssignedRoom().add(a+"인실 "+ (i+1) +"번방");
						roommate.add(roommateNum);
						Iterator it =roommate.iterator();
						while(it.hasNext()) {
							int member = (int) it.next();
							check[member][roommateNum] = true;
							check[roommateNum][member] = true;
						}

						random = roommateNum;
					}
				} else if(i<b+d){
					for(int j=0; j<c-1; j++) {
						int roommateNum = getFirstMeet(random, empSet, week, j+11);
						empSet.add(roommateNum);
						list.get(roommateNum).getAssignedRoom().add(c + "인실 "+ (i+1) +"번방");
						roommate.add(roommateNum);
						Iterator it =roommate.iterator();
						while(it.hasNext()) {
							int member = (int) it.next();
							check[member][roommateNum] = true;
							check[roommateNum][member] = true;
						}
						random = roommateNum;
					}
				} else {
					for(int j=0; j<e-1; j++) {
						int roommateNum = getFirstMeet(random, empSet, week, j+11);
						empSet.add(roommateNum);
						list.get(roommateNum).getAssignedRoom().add(e + "인실 "+ (i+1) +"번방");
						roommate.add(roommateNum);
						Iterator it =roommate.iterator();
						while(it.hasNext()) {
							int member = (int) it.next();
							check[member][roommateNum] = true;
							check[roommateNum][member] = true;
						}
						random = roommateNum;
				}
				
			}
			}
		}
		
		
	}
	
	//0~사이즈
	public int getRandomNum(Set<Integer> empSet) {
		int random = (int)(Math.random()*empSize);
		if(empSet.contains(random)) {
			int temp = random;
			while(++temp%empSize != random) {
				temp %= empSize;
				if(!empSet.contains(temp)) {
					return temp;
				}
			}
			if(temp == random) {
				System.out.println("직원 다 돌렸음");
			}
		}
		return random;
	}

	public int getFirstMeet(int person, Set<Integer> empSet, int week, int roomnum) {
		int random = (int)(Math.random()*empSize);
		if(check[person][random] == true || empSet.contains(random)) {
			int temp = random;
			int meetTemp = 0;
			if(!empSet.contains(random)) {
				meetTemp = random;
			}
			while(++temp%empSize != random) {
				temp %= empSize;
				if(check[person][temp] == false && !empSet.contains(temp)) {
					return temp;
				} else if(temp!=person && !empSet.contains(temp)) {
					meetTemp = temp;
				}
			}
			if(temp == random) {
				System.out.println(week + " 경우의 수 없음 +  " +temp +"   " +roomnum);
				return meetTemp;
			}
		}
		
		return random;
	}
	
	public void check() {
		for(int i=0; i<empSize; i++) {
			for(int j=0; j<empSize; j++) {
				System.out.print(check[i][j] + "   "); 
			}
			System.out.println();
		}
	}
}
