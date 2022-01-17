import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static HashMap<String, Course> courseList = new HashMap<>();
    private static HashMap<String, Room> roomList = new HashMap<>();
    private static ArrayList<Teacher> teacherList = new ArrayList<>();
    private static ArrayList<Group> groupList = new ArrayList<>();
    private static int studentCount = 0;
    private static int courseCount = 0;

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader("StudentDataObfuscated.csv", "CourseInfo.csv", "Room Utilization.csv", "FakeTeacherList.csv");
        studentList = csvReader.getStudentList();
        courseList = csvReader.getCourseList();
        roomList = csvReader.getRoomList();
        teacherList = csvReader.getTeacherList();

        //genetic algorithm 
        Timetable timetable = initializeTimetable(); 
        timetable.printTimetable();
        Algorithm alg = new Algorithm(100, 0.01, 0.9, 2, 5);
        Population population = alg.initPopulation(timetable);
        
        //evaluate population
        alg.sumFitness(population, timetable);
        
        int generation = 1;
        
        //evolution loop
        //THIS ENTIRE SECTION CURRENTLY DOES NOT WORK. WE FIRST HAVE TO WORK ON FITNESS ALG.
        System.out.println(alg.isMaxFit(population));
        while (alg.isMaxFit(population) == false) {
            //print fitness
            System.out.println("G" + generation + " Best fitness: " + population.getFittest(0).getFitness());

            //apply crossover
            population = alg.crossoverPopulation(population);

            //apply mutation
            population = alg.mutatePopulation(population, timetable);

            //reevaluate population
            alg.sumFitness(population, timetable);

            //increment the current generation
            generation++;
        }
        
        System.out.println("NO ERRORS");
        
 }
    public static Timetable initializeTimetable() {
        Timetable timetable = new Timetable();
     
        //iniatialize rooms
        for (String roomNum: roomList.keySet()) {
            timetable.addRoom(roomList.get(roomNum).getRoomId(), roomNum, roomList.get(roomNum).getRoomName());
        }
        //intialize teachers
        for (Teacher teacher : teacherList) {
            timetable.addTeacher(teacher.getTeacherId(), teacher.getTeacherName(), teacher.getQualifications());
        }        
        //intialize students and courses
        for (int i = 0; i < studentList.size(); i++) {
            Student student = studentList.get(i);
            addToGroup(student);
            timetable.addStudent(i, studentList.get(i));            
        }
        for (String courseCode : courseList.keySet()) {
            timetable.addCourse(courseCode, courseList.get(courseCode));
        }
        //initialize courses
        for (Group group : groupList) {
            timetable.addGroup(group.getGroupId(), group.getCourseCode());
        }
        return timetable;
    }
    
    public static void addToGroup(Student student) {
        for (String course : student.getCourses()) {
            if (!courseList.containsKey(course)) {
                return;
            }
            int groupIndex = availableGroupIndex(course);
            if ( groupIndex >= 0) {
                groupList.get(groupIndex).addStudent(student.getId());
            } else {
                groupList.add(new Group(groupList.size() + 1, course));
            }
        }
    }

    public static int availableGroupIndex(String courseCode) {
        int maxGroupSize = courseList.get(courseCode).getCap();
        for (int i = 0; i < groupList.size(); i++) {
            Group group = groupList.get(i);
            if (group.getCourseCode().equals(courseCode) && group.getGroupSize() < maxGroupSize) {
                return i;
            }
        }
        return -1;
    }
    
}