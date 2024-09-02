import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Class to represent a course
class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private int enrolledStudents;
    private String schedule;

    // Constructor to initialize the course details
    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolledStudents = 0;
    }

    // Getter methods for course details
    public String getCourseCode() {
        return courseCode;
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

    public int getAvailableSlots() {
        return capacity - enrolledStudents;
    }

    public String getSchedule() {
        return schedule;
    }

    // Method to enroll a student in the course
    public boolean enrollStudent() {
        if (enrolledStudents < capacity) {
            enrolledStudents++;
            return true;
        }
        return false;
    }

    // Method to drop a student from the course
    public boolean dropStudent() {
        if (enrolledStudents > 0) {
            enrolledStudents--;
            return true;
        }
        return false;
    }

    // Method to display course details
    public void displayCourseDetails() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Schedule: " + schedule);
        System.out.println("Capacity: " + capacity);
        System.out.println("Available Slots: " + getAvailableSlots());
    }
}

// Class to represent a student
class Student {
    private String studentID;
    private String name;
    private ArrayList<Course> registeredCourses;

    // Constructor to initialize the student details
    public Student(String studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    // Getter methods for student details
    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    // Method to register a course for the student
    public void registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            System.out.println("You are already registered for this course.");
        } else if (course.enrollStudent()) {
            registeredCourses.add(course);
            System.out.println("Successfully registered for " + course.getTitle());
        } else {
            System.out.println("Course is full. Registration failed.");
        }
    }

    // Method to drop a course for the student
    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println("Successfully dropped " + course.getTitle());
        } else {
            System.out.println("You are not registered for this course.");
        }
    }

    // Method to display registered courses for the student
    public void displayRegisteredCourses() {
        System.out.println("Registered Courses for " + name + " (ID: " + studentID + "):");
        for (Course course : registeredCourses) {
            System.out.println("- " + course.getTitle() + " (" + course.getCourseCode() + ")");
        }
    }
}

// Main class to run the Student Course Registration System
public class StudentCourseRegistrationSystem {

    // Course database to store and manage courses
    private static HashMap<String, Course> courseDatabase = new HashMap<>();

    // Student database to store and manage students
    private static HashMap<String, Student> studentDatabase = new HashMap<>();

    public static void main(String[] args) {
        // Initialize some sample courses
        courseDatabase.put("CS101", new Course("CS101", "Introduction to Computer Science", "Basic concepts of computer science.", 30, "Mon/Wed 10:00-11:30 AM"));
        courseDatabase.put("MATH101", new Course("MATH101", "Calculus I", "Introduction to differential and integral calculus.", 25, "Tue/Thu 09:00-10:30 AM"));
        courseDatabase.put("ENG101", new Course("ENG101", "English Literature", "Study of classic and modern literature.", 20, "Mon/Wed 01:00-02:30 PM"));

        // Initialize some sample students
        studentDatabase.put("S001", new Student("S001", "Alice Johnson"));
        studentDatabase.put("S002", new Student("S002", "Bob Smith"));
        studentDatabase.put("S003", new Student("S003", "Sayan Chakraborty"));
        studentDatabase.put("S004", new Student("S004", "Swastik Roy Choudhury"));

        // Start the registration system
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Student Course Registration System ---");
            System.out.println("1. Display Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Display Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option (1-5): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAvailableCourses();
                    break;
                case 2:
                    registerCourse(scanner);
                    break;
                case 3:
                    dropCourse(scanner);
                    break;
                case 4:
                    displayRegisteredCourses(scanner);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 5.");
            }
        }

        scanner.close();
    }

    // Method to display all available courses
    private static void displayAvailableCourses() {
        System.out.println("\nAvailable Courses:");
        for (Course course : courseDatabase.values()) {
            System.out.println("----------------------------------");
            course.displayCourseDetails();
        }
    }

    // Method to handle student course registration
    private static void registerCourse(Scanner scanner) {
        System.out.print("Enter your Student ID: ");
        String studentID = scanner.next();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to register: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.registerCourse(course);
    }

    // Method to handle course dropping
    private static void dropCourse(Scanner scanner) {
        System.out.print("Enter your Student ID: ");
        String studentID = scanner.next();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter Course Code to drop: ");
        String courseCode = scanner.next();
        Course course = courseDatabase.get(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        student.dropCourse(course);
    }

    // Method to display the courses a student is registered for
    private static void displayRegisteredCourses(Scanner scanner) {
        System.out.print("Enter your Student ID: ");
        String studentID = scanner.next();
        Student student = studentDatabase.get(studentID);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        student.displayRegisteredCourses();
    }
}