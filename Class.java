public class Class {
    private final int classId;
    private final int groupId;
    private final String courseCode;
    private int teacherId;
    private int roomId;
    private int period;
    private int semester;
    
    public Class(int classId, int groupId, String courseCode){
        this.classId = classId;
        this.courseCode = courseCode;
        this.groupId = groupId;
    }
    
    public String infoAsString() {
        return courseCode + ";" + roomId;
    }
    
    public void setTeacherId(int teacherId){
        this.teacherId = teacherId;
    }
    
    public void setRoomId(int roomId) {
     this.roomId = roomId;
    }
    
    public void setPeriod(int period) {
     this.period = period;
    }
    
    public void setSemester(int semester) {
     this.semester = semester;
    }   
    
    public int getClassId(){
        return this.classId;
    }
    
    public int getGroupId(){
        return this.groupId;
    }
    
    public String getCourseId(){
        return this.courseCode;
    }

    public int getTeacherId(){
        return this.teacherId;
    }
    
    public int getRoomId(){
        return this.roomId;
    }
    
    public int getPeriod(){
        return this.period;
    }
    
    public int getSemester() {
     return this.semester;
    }
    
    
}
