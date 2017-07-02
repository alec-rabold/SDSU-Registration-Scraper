public class Controller extends Options {
    public static void main(String[] args) throws Exception {

        /**
         * This program scrapes all classes available on SDSU's registration page in real-time. All of the data available on the registration
         * page is stored in ArrayLists. Then, the program takes some number of entries (10 by default), and checks whether they have a Course, Course Title, 
         * and Location. If they do, the 'WritingJSON' class formats the course's location so that it's able to be found in Google Maps. Finally, the output 
         * is sent to the file: AlecRabold.output.json which Google Maps is able to interpret and set markers.
         * 
         * I've implemented many options that you can play around with that can be found and documented in the Options.java class, or by simply calling
         * custom.printOptions(). My purpose behind this project was both to fulfill the requirements as a CS108 final project and to make a tool
         * that I'll be able to use when registering for classes over the next 3 years. 
         * 
         * Thanks for checking it out!
         * Alec
         * 
         */
        
        Options custom = new Options();
        WritingJSON makeFile = new WritingJSON(custom.getCourses(), custom.getCourseTitles(), custom.getLocations());

        /** Custom Search Parameters Go Here [Reference Options.java] */
        custom.setTerm("Fall", "2017");

        /** Number of iterations */
        custom.iterateAll(); // Take this out if iterating one at a time
        // custom.setDepartment("CS");
        // custom.iterateOne();

        /** Printing the Results */
        custom.printDepartmentCourses();

        /** Creating the JSON file */
        makeFile.createJSONfile();     

        /** Prints a list of available methods and their documentation- remove if desired */
        Thread.sleep(2000);
        System.out.println();
        System.out.println("Here is a list of available commands to add to the main method in the Controller class");
        System.out.println();
        Thread.sleep(5000);
        custom.printOptions();
        
    }
}