# Deliverable Information
   > Please include your answers below in a good format so it is easy for me to see. For answers to questions please use these Blockquotes. Make sure you also check the kickoff document for more details.

## 1: Basic Information (needed before you start with your Sprint -- Sprint Planning)

**Topic you chose:** Gym Scheduler

**Sprint Number:** 2

**Scrum Master**: Kelly Petrone

**Git Master**: Rita Barrilleaux

### Sprint Planning (For Sprint 1-4)
Document your Sprint Planning here. Also check the kickoff document for more details on what needs to be done. This is just the documentation. 

**Sprint Goal:** Focus on separating out the three types of user accounts, implementing the UI's/backend for each of those according to design documents, and eliminating any unused GUI elements from the previous Memoranda 

**How many User Stories did you add to the Product Backlog:**  10

**How many User Stories did you add to this Sprint:** 7

**Why did you add exactly these US?**

> We decided to create User Stories that worked toward our Sprint2 goal, which focused mainly on completing the front end GUI elements
of our program. The User Stories we added will help us in the future during Sprint3, when we will mainly have the backend to work
on, as well as putting the two together.

**Why do you think you will get them done? (details)**

> We spoke in depth about our time and work estimation this sprint, as well as how we managed our time last sprint. From this we
decided the amount of work in these User Stories is enough for us to complete this sprint, but not too much. We also took into account
the fact that we will have to unit test the work that we do.

**Do you have a rough idea what you need to do? (if the answer is no then please let me know on Slack)**

> Yes, we know what we need to do. We need to focus on getting the UI elements finished this sprint, as well as unit testing.



## 2: During the Sprint
> Fill out the Meeting minutes during your Sprint and keep track of things. Update your Quality policies when needed, as explained in the lectures and in the Quality Policy documents on Canvas. 
I would also advise you to already fill out the Contributions section (End of sprint) as you go, to create less work at the end.

### Meeting minutes of your Daily Scrums (3 per week, should not take longer than 10 minutes):
> Add as many rows as needed and fill out the table. (Burndown starts with Sprint 2 and Travis CI starts with Sprint 3, not needed before that)

| Date  | Attendees  |Minutes   | Burndown Info | TravisCI info | Additional Info  |
|---|---|---|---|--|--|
| 4/9  | Kelly P, Kelly E, Kevin W, Alex M, Rita B, Kevin S asynchronously through text   | Went through each task in the backlog and made sure it was valid to stay this sprint and that none overlapped. Evaluated the board for distribution of points. Started the sprint. | 0%  | N/A | N/A |
| 4/11  | Kelly P, Kelly E, Kevin W, Kevin S, Alex M, Rita B  | **Rita** will work on the Schedule GUI/layout/popup windows - No blockers. **Kevin W** is waiting on the database implementation to be done before starting tasks. **Kevin S** has designed the DB tables/relations and got feedback from the team. Created POJOs for DB and SQL queries to create the DB and tables with relations. Created the actual DB and a test DB for unit testing. Updated the gradle file to include SQLite and other updates to make IntelliJ more smooth. Will work on creating dummy data for test DB and creating on the interface for the DB that we'll use. Should be done today. Only 'blocker' is that SQLite doesn't have a datetime data type, but found a workaround. **Alex M** will work on the Agenda page. DB is the only blocker. **Kelly P** is also waiting for the DB to be finished before starting tasks. **Kelly E** Updated small things on the Login GUI - Masking the password with * 's and changing Username to Email. Updated Resources on the left to User Management. Waiting on DB to be done. | 0%  | Develop build passed | N/A  |
| 4/14  | Kelly P, Kelly E, Kevin W, Kevin S, Alex M, Rita B  | Discussed small changes to the Code Review checklists. **Rita** has been working on the Class Schedule landing page with all the buttons according to the design documents. **Kevin W** Implemented the User Management landing page with buttons/display. Removed the Project Bar from the top. Added tests to the database. Working on creating an interface 'middle layer' that all database functionality can be encapsulated in - Gym class. Working on retrieving the user's role from the db when a user is logged in. **Kevin S** Finished the database - basic CRUD operations & queries. Added some unit tests. Will work on the individual assignment and code reviews/PRs because HE'S DONE SO MUCH THIS SPRINT :) **Alex M** Added a logout button. Working on the Agenda landing screen. Will work together with Rita since they're working on the same parts of the code/GUIs. No blockers. **Kelly P** Finished the account creation GUI - masked the user's password with * 's, verifies correct information. Working on some newly added tasks for implementing pop up windows for buttons in the schedule view. No blockers. **Kelly E** Masked the user's password with * 's on the Login GUI, verified account information with database. Will continue to work on pop ups for incorrect password/no account found in Login GUI, and will implement pop up window for buttons on the Schedule page. No blockers.| 28%  | Develop build passed  | N/A  |
| 4/16 | Kelly P, Kelly E, Kevin S, Rita B, Alex M | **Rita** is working on finishing up the GUI for the admin schedule page. Finishing the buttons. No blockers. **Kevin S** Added some functionality to the database to help other team members with their User Stories. Will work on code reviews, helping with bugs, and PRs. No blockers. **Alex M** Working on the agenda page, displaying the classes schedule, and displaying the information of the user that's currently logged in. No blockers. **Kelly P** Has been and will continue working on unit testing. No blockers. **Kelly E** Finishing up the Login GUI and adding unit tests. Going to temporarily disable user authentication to make it easier for others to work on program (will add back before submitting deliverable). Will work on the classes GUI when Rita is finished getting it set up. No blockers. | 39% | Develop build passed | N/A |
| 4/18 | Kelly P, Kelly E, Kevin S, Kevin W, Rita B, Alex M | General reminder to fill in Deliverable2 with solo contributions and links. **Rita** Finished the pop up dialogs on the class schedule page. No blockers. **Kevin S** Working on figuring out database constraint issues with SQLite. No blockers. **Kevin W** Working on tests. One test will make the build fail, we will have to comment it out. Will take on new tasks tonight. **Alex M** Working on the Agenda page and adding trainer's belt to top of the page. Needs the ability to see who's currently logged in. **Kelly P** Working on the Classes page. Finishing up tests. No blockers. **Kelly E** Finishing up the Edit User functionality in User Management. Waiting for other PRs to be reviewed/approved. | 57% | Develop build passed | N/A |

### Meeting Summary:

> Add rows as needed and add the number how many meetings they attended:

   Kelly Petrone : 5

   Kelly Ellis : 5
   
   Kevin Wilkinson : 4
   
   Kevin Somers : 5
   
   Alex Mack : 5
   
   Rita Barrilleaux : 5
   


## 3: After the Sprint

### Sprint Review

**Screen Cast link**: https://youtu.be/OASRYUmUpb4

**What do you think is the value you created this Sprint?**

> Accomplishing the initial schedule system in the UI was a big step forward towards meeting the requirements. The Login GUI also authenticates users. The team implemented a database and the program communicates with it to save and retreive data.

**Do you think you worked enough and that you did what was expected of you?**

> I think our team has gone far above the requirements or what was expected of us this sprint. Not that we accomplished tasks outside of the User Stories, but went further into the User Stories that we did initially develop. We also met most of the Tasks on our sprint log. We left a lot of room going into sprint 3 to focus on the smaller things and to implement different levels of accounts based on the requirements.

**Would you say you met the customers’ expectations? Why, why not?**

> For the stories that we delivered on and completed we met all of the acceptance criteria, therefore meeting the customers expectations and requirements. There was one User Story that we did not finish, thus not meeting 100% of the established requirements this sprint.

### Sprint Retrospective

**Did you meet your sprint goal?**

> We met about 95% of our sprint goal. The only part of our sprint goal that we did not finish is the implementation of buttons on the Classes UI page. This was due to the time it took us to implement the classes page. Otherwise, we met all the elements of our sprint goal.

- Sprint Goal
   - Focus on separating out the three types of user accounts, implementing the UI's/backend for each of those according to design documents, and eliminating any unused GUI elements from the previous Memoranda  

**Did you complete all stories on your Spring Backlog?**

> We completely all but on story in the Sprint Backlog.

**If not, what went wrong?**

> One of the stories that we had in our sprint backlog, relied heavily on implementing the largest task of the sprint. If we had known this before hand, we likely would have saved this User Story for Sprint 3. There was a large learning curve going into Sprint 2 on how we were going to manipulate memoranda to meet our requirements which also played a role.

**Did you work at a consistent rate of speed, or velocity? (Meaning did you work during the whole Sprint or did you start working when the deadline approached.)**

> In general we followed the linear path expected for closing stories, as shown on our taiga board. We did slow down towards the end of the sprint as we started to narrow down the tasks remaining.

**Did you deliver business value?**

> We delivered on features that largely impact the customers ability to see classes and to manage user accounts in the system. They can view classes in a database but aren't yet able to join them. 

**Are there things the team thinks it can do better in the next Sprint?**

> Part way through the sprint we realized that we weren't pulling tasks into develop nearly enough. This caused issues with knowing how much progress people made on certain tasks. Also, initially we were waiting until a User Story was completely done to merge it in. This led to issues of overlap between different user stories. Also, we should narrow down the User Stories and have less tasks per User Story. 

**How do you feel at this point? Get a pulse on the optimism of the team.**

> We feel confident going into the final sprint to meet all of the User Requirements. We accomplished a large percentage of the requirements this sprint so we can produce a more polished product. 

### Contributions:

#### Team member: Kelly Ellis

  **Links to GitHub commits with main code contribution (up to 5 links):

    - https://github.com/amehlhase316/Neujahrskranz/commit/60f0ddded6219f5f7aa723da7a4a019aded9fda2
    - https://github.com/amehlhase316/Neujahrskranz/tree/US-43
    - https://github.com/amehlhase316/Neujahrskranz/commit/62d9ab9affe1d8eb2e4a6edf01b4197449332f0f
    - https://github.com/amehlhase316/Neujahrskranz/commit/a4bbc827d238e125c96390b0c6cff1ce1b81d2df
    - https://github.com/amehlhase316/Neujahrskranz/commit/f83c3fdc31d5478e50f915f246a5d472f37a239f

   **GitHub links to your Unit Tests (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/commit/1562e876cdd08c04a97ea3170c799775ed9b0fd8
    - https://github.com/amehlhase316/Neujahrskranz/commit/5165f5a87201354a0710a835afd18e528bf508ec
    - https://github.com/amehlhase316/Neujahrskranz/commit/b12f67066af923062726e5dd017124c29050f654

  **GitHub links to your Code Reviews (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/69
    - https://github.com/amehlhase316/Neujahrskranz/pull/66

  **How did you contribute to Static Analysis:

    - https://github.com/amehlhase316/Neujahrskranz/commit/7e6d507f1fedc3e141e86ef2d381a8e5785eaf4e
    - https://github.com/amehlhase316/Neujahrskranz/commit/20875a2f3d7877920bc2d75d2f011802730f9882
 
 **What was your main contribution to the Quality Policy documentation?:

    - I created the Code Review checklists for the Reviewer and the Developer. I also was in
    the meeting we had during sprint planning when we discussed how we would handle unit testing
    and static analysis.
    
 
#### Team member: Kelly Petrone:
  **Links to GitHub commits with main code contribution (up to 5 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/97
    - https://github.com/amehlhase316/Neujahrskranz/pull/81
    - https://github.com/amehlhase316/Neujahrskranz/pull/106
    - https://github.com/amehlhase316/Neujahrskranz/pull/107
    - https://github.com/amehlhase316/Neujahrskranz/pull/124

   **GitHub links to your Unit Tests (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/100
    - https://github.com/amehlhase316/Neujahrskranz/pull/109
  
  **GitHub links to your Code Reviews (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/105
    - https://github.com/amehlhase316/Neujahrskranz/pull/90
    - https://github.com/amehlhase316/Neujahrskranz/pull/105
 
 **What was your main contribution to the Quality Policy documentation?:

    - Served as the Scrum Master this sprint. Helped negotiate the terms with the team of what should be focused on in our Unit Tests. Also discussed with team members on several occassions on updating these standards as our requirements changed. Also filled out the portions required for different types of unit testing.
    
    
#### Team member Kevin Wilkinson:
  **Links to GitHub commits with main code contribution (up to 5 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/120/commits/f55bca829e186a624836e94868599d94c7f00ab4
    - https://github.com/amehlhase316/Neujahrskranz/commit/5d8440ef68314b1c21d8f4d33ad9f9f13764ea09
    - https://github.com/amehlhase316/Neujahrskranz/commit/a02fbb47dd21b8da0ec31b7ed15aaaf0f8f3b2ce
    - https://github.com/amehlhase316/Neujahrskranz/commit/0ef32968303853d5a8fa18585d503aa4e3a6dc2b
    - https://github.com/amehlhase316/Neujahrskranz/commit/9c16a4958158315ca342ecc5018ac7c6b4c6ef73

   **GitHub links to your Unit Tests (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/commit/0172394add79ca717b9969b8ab716b918eb08a24
    - https://github.com/amehlhase316/Neujahrskranz/commit/31852298aa756d39b15a57411a5ebd69c28d6bbd
    - https://github.com/amehlhase316/Neujahrskranz/commit/941a3d18ceaf1f49031a5dfefce75abee861daf8
    - https://github.com/amehlhase316/Neujahrskranz/commit/ef70a92ae95125f4998f3f71687ba18414b9a5e2

  **GitHub links to your Code Reviews (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/99
    - https://github.com/amehlhase316/Neujahrskranz/pull/93
    - https://github.com/amehlhase316/Neujahrskranz/pull/101
    - https://github.com/amehlhase316/Neujahrskranz/pull/109
    - https://github.com/amehlhase316/Neujahrskranz/pull/113
 
 **What was your main contribution to the Quality Policy documentation?:

    - https://github.com/amehlhase316/Neujahrskranz/commit/753f074dadab93fed95488a76cf6ec77532f3135

  
  #### Alexander Mack:
  **Links to GitHub commits with main code contribution (up to 5 links):

    - https://github.com/amehlhase316/Neujahrskranz/commit/31016241b074985025976dcff79e3f2ebf9cf6c8
    - https://github.com/amehlhase316/Neujahrskranz/commit/92ba43f7d310792d0faf7beff18b27df56008338
    - https://github.com/amehlhase316/Neujahrskranz/commit/6da4f60742f493435856a5c5f02d872a96767be1

   **GitHub links to your Unit Tests (up to 3 links):

    -  https://github.com/amehlhase316/Neujahrskranz/commit/f804ea9a15f703379d6d8e52003932cc51e73c23
    -  https://github.com/amehlhase316/Neujahrskranz/commit/51908dec12016024a8efadfc2ccca67a52245ea5
    -  https://github.com/amehlhase316/Neujahrskranz/commit/6fc0010390c3a0b70b4eaa741348d58dc11b9ada
    -  https://github.com/amehlhase316/Neujahrskranz/commit/d6cf76b51573e851d36bc853bfc65d0b77b8865a



  **GitHub links to your Code Reviews (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/117
    - https://github.com/amehlhase316/Neujahrskranz/pull/106

 
 **What was your main contribution to the Quality Policy documentation?:

    - After realizing that most GUI code could not be tested, and seeing that our previous versions of QP were unrealistic worked with team to come up with a more realistic approach to testing which is our current QP. 
    
     
  #### Team member: Margaryta Barrilleaux:
  **Links to GitHub commits with main code contribution (up to 5 links):
    - https://github.com/amehlhase316/Neujahrskranz/pull/105
    - https://github.com/amehlhase316/Neujahrskranz/pull/102
 
   **GitHub links to your Unit Tests (up to 3 links):
    - worked on gui, no unit testing for that 
  **GitHub links to your Code Reviews (up to 3 links):
    - https://github.com/amehlhase316/Neujahrskranz/pull/110
    - https://github.com/amehlhase316/Neujahrskranz/pull/112
 
 **What was your main contribution to the Quality Policy documentation?:
    - Participated in every meeting and discussion about quality policy, offered changes to them so we could do task merges into develop before the user story is complete if that task is important for other user story in order to make our progress faster and more efficient.
    
#### Team member Kevin Somers:
  **Links to GitHub commits with main code contribution (up to 5 links):

    - https://github.com/amehlhase316/Neujahrskranz/commit/a9c1f8bc88889a723049595c0181881d047f22b9
    - https://github.com/amehlhase316/Neujahrskranz/commit/be810f7da7638f6c55869a6956a9d3e4f3c5ea67
    - https://github.com/amehlhase316/Neujahrskranz/commit/90e44abd7787825d9d4e8dbc80c24e954ea75183

   **GitHub links to your Unit Tests (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/commit/f8a61428fed5378104fc6024c6c94210f9126346
    - (two tests on this one) https://github.com/amehlhase316/Neujahrskranz/commit/e7a922e2a1532929ce8b2cf0bfea90fa2aab0848
    - https://github.com/amehlhase316/Neujahrskranz/commit/5e4684d7f63814a8c088074e00e88bdd30b7034b

  **GitHub links to your Code Reviews (up to 3 links):

    - https://github.com/amehlhase316/Neujahrskranz/pull/97
    - https://github.com/amehlhase316/Neujahrskranz/pull/98
    - https://github.com/amehlhase316/Neujahrskranz/pull/100
 
 **What was your main contribution to the Quality Policy documentation?:

    - Asked professor for permission to change some of the developer review checklist.  Now our checklist checks for 120 characters intead of 100.  We discussed changing the sentence about unit testing to check for testing where it makes sense, because some branches/commits are GUI related and dont make sense to require a test for review.  
    
## 4: Checklist for you to see if you are done
- [x] Filled out the complete form from above, all fields are filled and written in full sentences
- [x] Read the kickoff again to make sure you have all the details
- [x] User Stories that were not completed, were left in the Sprint and a copy created
- [x] Your Quality Policies are accurate and up to date
- [ ] **Individual** Survey was submitted **individually** (create checkboxes below -- see Canvas to get link)
  - [x] Kevin W
  - [ ] Kevin S
  - [x] Kelly P
  - [x] Kelly E
  - [x] Rita B
  - [x] Alex M
- [x] The original of this file was copied for the next Sprint (needed for all but last Sprint where you do not need to copy it anymore)
  - [ ] Basic information (part 1) for next Sprint was included (meaning Spring Planning is complete)
  - [x] All User Stories have acceptance tests
  - [ ] User Stories in your new Sprint Backlog have initial tasks which are in New
  - [x] You know how to proceed
 
