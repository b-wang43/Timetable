import java.util.ArrayList;
import java.util.HashMap;

public class Group {
    private int groupId;
    private int groupSize;
    private final String courseCode;
    private boolean isSingleSection;
    private ArrayList<Integer> studentIds = new ArrayList<>();
    private int cap;
    
    public Group(int groupId, String courseCode, int cap) {
        this.groupId = groupId;
        this.isSingleSection = false;
        this.courseCode = courseCode;
        this.groupSize = 0;
        this.cap = cap;
    }
    
    public int getId() {
        return this.groupId;
    }
    
    public int getGroupSize() {
        return this.groupSize;
    }
    
    public String getCourseCode() {
        return this.courseCode;
    }
    
    public boolean getIsSingleSection() {
        return this.isSingleSection;
    }
    
    public void addStudent(int studentId) {
        studentIds.add(studentId);
        groupSize++;
    }
    
    public ArrayList<Integer> getStudentIds() {
        return this.studentIds;
    }
    
    public boolean isFull() {
        return groupSize >= cap;
    }
    
    public int getCap() {
        return this.cap;
    }
    
    public void setId(int id) {
        this.groupId = id;
    }
    
    public void setSingleSection() {
        this.isSingleSection = true;
    }
    
    public int findGroupGrade(HashMap<Integer, Student> studentList) {
        HashMap<Integer, Integer> numGradeOccurrences = new HashMap<>();
        for (Integer studentId : studentIds) {
            int studentGrade = studentList.get(studentId).getGrade();
            if (numGradeOccurrences.containsKey(studentGrade)) {
                numGradeOccurrences.put(studentGrade, numGradeOccurrences.get(studentGrade) + 1);
            } else {
                numGradeOccurrences.put(studentGrade, 1);
            }
        }
        int best = -1;
        for (Integer grade : numGradeOccurrences.keySet()) {
            if (best == -1) {
                best = grade;
            }
            if (numGradeOccurrences.get(grade) > numGradeOccurrences.get(best)) {
                best = grade;
            }
        }
        return best;
    }
    
}