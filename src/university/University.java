package university;
import java.util.logging.Logger;

import javax.print.DocFlavor.STRING;

/**
 * This class represents a university education system.
 * 
 * It manages students and courses.
 *
 */
public class University {
	private String name;
	private String RectorFirst;
	private String RectorLast;
	private int studentsCnt = 0;
	private int coursesCnt = 0;
	private int examsCnt = 0;
	private Student[] students = new Student[1000];
	private Course[] courses = new Course[50];

	

	
	
// R1
	/**
	 * Constructor
	 * @param name name of the university
	 */
	public University(String name){
		// Example of logging
		// logger.info("Creating extended university object");
		//TODO: to be implemented
		this.name = name;
	}
	
	/**
	 * Getter for the name of the university
	 * 
	 * @return name of university
	 */
	public String getName(){
		
		//TODO: to be implemented

		return this.name;
	}
	
	/**
	 * Defines the rector for the university
	 * 
	 * @param first first name of the rector
	 * @param last	last name of the rector
	 */
	public void setRector(String first, String last){
		//TODO: to be implemented
		this.RectorFirst = first;
		this.RectorLast = last;
	}
	
	/**
	 * Retrieves the rector of the university with the format "First Last"
	 * 
	 * @return name of the rector
	 */
	public String getRector(){
		//TODO: to be implemented
		return RectorFirst+" "+RectorLast;
	}
	
// R2
	/**
	 * Enrol a student in the university
	 * The university assigns ID numbers 
	 * progressively from number 10000.
	 * 
	 * @param first first name of the student
	 * @param last last name of the student
	 * 
	 * @return unique ID of the newly enrolled student
	 */
	public int enroll(String first, String last){
		//TODO: to be implemented
		int id = 10000 + studentsCnt;
		name = first + " " + last; 
		Student student = new Student(name, id);

		students[studentsCnt++] = student;
		logger.info("New student enrolled: " + id + ", " + first + " " + last);
		return id;
	}
	
	/**
	 * Retrieves the information for a given student.
	 * The university assigns IDs progressively starting from 10000
	 * 
	 * @param id the ID of the student
	 * 
	 * @return information about the student
	 */
	public String student(int id){
		//TODO: to be implemented
	    int StudentIndex = id - 10000;
		return id +" "+ students[StudentIndex].getName();
	}
	
// R3
	/**
	 * Activates a new course with the given teacher
	 * Course codes are assigned progressively starting from 10.
	 * 
	 * @param title title of the course
	 * @param teacher name of the teacher
	 * 
	 * @return the unique code assigned to the course
	 */
	public int activate(String title, String teacher){
		//TODO: to be implemented
		int code = 10 + coursesCnt;

		Course course = new Course(title, teacher, code);

		courses[coursesCnt++] = course;
		logger.info("New course activated: " + code + ", " + title + ", " + teacher);
		return code;
	}
	
	/**
	 * Retrieve the information for a given course.
	 * 
	 * The course information is formatted as a string containing 
	 * code, title, and teacher separated by commas, 
	 * e.g., {@code "10,Object Oriented Programming,James Gosling"}.
	 * 
	 * @param code unique code of the course
	 * 
	 * @return information about the course
	 */
	public String course(int code){
		//TODO: to be implemented
		int index = code-10;
		return courses[index].getCode()+courses[index].getTitle()+","+courses[index].getTeacher();
	}
	
// R4
	/**
	 * Register a student to attend a course
	 * @param studentID id of the student
	 * @param courseCode id of the course
	 */
      
	public void register(int studentID, int courseCode){
		//TODO: to be implemented
		int studentIndex = studentID - 10000;
		int courseIndex = courseCode - 10;

		students[studentIndex].addCourse(courses[courseIndex]);
		courses[courseIndex].AddStudent(students[studentIndex]);
		logger.info("Student " + studentID + " signed up for course " + courseCode);
	};
	
	/**
	 * Retrieve a list of attendees.
	 * 
	 * The students appear one per row (rows end with `'\n'`) 
	 * and each row is formatted as describe in in method {@link #student}
	 * 
	 * @param courseCode unique id of the course
	 * @return list of attendees separated by "\n"
	 */
	public String listAttendees(int courseCode){
		//TODO: to be implemented
		return courses[courseCode-10].getStudents();
	}

	/**
	 * Retrieves the study plan for a student.
	 * 
	 * The study plan is reported as a string having
	 * one course per line (i.e. separated by '\n').
	 * The courses are formatted as describe in method {@link #course}
	 * 
	 * @param studentID id of the student
	 * 
	 * @return the list of courses the student is registered for
	 */
	public String studyPlan(int studentID){
		//TODO: to be implemented
		String courses = "";
		courses = students[studentID-10000].getCourses();
		return courses;
	}

// R5
	/**
	 * records the grade (integer 0-30) for an exam can 
	 * 
	 * @param studentId the ID of the student
	 * @param courseID	course code 
	 * @param grade		grade ( 0-30)
	 */
	public void exam(int studentId, int courseID, int grade) {
		exam exam = new exam(studentId, courseID, grade);
		int studentIndex = studentId - 10000;
		int courseIndex = courseID - 10;

		students[studentIndex].addExam(exam);
		courses[courseIndex].addExam(exam);
		logger.info("Student " + studentId + " took an exam in course " + courseID + " with grade " + grade);
	}
    //3
	/**
	 * Computes the average grade for a student and formats it as a string
	 * using the following format 
	 * 
	 * {@code "Student STUDENT_ID : AVG_GRADE"}. 
	 * 
	 * If the student has no exam recorded the method
	 * returns {@code "Student STUDENT_ID hasn't taken any exams"}.
	 * 
	 * @param studentId the ID of the student
	 * @return the average grade formatted as a string.
	 */
	public String studentAvg(int studentId) {
		int ind = studentId - 10000;
		float avg;
		Student student = students[ind];
		avg = students[ind].getStudentAvg();
		if (student.getExamsCnt() == 0){
			return "Student " + studentId +" hasn't taken any exams";
		}
		
		return "Student " + studentId +  " : " + avg;
	}
	
	/**
	 * Computes the average grades of all students that took the exam for a given course.
	 * 
	 * The format is the following: 
	 * {@code "The average for the course COURSE_TITLE is: COURSE_AVG"}.
	 * 
	 * If no student took the exam for that course it returns {@code "No student has taken the exam in COURSE_TITLE"}.
	 * 
	 * @param courseId	course code 
	 * @return the course average formatted as a string
	 */
	public String courseAvg(int courseId) {
		int index = courseId-10;
		float avg;
		avg = courses[index].getCourseAvg();
		if (avg!=0){
			return "The average for the course "+ courses[index].getTitle() +"is: " +avg;
		}
		else{
			return "No student has taken the exam in "+ courses[index].getTitle();
		}
	}
	

// R6
	/**
	 * Retrieve information for the best students to award a price.
	 * 
	 * The students' score is evaluated as the average grade of the exams they've taken. 
	 * To take into account the number of exams taken and not only the grades, 
	 * a special bonus is assigned on top of the average grade: 
	 * the number of taken exams divided by the number of courses the student is enrolled to, multiplied by 10.
	 * The bonus is added to the exam average to compute the student score.
	 * 
	 * The method returns a string with the information about the three students with the highest score. 
	 * The students appear one per row (rows are terminated by a new-line character {@code '\n'}) 
	 * and each one of them is formatted as: {@code "STUDENT_FIRSTNAME STUDENT_LASTNAME : SCORE"}.
	 * 
	 * @return info on the best three students.
	 */
	public String topThreeStudents() {
		float first_score = -1;
		float second_score = -1;
		float third_score = -1;
		String result = "";
		float prev_score = -1;
		Student first_student = new Student();
		Student second_student = new Student();
		Student third_student = new Student();
		float score;

		for (int i = 0; i < studentsCnt; i++ ){
			score = students[i].getStudentAvg() + (students[i].getExamsCnt()/students[i].getCourseCnt())*10;
			if (score > first_score){
				if(first_score!=-1){
					prev_score = first_score;
				}

				first_score = score;
				first_student = students[i];
				
			}
			if(score > second_score || prev_score>second_score){

				if(prev_score==-1 && score != first_score){
				second_score = score;
				prev_score = score;
				}
				else if (prev_score != -1){
					second_score = prev_score;
					prev_score = -1;
					if(second_score!=-1){
						prev_score = second_score;
					}
				}
				
				second_student = students[i];
			}
			if (score > third_score && i>2 ){
				if(prev_score==-1 && (score!=first_score && score!=second_score)){
						third_score = score;
						third_student = students[i];
					}
				else if(prev_score!=-1){ 
						third_score = prev_score;
						third_student = students[i];		
					}
					
			}
		} 
		result = first_student.getName()  + " : " + first_score + "\n";
		if (second_score!=-1){ 
			result += second_student.getName()+" : " + second_score + "\n";}; 
		if (third_score != -1){	 
			result += third_student.getName() +" : " + third_score + "\n";
		}
		; 
		return result;
	}

// R7
    /**
     * This field points to the logger for the class that can be used
     * throughout the methods to log the activities.
     */
    public static final Logger logger = Logger.getLogger("University");

}



class Student{
	private String name;
	private int id;
	private Course[] courses = new Course[25];
	private int coursesCnt = 0;
	private exam[] exams = new exam[25];
	private int examsCnt = 0;
	public Student(){
	
 
	}
	//constructor
	public Student(String name,int id){
		this.name = name;
		this.id = id;
	}
	public void addCourse(Course course){
		courses[coursesCnt++] = course;
		return;
	}
	public String getName(){
		return this.name;
	}
	public int getId(){
		return this.id;
	}
	public String getCourses(){	
		String courses = "";
		String title;
		String teacher;
		int code;
		for (int i = 0; i < coursesCnt;i++){
			code = this.courses[i].getCode();
			title = this.courses[i].getTitle();
			teacher = this.courses[i].getTeacher();
			courses += code + "," + title + "," + teacher + "\n" ; 
		}
		return courses;
	}
	public void addExam(exam exam){
		exams[examsCnt++] = exam;
		return;
	}

	public float getStudentAvg(){
		float avg=0;
		float sum=0;
	
		for (int i = 0;i < examsCnt;i++){
			sum += exams[i].getGrade();			
		}
		avg = sum/examsCnt;
		return avg;
	}
	public int getExamsCnt() {
		return examsCnt;
	}
	public int getCourseCnt(){
		return coursesCnt;
	}
	
}

class Course{
	private String title;
	private String teacher;
	private int code;
	private Student[] students = new Student[100];
	private int studentsCnt = 0;
	private exam[] exams = new exam[100];
	private int examsCnt;

	public Course(){

	}
	//constructor
	public Course(String title,String teacher, int code){
		this.title = title;
		this.teacher = teacher;
		this.code = code;
	}
	public String getTitle(){
		return this.title;
	}
	public String getTeacher(){
		return this.teacher;
	}
	public int getCode(){
		return this.code;
	}
	public void AddStudent(Student student){
		students[studentsCnt++] = student;
		return;
	}
	public String getStudents(){
		String students = "";
		int id;
		String name;
		for (int i = 0; i < this.studentsCnt;i++){
			id = this.students[i].getId();
			name = this.students[i].getName();
			students += id + " " + name + "\n";
		}
		return students;
	}

	public void addExam(exam exam){
		exams[examsCnt++] = exam;
		return;
	}

	public float getCourseAvg(){
		float avg = 0;
		float sum = 0;
		if (examsCnt==0){
			return avg;
		}
		for (int i = 0; i<examsCnt;i++){
			sum += exams[i].getGrade();
		}
		avg = sum/examsCnt;
		return avg;
	}
	public int getExamsCnt() {
		return examsCnt;
	}

}
class exam{
	private int StudentId;
	private int CourseCode;
	private float grade;

	public exam(int StudentId,int CourseCode,int grade){
		this.StudentId = StudentId;
		this.CourseCode = CourseCode;
		this.grade = grade;
	}
	public int getId(){
		return this.StudentId;
	}
	public int getCode(){
		return this.CourseCode;
	}
	public float getGrade(){
		return this.grade;
	}

	
}