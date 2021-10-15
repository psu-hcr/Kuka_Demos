### To run this demo on the Kuka LBR IIWA 14 820
#### Step 0: Install, Sunrise Workbench on your machine, clone this repo into your Sunrise Workspace, setup connection to the Kuka
*Note: Use these steps to clone the repo with the Github Desktop App. This is only done once.*  
1.Open SunriseWorkbench and select a workspace. The default on Windows is a folder called ~/SunriseWorkspace.
2. To clone this repository into the workspace, open the Github desktop app and select File-> Clone repository.
3. In the window that pops up, find psu-hcr/Kuka_Demos and select it. Change the local path to the sunrise workspace and click `Clone'.
*You should now have a folder called ~/SunriseWorkspace/Kuka_Demos on your local machine.*
4. Set up the ethernet connection with the X66 ethernet port on the Sunrise Cabinet by going to Control Panel ->Network and Internet > Network and Sharing Center
5. Click on the Ethernet hyperlink under the unidentifies ethernet network and select Properties
6. Under properties, select IPv4.
7. In the menu that pops up, manually enter the IP address as 172.31.1.42 and mask as 255.255.0.0


#### Step 1: Load Project on Kuka
1. Open the Kuka_Demos project in the Sunrise Workbench. Click the Synchronize icon.
2. Select Deploy to Project and execute.
3. You will be asked to select a user and enter a password. Switch to user Maintenance technician and enter `argus` as the password.
4. On the Kuka smartHMI tablet, select the application and press the green arrow to enable. Then choose from the programmed motions.
This command incorporates changes from the remote (online) repository into your (local) branch.

#### Step 2: Make changes
You may now add publications, edit your people page, add news posts, etc. See below sections for specifics on how to make these kinds of changes. If you want to make more structural changes to the website, please make your own branch to test things out. We can merge them later when the lab agrees to the bigger changes.

#### Step 3: Preview locally
Use the command `bundle exec jekyll serve` and go to `http://127.0.0.1:4000` on your browser to view a local copy of the website. This allows you to see how your changes will look before making them public.

#### Step 4: Commit changes
To save the current state of your version of the website, add and commit your edits:   
`git add .` adds all changes in the folder   
`git add FILENAME.txt` adds all changes in a specific file   

Then commit the changes using `git commit -m "DESCRIBE CHANGES"` to commit changes and add a description of  the changes you made.

#### Step 5: Push changes
To make your changes to the actual, public website, push them to the remote repository using:   
 `git push origin master`

Now everyone can see them and the website will be publicly updated. If you get an error on this step, someone else may have pushed after you last pulled. To fix this, `git stash` your changes, and talk to Katie or Ian.

### To add a pdf and update pdf metadata:
1. Add your pdf to your local copy of the website by placing it in the folder ~/MurpheyLab.github.io/pdfs.
2. Check that your pdf has a title, author, and keywords by looking at the document properties either by right-clicking the file or using a pdf viewer. There are a number of applications that can be used to update metadata. An optional method is described below.
3. If you do not have pdftk already installed, use `sudo apt-get install pdftk`.
4. Navigate to the pdfs subfolder and run `pdftk mypaper.pdf dump_data output report.txt`.
5. Open report.txt in a text editor and add the following lines
```
InfoBegin
InfoKey: Title
InfoValue: The title of your paper goes here
InfoBegin
InfoKey: Author
InfoValue: your name, your coauthors name, your other coauthors name
InfoBegin
InfoKey: Subject
InfoValue: 160 characters or less describing your paper. Try to include keywords for indexing
InfoBegin
InfoKey: Keywords
InfoValue: keyword1, keyword2
```
Keep in mind the keywords are used by google like html metatags, so these may not be the exact keywords your list in your paper. However, they are VISIBLE to anyone who downloads your paper, so keep that in mind as you choose keywords.

6. Now you will create a new pdf in some other directory using `pdftk mypaper.pdf update_info report.txt output otherdir/mypaper.pdf`.

7. Replace the original file with the file generated in otherdir/mypaper.pdf.

8. Use `git add` to add your pdf to the repository. Note: As long as you used the name report.txt and it was generated in the directory pdfs, it will be ignored by git. If you used a different name/dir, please make sure you do not add this to the changes staged for commit.
 
### To add a publication:
1. Add a publication to `publications.html` in the format:
```
<p><b>TITLE</b>
<br>AUTHORS (first initial, last name)
<br><i>JOURNAL/CONFERENCE</i>, DETAILS, YEAR. <a href="/pdfs/URL.pdf">PDF</a> <a href="/videos/URL.mp4">Video</a></p>
```    
Replace TITLE, AUTHORS, JOURNAL/CONFERENCE, DETAILS, YEAR, and URLs, with the correct information.   
2. If there is no pdf, replace `pdfs/URL.pdf` with a link to the publication and replace the display text `PDF` with `Paper`.   
3. If there is no video, delete the entire `<a ... </a>` surrounding `VIDEO`.   
4. Add the publication to the associated Project pages, the People pages of each author,
and the Recent Publications list on the home page, by copying and pasting.    
    -  On each of these pages, ensure that the path to the pdf or video is accurate. For example, adding a pdf to a People page requires changing the path to `../pdfs/URL.pdf`    
5. See other publications in `publications.html` for reference.     

---
### To add a news post:
1. Create a new post by making a copy of `_posts/YYYY-MM-DD-TEMPLATE.md` in the `_posts` folder
2. Change the title (line 3)
3. Change the date (line 4), keeping it in YYYY-MM-DD format
4. Add an image (line 7, optional)
     - `IMAGE_PATH` is the path to the image (e.g., /images/griff.jpg)
     - `ALT_TEXT` is text that will show if the image doesn't load
     - `CAPTION_TEXT` is text that will show when mouse hovers over image
5. add text (line 11)
6. See other posts in the `_posts` folder for reference.


---   
If you have questions, contact Ana.