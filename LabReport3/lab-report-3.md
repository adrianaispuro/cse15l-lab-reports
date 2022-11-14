## Lab Report 3 - Researching Commands

### What we'll need

* [A clone of this directory](https://github.com/ucsd-cse15l-f22/docsearch/)
* A computer that uses a `bash` terminal (in this case, we'll use a machine on the `ieng6` server)

---

For this lab report, we'll be looking at several interesting command-line options that allow us to use grep in unique ways.

#### Part 1: Using `-w`

If we add the command-line flag `-w` to our grep command, the terminal will return instances that match our search term as only whole words (no substrings).

For example, in `/technical/government`, we'll see how we get different results with `grep` on an entire directory:

```text
[cs15lfa22ix@ieng6-202]:government:414$ ls
About_LSC  Alcohol_Problems  Env_Prot_Agen  Gen_Account_Office  Media  Post_Rate_Comm

% Here we use `grep` to find any matches for "test" in the Media directory, and printing the first ten results
[cs15lfa22ix@ieng6-202]:government:415$ grep  test Media/* | head -10

Media/A_Perk_of_Age.txt:with the greatest financial or social needs. The federal Agency
Media/Annual_Fee.txt:Most of the latest increase -- $42 -- will go to the Lawyers
Media/Assuring_Underprivileged.txt:protest.
Media/Assuring_Underprivileged.txt:The attorneys who appear before Zelon attest to the fact that
Media/Assuring_Underprivileged.txt:Competition, a contract-negotiating contest open to first-year law
Media/Assuring_Underprivileged.txt:Zelon faced one of the greatest challenges of her legal career,
Media/Assuring_Underprivileged.txt:their use of hearsay testimony.
Media/Barr_sharpening_ax.txt:LSC President John Erlenborn recently testified to Congress that
Media/Civil_Matters.txt:revenue estimating conference, which will give lawmakers the latest
Media/CommercialAppealMemphis2.txt:counties qualify for public legal aid, the latest census shows a

% Now let's use `-w` to find exact matches for "test" in the Media directory, again only printing the first ten results
[cs15lfa22ix@ieng6-202]:government:416$ grep -w  test Media/* | head -10
Media/New_Online_Resources.txt:"We are replacing the older test site with two dynamic
Media/Terrorist_Attack.txt:more useful test of future lawyers' potential than rote memory, and

```

Here we can see that by using `grep` without `-w` includes matches where "test" is a substring within another word; using `grep -w` only returned matches where "test" was a whole word.

We can also use this on single files. In this example, we'll look at `/government/Media/water_fees.txt`:

```text
[cs15lfa22ix@ieng6-202]:Media:448$ grep vote water_fees.txt | head -5

election. The propertyrelated fee increase requires voter approval
Irrigation's vote.
51% of property owners in the district must submit protest votes in
writing. No protest vote means approval of the price jump.
calls eligible voters "owners of record." Another calls them

[cs15lfa22ix@ieng6-202]:Media:449$ grep -w vote water_fees.txt | head -5
Irrigation's vote.
writing. No protest vote means approval of the price jump.

```

This also works to find the whole-word results of searching for "vote" in the file.

In our last example using `-w`, we can see how using `-w` works with multiple files in a directory:

```text
[cs15lfa22ix@ieng6-203]:911report:499$ grep -w terror chapter-2.txt chapter-1.txt 
chapter-2.txt:            "All Americans must recognize that the face of terror is not the true face of Islam,"
```

Finding exact matches with `grep -w` can be useful when searching multiple files for specific keywords.

#### Part 2: Using `-c`

If we use the `-c` with `grep`, the terminal will display the output of the **count** of matches.

For example, we can count the number of matches of a search term in `technical/plos`:

```text
[cs15lfa22ix@ieng6-203]:plos:509$ grep -c health journal.pbio.0020052.txt 
6
```

Here, the output is the number of matches found in the file `journal.pbio.0020052.txt`.

We can also combine `-c` with `-w` to find the count of *exact* matches in multiple files:

```text
[cs15lfa22ix@ieng6-203]:plos:514$ grep -wc health pmed.001002*
pmed.0010021.txt:1
pmed.0010022.txt:6
pmed.0010023.txt:0
pmed.0010024.txt:0
pmed.0010025.txt:0
pmed.0010026.txt:0
pmed.0010028.txt:0
pmed.0010029.txt:3
```

Here the output shows the number of matches found in each file that matches our `*` pattern.

If we want to get the results of `grep -c` for an entire directory, we can also add `-r` instead of using `*` patterns:

```text
[cs15lfa22ix@ieng6-203]:technical:532$ grep -rc govern 911report/
911report/chapter-1.txt:3
911report/chapter-10.txt:12
911report/chapter-11.txt:36
911report/chapter-12.txt:97
911report/chapter-13.1.txt:40
911report/chapter-13.2.txt:2
911report/chapter-13.3.txt:26
911report/chapter-13.4.txt:18
911report/chapter-13.5.txt:23
911report/chapter-2.txt:29
911report/chapter-3.txt:60
911report/chapter-5.txt:18
911report/chapter-6.txt:27
911report/chapter-7.txt:7
911report/chapter-8.txt:17
911report/chapter-9.txt:3
911report/preface.txt:3
```

This command can be very useful in combination with other commands to quickly find matches with large files without having to print the matching output.

#### Part 3: Using `-v`

Another useful option with `grep` is adding the `-v` "inverse search" flag, which finds instances with ***no*** matches to the search term.

For example, let's find the lines in `technical/biomed/rr166.txt` without the lowercase letter 'a':

```text
[cs15lfa22ix@ieng6-203]:biomed:548$ grep -v a rr166.txt 

  
    
      
        Introduction
        the fibrotic process [ 2 ] .
        properties 
        (TX)A 
      
      
        
          to 8.
        
        
          2 , 6-keto-PGF 
          2 , PGF 
          2α , TXB 
          vol/vol) for 6-keto-PGF 
          2 . Then PGF 
          2 , PGE 
          ions m/z 569/573 for PGF 
          2α , m/z 614/618 for TXB 
        
        
          1% Tween 20, 10 mM phenylmethylsulphonyl fluoride,
        
        
          overnight.
        
        
        
      
      
        Results
        
          ng/10 6cells/30 min, respectively; 

          2 ), PGF 
          except TXB 
          0.61 [0.21-1.64] ng/10 6cells/30 min with IL-1β
          (results not shown).
          HF-NL (0.61 [0.21-1.64] ng/10 6cells/30 min; 
          different between the two cell groups (1.73 [0.77-2.53]
          versus 0.75 [0.15-2.58] ng/10 6cells/30 min, in HF-IPF
          1α :TXB
          P = 0.09 [Fig. 2]).


          4).



        Discussion
        2 , TXB
        2 to TXA
        2 :TXA
        [ 13 14 15 16 17 26 ] . TXA
        smooth muscle cells TXA
        c-fos ,
        14 ] .
        of PGI
        fibrogenesis.
        2 to TXA
        in vivo .

        5 6 7 8 ] .
        PGE
        2 production, but they further showed
        2 production in response to TGFβ
        Wilborn
        lungs of subjects with IPF.


        Conclusion
        2 :TXA
        of IPF.

```

If we simply want the count of lines that don't have our search term, we can `-vc`, which combines our "inverse search" and "count" command-line options:

```text
% Let's search how many lines in 'rr167.txt' that do not contain "in"
cs15lfa22ix@ieng6-203]:biomed:548$ grep -vc in rr167.txt
347
```

Finally, we can combine `-vc` with `*` patterns to "inverse search" multiple files and return the count for each file:

```text
[cs15lfa22ix@ieng6-203]:biomed:549$ grep -vc heal rr*     
rr166.txt:453
rr167.txt:726
rr171.txt:660
rr172.txt:448
rr191.txt:551
rr196.txt:667
rr37.txt:536
rr73.txt:338
rr74.txt:426
```

I'm not sure where the command `grep -v` would be useful, but it is interesting that there is a command that returns the opposite of what is expected with `grep`.
