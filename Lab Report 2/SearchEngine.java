import java.io.IOException;
import java.net.URI;
import java.util.Vector;

class Handler implements URLHandler {
        // Vector of strings that will contain all the words we add
        Vector<String> stringList = new Vector<String>();
    
        public String handleRequest(URI url) {
            // If the URL doesn't have any arguments, load home page
            if (url.getPath().equals("/")) {
                return String.format("Ready to search!");
            } 
            else {
                System.out.println("Path: " + url.getPath());
                if (url.getPath().contains("/add")) {
                    String[] parameters = url.getQuery().split("=");

                        // Add the string we included in the argument (parameters[0] = "?s")
                        stringList.add(parameters[1]);
                        return String.format("New word added: %s", parameters[1]);
                    
                }
                else if (url.getPath().contains("/search")) {
                    String[] parameters = url.getQuery().split("=");

                    //Create a new vector to store words that match query
                    Vector<String> results = new Vector<String>();

                        // Loop through every word in stringList and add each one to results
                        // if the word matches the query
                        for (String s : stringList){
                            // parameters[1] = query word, parameters[0] = tag "?s"
                            if (s.contains(parameters[1])){
                                results.add(s);
                            }
                        }
                    return results.toString();
                }
                return "404 Not Found!";
            }
        }
    }

    class SearchEngine {
        public static void main(String[] args) throws IOException {
            if(args.length == 0){
                System.out.println("Missing port number! Try any number between 1024 to 49151");
                return;
            }
    
            int port = Integer.parseInt(args[0]);
    
            Server.start(port, new Handler());
        }
    }