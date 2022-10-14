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
```
Save the new file in the same directory as `Server.java`, then compile and run the code with the following commands:
* `javac Server.java SearchEngine.java`
* `java SearchEngine [port number (1024-49151)]`

You should see the following on your terminal:
![Terminal showing search engine running on localhost](compile-and-run-searchengine.png)

Now we have a local server running on our computer! This will run in the background on our computer until we cancel the command on the terminal by pressing `CTRL-C`.

Notice that we were given a localhost URL on the terminal. If we visit that URL on a web browser, we'll see this:
![Web page showing search online](search-online.png)

Our search engine works by adding words to a list of Strings, then searching that list for words that match our query.

First, let's add some words to our list: We can use the `/add?s=` argument at the end of the URL to add any word after the `=` to our list.

For example, we can add "tomato" to our list by inserting `/add?s=tomato` to the end of the URL:
![Add tomato to word list](search-add.png)


The page updates to show that "tomato" was added to our list!

Here's what's happening in the background:
* When we add the `/add?s=tomato` argument to the URL and press `enter`, we pass the new URL to the `handleRequest` method.
* Within `handleRequest`, the URL catches the if statement looking for `/add` and splits our argument in two: `s` and `tomato`.
* Then, the second argument (our new word; in this case "tomato") is added to `stringList`; the site updates to show the word was added.

Let's add a couple more words before we make our first query:
![Add cherry to word list](search-add-cherry.png)
![Add shill to word list](search-add-shill.png)
![Add child to word list](search-add-child.png)
![Add thick to word list](search-add-thick.png)
![Add think to word list](search-add-think.png)


Now, let's make our first query; we want to search our word list for every word with "hi" in it. 

For that, we'll replace `/add?s=` with a new argument: `/search?s=hi`.

![Search query for "hi"](search-query-hi.png)

How `/search?s=hi` works is similar to how `/add` works:
* We update the URL passed to `handleRequest` method.
* We catch the if statement looking for `/search` in our new URL.
* The method then splits our argument in two: `s` and any word after `=` (e.g. `hi`).
* The method looks through every word in `stringList` for any word that contains/matches the second argument (our query word `hi`) and copies every matching word to a new list of Strings (`results`).
* Once `stringList` has been fully read through, the method returns `results` and prints it on the site.

Now we set up our simple search engine! We can add as many words as we want, and search within those words.

#### Step 2
Here is where to upload the screenshots from the lab on Thursday
