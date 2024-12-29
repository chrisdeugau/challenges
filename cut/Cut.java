import java.io.*;
import java.util.ArrayList;

class Cut {
    public static void main(String[] args) {

        // parse the file into memory
        BufferedReader br;
        String filename = "";
        int fieldToPrint = 0;
        String delim = "\t";

        for (int i=0; i < args.length; i++) {
            String flag = args[i].substring(0, 2);
            String param = args[i].substring(2).trim();

            if (flag.equals("-f")) {
                fieldToPrint = Integer.parseInt(param) - 1;
            } else if (flag.equals("-d")) {
                delim = param;
            } 
            else {
                filename = args[i];
                break;
            }
        }

        System.out.println(fieldToPrint + " " + delim + " " + filename);
        ArrayList<String[]> inputs = new ArrayList<String[]>();

        try {
            br = new BufferedReader(new FileReader(filename));
            
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(delim);
                inputs.add(parts);

                line = br.readLine();
            }

            for (String[] curr : inputs) {
                if (fieldToPrint < curr.length)
                    System.out.println(curr[fieldToPrint]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("cut command");
    }
}