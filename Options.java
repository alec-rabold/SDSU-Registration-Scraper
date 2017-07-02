import java.util.*;

public class Options extends RegistrationScraper {
    
    public Options() {
        RegistrationScraper scraper = new RegistrationScraper();
    }

    /**
     ***** OPTIONS CONTROLLER *****
     * 
     * The following methods all interact with the HTML GET method
     * and modify the iterated URL
     * 
     * All the methods are explained in the printOptions() method below
     * 
     */

    public void printOptions() {
        String indent = "   ";
        System.out.println();
        System.out.println("+--------------------------- COMMANDS AND OPTIONS LIST ---------------------------+");

        System.out.println();
        System.out.println("******* COMMANDS (scraper.[COMMAND]) *******");
        System.out.println("The following interact with the RegistrationScraper class");
        System.out.println();
        

        System.out.println();
        System.out.println("iterateAll() -->  Scrape every SDSU department's class and store the data [Recommended before: printDepartmentCourses()]");
        System.out.println(indent + "@usage iterateAll()");

        System.out.println();
        System.out.println("iterateOne() -->  Scrape ONE department's class and store the data [Recommended before: printDepartmentCourses()]");
        System.out.println(indent + "@usage iterateOne(\"CS\")");
        System.out.println(indent + "@param department");

        System.out.println();
        System.out.println("printDepartmentCourses() -->  Display a formatted list of every stored class so far");
        System.out.println(indent + "@usage printDepartmentCourses()");

        System.out.println();
        System.out.println("printArrListSizes() -->  Display the sizes of all the data (to make sure they're all the same length)");
        System.out.println(indent + "@usage printArrListSizes()");
        

        System.out.println();
        System.out.println("******* OPTIONS (custom.[COMMAND] *******");
        System.out.println("The following interact with the HTML GET method");
        System.out.println();
        

        System.out.println();
        System.out.println("setTerm() -->  Set the semester term you want to search");
        System.out.println(indent + "@usage setTerm(\"Summer\", \"2017\")");
        System.out.println(indent + "@param season");
        System.out.println(indent + indent + "options: Fall, Spring, Winter, Summer");
        System.out.println(indent + "@param year");
        System.out.println(indent + indent + "options: 2015, 2016, 2017, etc.");

        System.out.println();
        System.out.println("setDepartment() -->  Set a single department you wish to search (use with iterateOne())");
        System.out.println(indent + "@usage setDepartment(\"CS\")");
        System.out.println(indent + "@param department");
        System.out.println(indent + indent + "options: AMIND, BIOL, CS, etc.");

        System.out.println();
        System.out.println("setInstructor() -->  Set the course number you want to return (ex. all \"xx-108\" classes)");
        System.out.println(indent + "@usage setInstructor(\"Kraft\")");
        System.out.println(indent + "@param last name");

        System.out.println();
        System.out.println("setCourseNumber() -->  Set the course number you want to return (ex. all \"xx-108\" classes)");
        System.out.println(indent + "@usage setCourseNumber(\"108\")");
        System.out.println(indent + "@param number");

        System.out.println();
        System.out.println("setCourseNumber() -->  Set the course number AND suffic you want to return (ex. all \"xx-451A\" classes)");
        System.out.println(indent + "@usage setTerm(\"451\", \"A\")");
        System.out.println(indent + "@param number");
        System.out.println(indent + "@param suffix");
        System.out.println(indent + indent + "options: A, B, C, etc.");

        System.out.println();
        System.out.println("setScheduleNumber() -->  Set the specific class you want to return");
        System.out.println(indent + "@usage setScheduleNumber(\"20019\")");
        System.out.println(indent + "@param number");

        System.out.println();
        System.out.println("setUnits() -->  Set the specific number of units");
        System.out.println(indent + "@usage setUnits(\"3\")");
        System.out.println(indent + "@param units");

        System.out.println();
        System.out.println("setLocation() -->  Set the facility location of the class");
        System.out.println(indent + "@usage setUnits(\"GMCS\")");
        System.out.println(indent + "@param facility");

        System.out.println();
        System.out.println("setLocation() -->  Set the facility AND room location of the class");
        System.out.println(indent + "@usage setUnits(\"GMCS\", \"311\")");
        System.out.println(indent + "@param facility");
        System.out.println(indent + "@param room number");

        System.out.println();
        System.out.println("setServiceLearning() -->  Toggle the 'Service Learning' option [only show Service Learning classes]");
        System.out.println(indent + "@usage setServiceLearning(true)");
        System.out.println(indent + "@param true/false");

        System.out.println();
        System.out.println("setSpecialTopics() -->  Toggle the 'Special Topics' option [only show Special Topics classes]");
        System.out.println(indent + "@usage setSpecialTopics(true)");
        System.out.println(indent + "@param true/false");

        System.out.println();
        System.out.println("setHonors() -->  Toggle the 'Honors' option [only show Honors classes]");
        System.out.println(indent + "@usage setHonors(true)");
        System.out.println(indent + "@param true/false");
        
        System.out.println();
        System.out.println("setDistanceOnline() -->  Toggle the 'Distance Online' option [only show Online classes]");
        System.out.println(indent + "@usage setDistanceOnline(true)");
        System.out.println(indent + "@param true/false");

        System.out.println();
        System.out.println("setDistanceHybrid() -->  Toggle the 'Distance Hybrid' option [only show Hybrid classes]");
        System.out.println(indent + "@usage setDistanceHybrid(true)");
        System.out.println(indent + "@param true/false");

        System.out.println();
        System.out.println("setEvening() -->  Toggle the 'Evening' option [only show Evening classes]");
        System.out.println(indent + "@usage setEvening(true)");
        System.out.println(indent + "@param true/false");

        System.out.println();
        System.out.println("setMeetingType() -->  Set your preferred meeting type");
        System.out.println(indent + "@usage setMeetingType(\"Lecture\")");
        System.out.println(indent + "@param type");
        System.out.println(indent + indent + "@options Activity, Discussion, Labratory, Lecture, "+
            "Nontraditional, ROTC, Seminar, Supervised");
        
        System.out.println();
        System.out.println("setGenEd() -->  Set the General Education requirements you want to show");
        System.out.println(indent + "@usage setGenEd(\"IIA2\")");
        System.out.println(indent + "@param true/false");
        System.out.println(indent + indent + "@options see general catalog");

        System.out.println();
        System.out.println("setSession() -->  Set the Summer Session you want to show");
        System.out.println(indent + "@usage setGenEd(\"S1\")");
        System.out.println(indent + "@param session");
        System.out.println(indent + indent + "@options S1, S2, T1");

        System.out.println();
        System.out.println("+---------------------------------------------------------------------------------+");
        System.out.println();

    }

     public void setDepartment(String department) {
         super.departmentSet = true;
         super.departmentSingle = department;
     }
    public void setTerm(String season, String year) {
        String query = "&period=";
        checkIfPresent(query);

        String seasonNumber = "";
        switch (season) {
            case "Winter": 
                seasonNumber = "1";
                break;
            case "Spring": 
                seasonNumber = "2";
                break;
            case "Summer": 
                seasonNumber = "3";
                break;
            case "Fall": 
                seasonNumber = "4";
                break;
        }
        String addParam = query + year + seasonNumber;
        appendParameter(addParam);
    }

    
    public void setCourseNumber(String num) {
        String query = "&number=";
        checkIfPresent(query);

        String addParam = query + num;        
        appendParameter(addParam);
    }

    public void setCourseNumber(String num, String suffix) {
        String query1 = "&number=";
        checkIfPresent(query1);
        String query2 = "&suffix=";
        checkIfPresent(query2);

        String addParam = query1 + num + query2 + suffix;
        appendParameter(addParam);
    }

    public void setScheduleNumber(String number) {
        String query = "&scheduleNumber=";
        checkIfPresent(query);

        String addParam = query + number;
        appendParameter(addParam);
    }

    public void setUnits(String units) {
        String query = "&units=";
        checkIfPresent(query);

        String addParam = query + units;
        appendParameter(addParam);
    }

    public void setServiceLearning(boolean toggle) {
        String query = "&serviceLearning=";
        checkIfPresent(query);
        if(toggle) {
            String addParam = query + "on";
            appendParameter(addParam);
        }
    }

    public void setSpecialTopics(boolean toggle) {
        String query = "&specialTopics=";
        checkIfPresent(query);
        if(toggle) {
            String addParam = query + "on";
            appendParameter(addParam);
        }
    }

    public void setHonors(boolean toggle) {
        String query = "&honors=";
        checkIfPresent(query);
        if(toggle) {
            String addParam = query + "on";
            appendParameter(addParam);
        }
    }

    public void setDistanceOnline(boolean toggle) {
        String query = "&distanceOnline=";
        checkIfPresent(query);
        if(toggle) {
            String addParam = query + "on";
            appendParameter(addParam);
        }
    }

    public void setDistanceHybrid(boolean toggle) {
        String query = "&distanceHybrid=";
        checkIfPresent(query);
        if(toggle) {
            String addParam = query + "on";
            appendParameter(addParam);
        }
    }

    public void setEvening(boolean toggle) {
        String query = "&evening=";
        checkIfPresent(query);
        if(toggle) {
            String addParam = query + "on";
            appendParameter(addParam);
        }
    }

    public void setInstructor(String instructor) {
        String query = "&instructor=";
        checkIfPresent(query);
        
        String addParam = query + instructor;
        appendParameter(addParam);
    }

    public void setLocation(String facility) {
        String query = "&facility=";
        checkIfPresent(query);
        
        String addParam = query + facility;
        appendParameter(addParam);
    }

    public void setLocation(String facility, String space) {
        String query1 = "&facility=";
        checkIfPresent(query1);
        String query2 = "&space=";
        checkIfPresent(query2);
        
        String addParam = query1 + facility + query2 + space;
        appendParameter(addParam);
    }

    public void setMeetingType(String type) {
        String query = "&meetingType=";
        checkIfPresent(query);
        
        String addParam = query + type;
        appendParameter(addParam);
    }

    public void setGenEd(String genEd) {
        String query = "&ge=";
        checkIfPresent(query);

        String addParam = query + genEd;
        appendParameter(addParam);
    }

    public void setSession(String sess) {
        String query = "&session=S1";
        checkIfPresent(query);

        String addParam = query + sess;
        appendParameter(addParam);
    }

    public void checkIfPresent(String query) {
        int startIndex = 0, endIndex = 0;
        boolean nextParamFound = false;
        if(super.parameters != null) {
            if(super.parameters.contains(query)) {
                StringBuilder removeParam = new StringBuilder(super.parameters);
                startIndex = super.parameters.indexOf(query);
                for(int i = startIndex; i < super.parameters.length(); i++) {
                    if(super.parameters.charAt(i) == '&') {
                        endIndex = i;
                        nextParamFound = true;
                    }
                }
                if(!nextParamFound) {
                    endIndex = super.parameters.length();
                }
                super.parameters = removeParam.delete(startIndex, endIndex).toString();
            }
        }
    }
    public void appendParameter(String addParam) {
        if(parameters != null)
            super.parameters = parameters + addParam;
        else
            super.parameters = addParam;
    }

    public List<String> getCourses() {
        return super.course;
    }
    public List<String> getCourseTitles() {
        return super.courseTitle;
    }
    public List<String> getLocations() {
        return super.location;
    }

}