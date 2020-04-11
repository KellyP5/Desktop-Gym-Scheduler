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

> Answer the questions below about your Sprint Planning?

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
| 4/11  | Kelly P, Kelly E, Kevin W, Kevin S, Alex M, Rita B  | **Rita** will work on the Schedule GUI/layout/popup windows - No blockers. **Kevin W** is waiting on the database implementation to be done before starting tasks. **Kevin S** has designed the DB tables/relations and got feedback from the team. Created POJOs for DB and SQL queries to create the DB and tables with relations. Created the actual DB and a test DB for unit testing. Updated the gradle file to include SQLite and other updates to make IntelliJ more smooth. Will work on creating dummy data for test DB and creating on the interface for the DB that we'll use. Should be done today. Only 'blocker' is that SQLite doesn't have a datetime data type, but found a workaround. **Alex M** will work on the Agenda page. DB is the only blocker. **Kelly P** is also waiting for the DB to be finished before starting tasks. **Kelly E** Updated small things on the Login GUI - Masking the password with * 's and changing Username to Email. Updated Resources on the left to User Management. Waiting on DB to be done. | 0%  | N/A | N/A  |
|   |   |   |   |  |  |

### Meeting Summary:

> Add rows as needed and add the number how many meetings they attended:

   Kelly Petrone : 2

   Kelly Ellis : 2
   
   Kevin Wilkinson : 2
   
   Kevin Somers : 2
   
   Alex Mack : 2
   
   Rita Barrilleaux : 2
   


## 3: After the Sprint

### Sprint Review

**Screen Cast link**: Your link

> Answer the following questions as a team. 

**What do you think is the value you created this Sprint?**

> Your Answer

**Do you think you worked enough and that you did what was expected of you?**

> Your Answer

**Would you say you met the customers’ expectations? Why, why not?**

> Your Answer

### Sprint Retrospective

> Include your Sprint retrospective here and answer the following questions in an evidence based manner as a team (I do not want each of your individuals opinion here but the team perspective). By evidence-based manner it means I want a Yes or No on each of these questions, and for you to provide evidence for your answer. That is, don’t just say "Yes we did work at a consistent rate because we tried hard"; say "we worked at a consistent rate because here are the following tasks we completed per team member and the rate of commits in our Git logs."

**Did you meet your sprint goal?**

> Your Answer

**Did you complete all stories on your Spring Backlog?**

> Your Answer

**If not, what went wrong?**

> Your Answer

**Did you work at a consistent rate of speed, or velocity? (Meaning did you work during the whole Sprint or did you start working when the deadline approached.)**

> Your Answer

**Did you deliver business value?**

> Your Answer

**Are there things the team thinks it can do better in the next Sprint?**

> Your Answer

**How do you feel at this point? Get a pulse on the optimism of the team.**

> Your Answer

### Contributions:

> In this section I want you to point me to your main contributions (each of you individually). Some of the topcs are not needed for the first deliverables (you should know which things you should have done in this Sprint, if you don't then you have probably missed something):

#### Team member A:
  **Links to GitHub commits with main code contribution (up to 5 links):

    - link1
    - link2

   **GitHub links to your Unit Tests (up to 3 links):

    - link1
    - link2

  **GitHub links to your Code Reviews (up to 3 links):

    - link1
    - link2

  **How did you contribute to Static Analysis:

    - link1
    - link2
 
 **What was your main contribution to the Quality Policy documentation?:

    - info
  
## 4: Checklist for you to see if you are done
- [ ] Filled out the complete form from above, all fields are filled and written in full sentences
- [ ] Read the kickoff again to make sure you have all the details
- [ ] User Stories that were not completed, were left in the Sprint and a copy created
- [ ] Your Quality Policies are accurate and up to date
- [ ] **Individual** Survey was submitted **individually** (create checkboxes below -- see Canvas to get link)
  - [ ] Team member 1
  - [ ] Team member 2
- [ ] The original of this file was copied for the next Sprint (needed for all but last Sprint where you do not need to copy it anymore)
  - [ ] Basic information (part 1) for next Sprint was included (meaning Spring Planning is complete)
  - [ ] All User Stories have acceptance tests
  - [ ] User Stories in your new Sprint Backlog have initial tasks which are in New
  - [ ] You know how to proceed
