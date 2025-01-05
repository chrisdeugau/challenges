import java.io.*;
import java.util.ArrayList;

class Cut {
    public static void main(String[] args) {
        BufferedReader br;
        String filename = "";
        ArrayList<Integer> fieldsToPrint = new ArrayList<Integer>();
        String delim = "\t";

        // need to parse the params for delimiters and fields to print
        for (int i=0; i < args.length; i++) {
            String flag;
            String param;

            // check if our args length is greater than one, or if it's just "-"
            if (args[i].length() > 1) {
                flag = args[i].substring(0, 2);
                param = args[i].substring(2).trim();    
            } else {
                flag = "-";
                param = "";
            }

            // handle different potential arguments
            if (flag.equals("-f")) {
                String[] splitParams = param.split("[ ,]");
                for (String part : splitParams) {
                    fieldsToPrint.add(Integer.parseInt(part) - 1);
                }
            } else if (flag.equals("-d")) {
                delim = param;
            } else if (flag.equals("-")) {
                filename = "";
            }
            else {
                filename = args[i];
                break;
            }
        }

        // list of input lines delimitted
        ArrayList<String[]> inputs = new ArrayList<String[]>();

        try {
            // parse the file into memory
            if (filename.equals("")) {
                br = new BufferedReader(new InputStreamReader(System.in));
            } else {
                br = new BufferedReader(new FileReader(filename));
            }
            
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(delim);
                inputs.add(parts);

                line = br.readLine();
            }

            // print the correct fields from inputs
            for (String[] curr : inputs) {
                for (int i = 0; i < fieldsToPrint.size(); i++) {
                    if (fieldsToPrint.get(i) < curr.length)
                        System.out.print(curr[fieldsToPrint.get(i)]);
                    if (i < fieldsToPrint.size() - 1) {
                        System.out.print(delim);
                    } else {
                        System.out.println();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}