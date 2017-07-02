import java.util.*;
import java.io.*;

public class WritingJSON extends Options{
    List<String> courses = new ArrayList<String>();
    List<String> titles = new ArrayList<String>();
    List<String> locations = new ArrayList<String>();
    HashMap<String, String> buildings = new HashMap<String, String>();
    private final int NUM_GOOGLE_MARKERS = 10;

    
    /**
     * Writes to a JSON file called AlecRabold.output.json
     * Prints the Nth line of SDSU's department page
     * 
     * @param lineNum number line to print
     */
    public WritingJSON(List<String> courses, List<String> titles, List<String> locations){
        this.courses = courses;
        this.titles = titles;
        this.locations = locations;

        buildings.put("AD", "Administration");
        buildings.put("AH", "Adams Humanities");
        buildings.put("AL", "Arts and Letters");
        buildings.put("COM", "Communication Building");
        buildings.put("BSCI", "Biosciece Center");
        buildings.put("DA", "Dramatic Arts");
        buildings.put("E", "Engineering Building");
        buildings.put("EBA", "Education and Business Administration");
        buildings.put("EC", "East Commons");
        buildings.put("ED", "Education Building");
        buildings.put("ENS", "ENS Building");
        buildings.put("ENSA", "ENS annex");
        buildings.put("ESC", "Extended Studies building");
        buildings.put("FAC", "Fowler Athletics Center");
        buildings.put("GMCS", "Geology, Math, Computer Science Building");
        buildings.put("HH", "Hepner Hall");
        buildings.put("HT", "Hardy Tower");
        buildings.put("LSN", "Life Sciences North");
        buildings.put("LSS", "Life Sciences South");
        buildings.put("LT", "Little Theatre");
        buildings.put("M", "Music Building");
        buildings.put("MH", "Manchester Hall");
        buildings.put("NH", "Nasatir Hall");
        buildings.put("NE", "North Education");
        buildings.put("P", "Physics Building");
        buildings.put("PA", "Physics Astronomy Building");
        buildings.put("PG", "Peterson Gym");
        buildings.put("PS", "Physical Sciences");
        buildings.put("PSFA", "Professional Studies and Fine Arts Building");
        buildings.put("SLHS", "Speech Lanuage and Hearing Center");
        buildings.put("SH", "Storm Hall");
        buildings.put("SHW", "Storm Hall West");
        buildings.put("SSE", "Student Services East");
        buildings.put("SSW", "Student Services West");
        buildings.put("WC", "West Commons");
        buildings.put("WRC", "Women's Resource Center");
    }

    // Creates and writes 10 random classes, their titles, and location to a json file
    public void createJSONfile() throws IOException {

        Random rand = new Random();
        

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("AlecRabold.output.json"), "utf-8"))) {

            writer.write("[");
            writer.write("[\"Location\", \"CourseTitle\"],");

            int index = 0;

            for(int i = 0; i < NUM_GOOGLE_MARKERS; i++) {
                boolean classExists = false;
                boolean titleExists = false;
                boolean locationExists = false;

                String theCourse = "";
                String theTitle = "";
                String theLocation = "";

                try {
                    while((!classExists) || (!titleExists) || (!locationExists)) {
                        index = rand.nextInt(courses.size());

                        theCourse = courses.get(index);
                        classExists = checkExists(theCourse);

                        theTitle = titles.get(index);
                        titleExists = checkExists(theTitle);

                        theLocation = locations.get(index);
                        locationExists = checkLocExists(theLocation);
                        if(locationExists)
                            theLocation = parseLocation(theLocation);
                    } 
                }
                catch(StringIndexOutOfBoundsException e) {
                }

                writer.write("[\"SDSU " + theLocation + "\","); // Location ["SDSU Manchester Hall"],
                writer.write("\"" + theCourse + ": "+ theTitle + "\"]"); // CourseTitle ["CS108: Intermed Computer Prog"]
                // writes commas after every pair except the last
                if(i != NUM_GOOGLE_MARKERS - 1)
                    writer.write(",");
            }
            writer.write("]");
        }
        
    }

    //Makes sure that the entry isn't just a space
    public boolean checkExists(String item) {
        if(item.matches(".*[a-zA-Z]+.*"))
            return true;
        else 
            return false;
    }

    public boolean checkLocExists(String location) {
        if(location.contains("ON-LINE") || location.contains("SDSU")) {
            return false;
        }
        else if(location.matches(".*[a-zA-Z]+.*")){
            return true;
        }
        else {
            return false;
        }
    }

    public String parseLocation(String location) {
        
        int startIndex = 0;
        int endIndex = location.indexOf("-");

        location = location.substring(startIndex, endIndex);

        Iterator it = buildings.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if(pair.getKey().equals(location.trim()))
                location = pair.getValue().toString();
        }
        
        return location;

    }

    
}