package self.kiwi.test;

import self.kiwi.dao.MemberDAO;
import self.kiwi.model.GenericMember;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tester().addUser();

	}
	
	public void addUser(){
		GenericMember newMember = new GenericMember("111", "zcw");
		try{
			MemberDAO memberDAO = new MemberDAO();
			memberDAO.insertNewMember(newMember);
			memberDAO.destroy();
		} catch (Exception e){
			e.printStackTrace();
		} 
	}

}
