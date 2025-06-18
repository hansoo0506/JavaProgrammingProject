import java.io.*;
import java.util.*;
import javax.swing.*;

public class GradeManager {
	private List<Student> students = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<Student> getStudents() {
        return students;
    }

    public void inputScoresOrLoad() {
        File file = new File("student_scores.txt");
        if (file.exists()) {
            System.out.println("기존 성적 파일을 불러옵니다");
            importScoresFromFile();
        } else {
            System.out.println("기존 파일이 없습니다. 새로 입력해 주십시오");
            inputScoresGUI();
        }
    }

    public void inputScoresGUI() {
        for (int i = 1; i <= 10; i++) {
            try {
            	String name = JOptionPane.showInputDialog("[" + i + "번 학생] 이름:");
            	int mid = Integer.parseInt(JOptionPane.showInputDialog("중간고사 점수:"));
            	int finalExam = Integer.parseInt(JOptionPane.showInputDialog("기말고사 점수:"));
            	int assignment = Integer.parseInt(JOptionPane.showInputDialog("과제 점수:"));
            	int attendance = Integer.parseInt(JOptionPane.showInputDialog("출석 점수:"));

            	Student s = new Student(i, name, mid, finalExam, assignment, attendance);
            	students.add(s);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "잘못된 입력입니다. 다시 시도하세요.");
                i--; // 현재 학생 다시 입력
            }
        }
        exportScoresToFile();
    }

    public void importScoresFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("student_scores.txt"))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null && index < 10) {
                String[] parts = line.split("[|:]");

                int id = Integer.parseInt(parts[1].trim());
                String name = parts[3].trim();
                int mid = Integer.parseInt(parts[5].trim());
                int finalExam = Integer.parseInt(parts[7].trim());
                int assignment = Integer.parseInt(parts[9].trim());
                int attendance = Integer.parseInt(parts[11].trim());
                int finalScore = Integer.parseInt(parts[13].trim());

                Student s = new Student(id, name, mid, finalExam, assignment, attendance);
                s.setFinalScore(finalScore);

                students.add(s);
                index++;  
            }
            System.out.println("성공적으로 불러왔습니다.");
        } catch (IOException e) {
            System.out.println("불러오기 중 오류 발생: " + e.getMessage());
        }
    }

    public void exportScoresToFile() {
        try (FileWriter writer = new FileWriter("student_scores.txt")) {
            for (Student s : students) {
            	String line = String.format(
            		    "ID: %d | Name: %s | Mid: %d | Final: %d | Assignment: %d | Attendance: %d | Final Score: %d\n",
            		    s.getId(), s.getName(), s.getMid(), s.getFinalExam(), s.getAssignment(), s.getAttendance(), s.getFinalScore()
            		);

                writer.write(line);
            }
            System.out.println("student_scores.txt 파일로 저장되었습니다.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
