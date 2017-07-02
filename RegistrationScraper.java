import java.net.*;
import java.util.*;
import java.io.*;

public class RegistrationScraper {
    final String REGISTRATION_SEARCH_PAGE = "https://sunspot.sdsu.edu/schedule/search?mode=search";
    private URL Registration_URL;
    protected String parameters;
    protected boolean departmentSet = false;
    protected String departmentSingle = "";




    List<String> course = new ArrayList<String>(), section = new ArrayList<String>(), scheduleNumber = new ArrayList<String>(),
    courseTitle = new ArrayList<String>(), units = new ArrayList<String>(), format = new ArrayList<String>(), time = new ArrayList<String>(), 
    day = new ArrayList<String>(), location = new ArrayList<String>(), instructor = new ArrayList<String>(), seatsOpen = new ArrayList<String>();
    
    private int sectionMeetingCounter = 0;


    /*******TEMPORARY ****************/
    public String getParameters() {
        return this.parameters;
    }

    public void printArrListSizes() {
        System.out.println();
        System.out.println("Courses: " + course.size());
        System.out.println("Sections: " + section.size());
        System.out.println("ScheduleNumbers: " + scheduleNumber.size());
        System.out.println("CourseTitles: " + courseTitle.size());
        System.out.println("Unitss: " + units.size());
        System.out.println("Formats: " + format.size());
        System.out.println("Times: " + time.size());
        System.out.println("Days: " + day.size());
        System.out.println("Locations: " + location.size());
        System.out.println("Instructors: " + instructor.size());
        System.out.println("SeatsOpens: " + seatsOpen.size());

    }
    /*******TEMPORARY ****************/
    public RegistrationScraper() {
    }

    public void iterateOne() throws Exception {
        if(departmentSet) {
            this.parseDepartmentHTML(departmentSingle);
        }
        else {
            System.out.println("ERROR: You have not set the department you want to scrape.");
            System.out.println("Type 'HELP' for a list of options and commands.");
        }
    }

    public void iterateAll() throws Exception {

        DepartmentScraper departments = new DepartmentScraper();

        System.out.println("Scraping...");

        String department = "";
        for(Map.Entry<String, String> entry : departments.getDepartmentMap().entrySet() ) {
            department = entry.getKey();

            this.parseDepartmentHTML(department);

        }
    }

    public void parseDepartmentHTML(String department) throws Exception {

        setDepartmentSearch(department);

        System.out.println(Registration_URL.toString());

        
        try {

            BufferedReader in = new BufferedReader(new InputStreamReader(Registration_URL.openStream()));
            
            String inputLine, value;

            while((inputLine = in.readLine()) != null) {

                updateCount(inputLine);
                
                if(inputLine.contains("<a href=\"sectiondetails") && !inputLine.contains("footnote")) {
                    
                    int indexStart = inputLine.indexOf("\">") + 2;
                    int indexEnd = inputLine.indexOf("</a>");
                    value = inputLine.substring(indexStart, indexEnd);
                    this.course.add(value);
                }

                if(inputLine.contains("sectionFieldSec")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Sec"))
                        this.section.add(value);
                }

                if(inputLine.contains("sectionFieldSched")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Sched #"))
                        this.scheduleNumber.add(value);
                }

                if(inputLine.contains("sectionFieldTitle")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Course Title"))   
                        this.courseTitle.add(value);
                }
                if(inputLine.contains("sectionFieldUnits")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Units"))
                        this.units.add(value);
                }
                if(inputLine.contains("sectionFieldType")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Format"))
                        this.format.add(value);
                }
                if(inputLine.contains("sectionFieldTime")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Time"))
                        this.time.add(value);
                }
                if(inputLine.contains("sectionFieldDay")) {
                    value = parseSection(inputLine);
                    if(!value.equals("Day"))
                        this.day.add(value);
                }
                if(inputLine.contains("sectionFieldLocation") && !inputLine.contains(">Location<")) {
                    if((inputLine = in.readLine()).contains("<a")){
                        updateCount(inputLine);
                        inputLine = in.readLine(); // the HTML data is on the 3rd line due to inconsistent formatting on WebPortal
                        updateCount(inputLine);
                        value = inputLine.trim();
                        this.location.add(value);
                    }
                    else {
                        inputLine = in.readLine();
                        updateCount(inputLine);
                        value = inputLine.trim();
                        this.location.add(value);
                        
                    }
                }
                if(inputLine.contains("sectionFieldSeats") && !inputLine.contains(">Seats Open<")) {
                    boolean seatsFound = false;
                    while(!seatsFound) {
                        inputLine = in.readLine();
                        if(inputLine.contains("Waitlisted")) {
                            inputLine = inputLine.trim();
                            int indexStart = 0;
                            int indexEnd = inputLine.indexOf("<br>");
                            value = inputLine.substring(indexStart, indexEnd);
                            this.seatsOpen.add(value);
                            seatsFound = true;
                        }
                        else if(inputLine.contains("/") && !(inputLine.contains("<"))) {
                            value = inputLine.trim();
                            this.seatsOpen.add(value);
                            seatsFound = true;
                        }
                    }
                }
                
                if(inputLine.contains("sectionFieldInstructor") && !inputLine.contains(">Instructor<")) {
                    boolean instructorFound = false;
                    for(int i = 0; i < 3; i++) {
                        inputLine = in.readLine();
                        updateCount(inputLine);
                        if(inputLine.contains("<a href=\"search?mode=search&instructor")) {    
                            int indexStart = inputLine.indexOf("\">") + 2;
                            int indexEnd = inputLine.indexOf("</a>");
                            value = inputLine.substring(indexStart, indexEnd);
                            if(!value.equals("Instructor")) { 
                                instructorFound = true;
                                this.instructor.add(value);
                            }
                        }
                    }
                    if(!instructorFound)
                        this.instructor.add("");
                }
            }
        }
        catch(NullPointerException e) {
            System.out.println(Registration_URL.toString() + "  <-- No courses for this department in selected period");
        }
    }


    public void updateCount(String inputLine) {

        // Accounts for courses with a Lecture and Activity class
        if(inputLine.contains("sectionRecordEven") || inputLine.contains("sectionRecordOdd")) {
            this.sectionMeetingCounter = 0;
        }   
        
        // Handles multiple locations, teachers, times, etc. per one class
        if(inputLine.contains("sectionMeeting")) { 
            this.sectionMeetingCounter++;
            if((this.sectionMeetingCounter) >= 2) {
                this.course.add("");
                this.seatsOpen.add("");
            }
        }     
    }

    public void printDepartmentCourses() {
        printDepartmentCoursesHeader();
        for(int i = 0; i < course.size(); i++) {
            System.out.printf("%-10s", this.course.get(i));
            System.out.printf("%-5s", this.section.get(i));
            System.out.printf("%-10s", this.scheduleNumber.get(i));
            System.out.printf("%-30s", this.courseTitle.get(i));
            System.out.printf("%-8s", this.units.get(i));
            System.out.printf("%-18s", this.format.get(i));
            System.out.printf("%-12s", this.time.get(i));
            System.out.printf("%-10s", this.day.get(i));
            System.out.printf("%-12s", this.location.get(i));
            System.out.printf("%-28s", this.instructor.get(i));
            System.out.printf("%-8s", this.seatsOpen.get(i));

            System.out.println();    
        }
    }

    public void printDepartmentCoursesHeader() {
            System.out.println();
            System.out.printf("%-10s", "Course");
            System.out.printf("%-5s", "Sec");
            System.out.printf("%-10s", "Sched #");
            System.out.printf("%-30s", "Title");
            System.out.printf("%-8s", "Units");
            System.out.printf("%-18s", "Format");
            System.out.printf("%-12s", "Time");
            System.out.printf("%-10s", "Day");
            System.out.printf("%-12s", "Location");
            System.out.printf("%-28s", "Instructor");
            System.out.printf("%-8s", "Seats Open");
            System.out.println(); 
            System.out.println("-----------------------------------------------------------------" +
            "----------------------------------------------------------------------------------------");
    }
    public String parseSection(String inputLine) {
        int indexStart = inputLine.indexOf("column\">") + 8;
        int indexEnd = inputLine.indexOf("</div>");
        String value = inputLine.substring(indexStart, indexEnd);
        return value;
    }

    public void setDepartmentSearch() throws MalformedURLException {
        String formURL = "";
        if(this.parameters != null) 
            formURL = REGISTRATION_SEARCH_PAGE + parameters;
        else 
            formURL = REGISTRATION_SEARCH_PAGE;
        String searchURL = formatURL(formURL);
        this.Registration_URL = new URL(formURL);
    }
    public void setDepartmentSearch(String department) throws MalformedURLException {
        String formURL = "";
        if(this.parameters != null) 
            formURL = REGISTRATION_SEARCH_PAGE + "&abbrev=" + department + parameters;
        else 
            formURL = REGISTRATION_SEARCH_PAGE + "&abbrev=" + department;
        String searchURL = formatURL(formURL);
        this.Registration_URL = new URL(searchURL);
    }

    public String formatURL(String url) {
        StringBuilder newURL = new StringBuilder();
        
        for(int i = 0; i < url.length(); i++) {
            if(url.charAt(i) == ' ')
                newURL.append('+');
            else 
                newURL.append(url.charAt(i));
        }
        return newURL.toString();
    }
}