import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private int enrolled;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.enrolled = 0;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public String getSchedule() {
        return schedule;
    }

    public boolean enrollStudent() {
        if (enrolled < capacity) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean dropStudent() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    public boolean hasSlots() {
        return enrolled < capacity;
    }
}

class Student {
    private String id;
    private String name;
    private List<Course> courses;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}

public class CourseRegistrationSystem {
    private static HashMap<String, Course> courses = new HashMap<>();
    private static HashMap<String, Student> students = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        initializeStudents();

        while (true) {
            displayMenu();
        }
    }

    private static void initializeCourses() {
        courses.put("CS101", new Course("CS101", "Intro to Computer Science", "Basics of CS", 2, "Mon 9-11"));
        courses.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to Calculus", 2, "Wed 10-12"));
    }

    private static void initializeStudents() {
        students.put("S001", new Student("S001", "Alice"));
        students.put("S002", new Student("S002", "Bob"));
    }

    private static void displayMenu() {
        System.out.println("\n1. List Courses");
        System.out.println("2. Register for Course");
        System.out.println("3. Drop Course");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                listCourses();
                break;
            case 2:
                registerForCourse();
                break;
            case 3:
                dropCourse();
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void listCourses() {
        for (Course course : courses.values()) {
            System.out.println(course.getCode() + ": " + course.getTitle() + " (" + course.getEnrolled() + "/" + course.getCapacity() + ")");
        }
    }

    private static void registerForCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (course.hasSlots() && course.enrollStudent()) {
            student.addCourse(course);
            System.out.println("Successfully registered for the course.");
        } else {
            System.out.println("Course is full or could not register.");
        }
    }

    private static void dropCourse() {
        System.out.print("Enter student ID: ");
        String studentId = scanner.next();
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter course code: ");
        String courseCode = scanner.next();
        Course course = courses.get(courseCode);
        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        if (student.getCourses().contains(course)) {
            course.dropStudent();
            student.removeCourse(course);
            System.out.println("Successfully dropped the course.");
        } else {
            System.out.println("You are not registered for this course.");
        }
    }
}
