import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class GradeManagerGUI extends JFrame {
    private GradeManager gm;

    private DefaultTableModel fullScoreModel;
    private DefaultTableModel finalScoreModel;

    public GradeManagerGUI() {
        gm = new GradeManager();
        gm.inputScoresOrLoad();

        setTitle("성적 관리 시스템");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 탭 패널 생성
        JTabbedPane tabbedPane = new JTabbedPane();

        // 전체 성적 탭
        JPanel fullScorePanel = new JPanel(new BorderLayout());
        fullScoreModel = new DefaultTableModel(new Object[]{
            "ID", "Name", "Mid", "Final", "Assignment", "Attendance", "Final Score"
        }, 0);
        JTable fullScoreTable = new JTable(fullScoreModel);
        fullScorePanel.add(new JScrollPane(fullScoreTable), BorderLayout.CENTER);
        tabbedPane.addTab("전체 성적", fullScorePanel);

        // 최종 성적 탭
        JPanel finalScorePanel = new JPanel(new BorderLayout());
        finalScoreModel = new DefaultTableModel(new Object[]{
            "ID", "Name", "Final Score"
        }, 0);
        JTable finalScoreTable = new JTable(finalScoreModel);
        finalScorePanel.add(new JScrollPane(finalScoreTable), BorderLayout.CENTER);
        tabbedPane.addTab("최종 성적", finalScorePanel);

        // 학생 조회 탭
        JPanel studentLookupPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JLabel label = new JLabel("조회할 학생 번호:");
        JTextField inputField = new JTextField(5);
        JButton searchButton = new JButton("검색");
        inputPanel.add(label);
        inputPanel.add(inputField);
        inputPanel.add(searchButton);

        JTextArea outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        studentLookupPanel.add(inputPanel, BorderLayout.NORTH);
        studentLookupPanel.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("학생 조회", studentLookupPanel);

        add(tabbedPane);

        // 테이블 데이터 채우기
        loadFullScoreTable();
        loadFinalScoreTable();

        // 검색 버튼 이벤트 처리
        searchButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            try {
                int id = Integer.parseInt(input);
                if (id >= 1 && id <= gm.getStudents().size()) {
                    Student s = gm.getStudents().get(id - 1);
                    outputArea.setText(String.format(
                        "ID: %d | Name: %s | Mid: %d | Final: %d | Assignment: %d | Attendance: %d | Final Score: %d",
                        s.getId(), s.getName(), s.getMid(), s.getFinalExam(), s.getAssignment(), s.getAttendance(), s.getFinalScore()));
                } else {
                    JOptionPane.showMessageDialog(this, "잘못된 번호입니다.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "숫자를 입력해 주세요.");
            }
        });
    }

    private void loadFullScoreTable() {
        fullScoreModel.setRowCount(0);
        for (Student s : gm.getStudents()) {
            fullScoreModel.addRow(new Object[]{
                s.getId(), s.getName(), s.getMid(), s.getFinalExam(), s.getAssignment(), s.getAttendance(), s.getFinalScore()
            });
        }
    }

    private void loadFinalScoreTable() {
        finalScoreModel.setRowCount(0);
        for (Student s : gm.getStudents()) {
            finalScoreModel.addRow(new Object[]{
                s.getId(), s.getName(), s.getFinalScore()
            });
        }
    }
}
