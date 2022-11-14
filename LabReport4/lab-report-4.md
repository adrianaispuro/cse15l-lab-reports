## Lab Report 4 - Using Vim

### What we'll need

* [Vim (if not already installed)](https://www.vim.org/download.php)

The focus of this lab was becoming familiar with Vim to make small edits and compare/contrast the editing styles of making local edits against making remote edits.

#### Part 1: Using and Optimizing with Vim

For our exercise with Vim, we are tasked with the simple task of adding a `print` statement between lines 16 and 17:
![snippet of code](media/docSearch%20files%20before.png)

However, we are told to make this edit using Vim to streamline the process as much as possible. After optimizing with my partner [Jack Muir](https://jackmuir1.github.io/), we came up with the following commands:

* `vim DocSearchServer.java<Enter>` opens the DocSearchServer java file that we need to edit

* `/if<enter>` finds the first instance of the word "if" (in this case, line 16).
![screenshot of vim, with cursor on line of code starting with "if"](media/vim%20slash%20if.png)

* `<shift>A<enter>` enters visual line mode at the current line (line 16), adds a new line below it, and moves the cursor to it in insert mode.
![screenshot of vim, with cursor on blank line after "if"](media/vim%20shift%20a%20enter.png)

* `System.out.println(f.toString() + "is directory");` prints a new message that the file being scanned is a directory.
![screenshot of vim, with cursor on line of print-line code](media/vim%20println.png)

* `<esc>:x<enter>` returns to command mode, saves the new text to the file, and quits Vim
![screenshot of vim open with command colon-x](media/vim%20colon%20x.png)
* `sh st<tab> 5012` uses tab autocomplete to run `start.sh`, a script written to compile and run DocSearchServer with the argument
![screenshot of running script](media/vim%20script.png)

#### Part 2: Editing style (Local edits v. Vim)

Here, I timed myself making the edit above in two ways.

1. Making the edit locally, using `scp` to send the edit to the remote server, compiling and running the edited file using a script.
2. Making the edit already logged onto the remote server using Vim, then compiling and running using the same script.

Using the first method (local editing), it took 2 minutes and 19 seconds to edit, copy, compile, and run the file.

Using the second method (remote editing using Vim), it took 43 seconds to edit, compile, and run.

---
If I had to work on a program running remotely, I would use Vim to make minor edits, but in general I would prefer making local edits. I feel that local edits are better  because it's easier to isolate any new bugs/errors to a single instance of the code.

My preference for local edits would be even stronger if the project being worked on had *any* involvement with securing sensitive data. I would hesitate to make remote edits with these kinds of projects because even minute edits can have massive implications to data leaks and malicious activity.
