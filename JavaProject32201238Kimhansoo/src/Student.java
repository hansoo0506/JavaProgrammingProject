
public class Student {
	private int id;   		 // 학생번호(1 ~ 10)
	private String name;	 // 이름
	private int mid;         // 중간고사
	private int finalExam;   // 기말고사
	private int assignment;  // 과제
	private int attendance;  // 출석
	private int finalScore;  // 최종 성적 
	private int rank;        // 등수
	
	public Student() {}
	
	public Student(int id, String name, int mid, int finalExam, int assignment, int attendance) {
		this.id = id;
		this.name = name;
		this.mid = mid;
		this.finalExam = finalExam;
		this.assignment = assignment;
		this.attendance = attendance;
		this.finalScore = calculateFinal();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getFinalExam() {
		return finalExam;
	}
	public void setFinalExam(int finalExam) {
		this.finalExam = finalExam;
	}
	public int getAssignment() {
		return assignment;
	}
	public void setAssignment(int assignment) {
		this.assignment = assignment;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	public int getFinalScore() {
		return finalScore;
	}
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public int calculateFinal() {
		int finalScore = 0;
		finalScore = (int)(0.3*mid + 0.3*finalExam + 0.3*assignment + 0.1*attendance);
		return finalScore;
	}
}
