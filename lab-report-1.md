# Lab Report 1 - Remote Connections

In this lab report, you will learn how to access your `ieng6` account and remotely connect to a computer in the CSE basement.


***

## What you'll need (and important links)

* [Virtual Studio Code](https://code.visualstudio.com/)
    - If you don't have it already downloaded, do that now; you'll need it beyond this point in this class. 

* [UCSD Account Lookup](https://sdacs.ucsd.edu/~icc/index.php)
    - This is the link to access your student account (including your course-specific accounts)

* [OpenSSH](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh_install_firstuse?tabs=gui)
    - For anyone using Windows, you will need to download OpenSSH to make the remote connection since the server only accepts secured connections.

***

## Step 1: Downloading Visual Studio Code

* If you already have VSCode installed and ready, skip to step 2; otherwise, keep reading.

Go to the VSCode website linked above and download the version compatible with your computer.

(*Note:* You **need** a computer that can run VSCode offline; this process will not work if you are using the browser-based version of VSCode. Sorry tablet/Chromebook users 🙁)

After the install finishes, open VSCode; your screen should now resemble this: (The format/color theme may vary depending on device)

![Visual Studio Code startup page](./vscode%20startup.png)

*** 

## Step 1.5: Installing OpenSSH (non-Windows users - skip ahead)

For anyone using Windows, you have an extra step: downloading/activating OpenSSH. Click the OpenSSH link above and you'll be taken to a Microsoft page; scroll down to this section and follow the instructions:

![OpenSSH install instructions](openssh%20install%20instructions.png)



***

## Step 2: Setting up your course-specific account

Next, go the Account Lookup page linked above and you'll be greeted by a page that should look like this:

![Account Lookup login page](./account%20lookup%20login.png)

Enter your login information, then click "Submit Query". If the login succeeded, you'll see something like the following:

![Account Lookup result page](./account%20lookup%20page.png)

You should notice a clickable button under "Additional Accounts" with text that looks like "`cs15lXXXXXX`". This is your account specifically made for this course. 

* The last six characters will differ when you access this page; this screenshot was taken during the Fall 2022 quarter (the `fa22` part) and the last two characters are unique to your account (mine are `ix`).

Click on the button, and the web should take you here:

![CS15L account page](./CS15L%20account%20page.png)

Since this is your first time accessing this account, you'll probably see an alert to reset your password. Go ahead and click through and reset your password.

* *Note:* there'll be a roughly 15-minute cooldown after you change/set your password before the servers will reflect the change.

***

## Step 3a: Making a Remote Connection

* From this point on, we'll need to  specify which terminal to make commands. For terminal commands on the client (the computer physically in front of you) we'll use <font color = "0c7bdc"> the color blue.</font> For terminal commands on the server (the computer you're connecting to over the Internet) we'll use <font color = "green"> the color green.</font>

On <font color = "0c7bdc">your computer</font>, start VSCode and open a new terminal.

* Click the "New Terminal" option in the "Terminal" menu or use keyboard shortcut `` CTRL + ` `` or ``command + ` ``, depending on your system.

In the terminal, type the following command and press `enter`:

``` ssh cs15lxxxxxx@ieng6.ucsd.edu```

* Make sure you type a lowercase L "l", not the number one "1".

The first time you connect to the server, you'll see a warning mentioning "authenticity can't be established"; this is expected, go ahead and type `yes` and press `enter`.

You should receive a password prompt; enter your password and successfully log in, your screen will fill up with information about usage information that'll look like this:

![log in output on terminal](ssh%20login%20output.png)
* *Note:* Password prompts will **NOT** show any characters as you type. Even though it looks like nothing's happening, the terminal will log what you type.

* If you run into issues logging into your `ieng6` account, try resetting your `cse15l` account password again and wait 15-30 minutes. If you still have issues, reach out to your professor or TAs.

 Otherwise, if you've made it this far, congrats! You connected your terminal to the server; now we can use some commands on the terminal to access files on <font color = "green">the server.</font>



***
## Step 3b: Run Some Commands

Now that you're remotely connected, try running a couple commands on both <font color = "0c7bdc"> your computer's terminal </font> and <font color = "green"> the terminal connected to the server</font>.

Here are a few useful commands you can try:
* `ls`: prints the contents of the current directory (plain text denotes files, blue text denotes directories)
* `pwd`: prints the path to the current directory
* `cd PATH`: changes the current directory to the directory at path PATH
* `cd ~`: returns to the current user's home directory
* `cp FILEPATH DESTINATIONPATH`: copies the file at FILEPATH to the directory at DESTINATIONPATH 
* `exit`: logs out of `ieng6`

After running some commands, your <font color = "green">terminal</font> should resemble this: 

![terminal](./terminal%20commands.png)

***

## Step 4. Moving/Copying Files over SSH

Once you've made a remote connection, one of the most powerful things you can learn is how to transfer files between the client (<font color ="0c7bdc">your computer</font>) and <font color = "green">the server</font>. In this step, we'll cover how to use the command `scp`.

On <font color = "0c7bdc"> your computer</font>, create a file and fill it with any text you want;
for this example, we'll make a Java file called `HelloDateAndTime.java` with the following code: 
```java
 import java.time.*;
class HelloDateAndTime {
	public static void main(String[] args) {
		System.out.println("Hello World!");
		System.out.println("OS: " + System.getProperty("os.name"));
		System.out.println("User name: " + System.getProperty("user.name"));
		System.out.println("Date and Time: " + java.time.LocalDateTime.now());
	}
}	
```
Save the file, then open a new <font color = "0c7bdc">terminal</font> on VSCode and run the following command:

`scp HelloDateAndTime.java cs15lXXXXXX@ieng6.ucsd.edu:~/`

* IMPORTANT: Make sure you include the `:~/` at the end of your `ieng6` username as this specifies where to send a copy of the file; without it, the file will **not** copy to <font color = "green">the server</font>.

You should receive a password prompt; log in as you would with `ssh`. Once the password is entered, you should see something like this:

![Using scp to upload file](./scp%20command.png)
* I don't have a password prompt because I have a SSH key set up (more on that later)

If you have Java installed, try compiling and running the file and note its output; we'll compare results later.

Now that my Java file was uploaded, let's log back into <font color = "green">the terminal connected to the server</font> to see if it's there using command `ls`:

![Terminal now shows my Java file on the server](./scp%20HDAT%20success.png)

Success! Since I made a Java file, let's compile and run the program to see what it prints out: 

![Java file compiles and runs](./HDAT%20compile%20run%20and%20output.png)

Since we have the file on both <font color="0c7bdc"> the client</font> and <font color="green">the server</font>, compare the results of running the program on both machines. 

***

## Step 5: SSH Keys

Entering your password every time you use `scp` and `ssh` can be  repetitive and annoying. Since we will be using these commands often, we need to find a way to streamline this process so we can work more efficiently.

In this step, we'll show a good way to quickly log into secured accounts: using `ssh` keys to bypass typing in a password.

Whenever we generate `ssh` keys, we actually create a ***pair*** of keys: a *public* key (the file ending with `.pub`) that we keep on the server, and a **private** key that we keep securely on the client.

On <font color = "0c7bdc">your computer's terminal</font>, type in the following: `ssh-keygen`

You'll see the terminal fill with text similar to this: 

![SSH-keygen process](./ssh%20keygen%20on%20local%20terminal.png)

* In this example, we already created a key, so the terminal asks to overwrite the existing key; on your computer, you will not see this.

The terminal will prompt you to enter a path to save the keys to; for this tutorial, we will save it to the default path (`Users\chees/.ssh`).

* *Extra step for Windows users:* You will need to run additional commands on your computer's terminal to use your private key. Open [this link](https://learn.microsoft.com/en-us/windows-server/administration/openssh/openssh_keymanagement#user-key-generation), scroll down and run the commands listed here:

![ssh-agent commands](./windows%20ssh-agent%20commands.png)
* **IMPORTANT NOTE:** On the line with the `ssh-add` command, change `id_ed25519` to `id_rsa`; ED25519 is a different encryption algorithm that we will not cover in this tutorial.

Now that we have our new keys, we need to copy the *public* key to the server.

On the <font color="green">terminal connected to the server</font>, we need to make a new directory to store the public key. Enter the following command:
`mkdir .ssh` like this: 

![Make directory on remote terminal](./make%20ssh%20directory%20on%20remote%20terminal.png)
* We already made the directory `.ssh` in this instance; if the directory doesn't exist, the terminal will create it. You can verify this with `ls`.

On the <font color="0c7bdc">terminal on your computer</font>, use `scp` to copy the public key to the directory you just made on the server:

![copy key from local terminal to remote terminal](./scp%20key%20on%20local%20terminal.png)

If done successfully, you should be able to `scp` and `ssh` without having to enter your password. How convenient!



