## Lab Report 2 - Testing & Debugging

#### Step 1: Simplest Search Engine

Here's the code we'll need to run our simple search engine

You'll need to download:
* [Server.java](Server.java)

Create a new file called `SearchEngine.java` and paste the following code:
```java
import java.io.IOException;
import java.net.URI;
import java.util.Vector;

class Handler implements URLHandler {
        // The one bit of state on the server: a number that will be manipulated by
        // various requests.
        Vector<String> stringList = new Vector<String>();
    
        public String handleRequest(URI url) {
            if (url.getPath().equals("/")) {
                return String.format("Ready to search!");
            } 
            else {
                System.out.println("Path: " + url.getPath());
                if (url.getPath().contains("/add")) {
                    String[] parameters = url.getQuery().split("=");
                        stringList.add(parameters[1]);
                        return String.format("New word added: %s", parameters[1]);
                    
                }
                else if (url.getPath().contains("/search")) {
                    String[] parameters = url.getQuery().split("=");
                    Vector<String> results = new Vector<String>();
                        for (String s : stringList){
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
```
Save the new file, then run and compile the code with the following commands
* `javac Server.java SearchEngine.java`
* `java SearchEngine [port number (1024-49151)]`

You should see the following on your terminal:
![Terminal showing search engine running on localhost](compile-and-run-searchengine.png)

Now we have a local server running on our computer! This will run in the background on our computer until we cancel the command on the terminal by pressing `CTRL-C`.

If we visit that URL on a web browser, we'll see this:
![Web page showing search online](search-online.png)

By adding `/add?s=` to the end of our URL path, we can add "tomato" to a word list:
![Add tomato to word list](search-add.png)

Let's add a couple more words before we make our first query:
![Add cherry to word list](search-add-cherry.png)
![Add shill to word list](search-add-shill.png)
![Add child to word list](search-add-child.png)
![Add thick to word list](search-add-thick.png)
![Add think to word list](search-add-think.png)

* In these screenshots, I added our new word to the end of our URL; this calls the `add` method in `SearchEngine` to add our new word to our list.
* 

Now, let's make our first query; we want to search our word list for every word with "hi" in it. For that, we'll replace `/add?s=` with `/search?s=` and our search query `hi`:

![Search query](search-query-hi.png)

The website now shows every word from our word list that matches our query!

#### Step 2
Here is where to upload the screenshots from the lab on Thursday
