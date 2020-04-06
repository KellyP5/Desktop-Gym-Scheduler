### Quality Policy

**GitHub Workflow** 
  > We will maintain a develop branch. The develop branch will be the branch where we merge all completed UserStories by a pull request. This will be enforced by github settings. Each user story will have its own branch, each task  will be created on its associated story branch. When a task is complete, it is then PR'd on github (with no review required). This is to ensure that github will mark the branch as 'merged', and better facilitate organization.

>The name of the branch will follow 'US#', where the # will be the one assigned on the taiga board. When a developer assigns themselves a task, they then branch from the UserStory branch, and follows the convention of 'US#-Task#', where the numbers also correspond with the taiga board. 

>We will try to ensure develop doesn't break, but when it does, a seperate branch in the form of Fix-#, where # is the current bug fix.

>Each commit message needs to describe what you are working on with US# and Task # (also include things like "Unit Test", be descriptive). Read the following for more information on commit messages: https://chris.beams.io/posts/git-commit/

>At least one team member should approve a PullRequest, review it, and the reviewer should not be the member who started
>the pull request.

>Only one team member (Git master) is allowed to do the merge into Master (different person for each Sprint)! In case that person is not available for some reason, someone else is allowed to do it, but please provide a comment in the pull request for it. The Git master is the one who should make sure that all Pull Requests are fast forwards and that all pull requests are approved by someone. Thus, talking to the team and making sure Pull Requests are done correctly. Then the Git master should only push the "merge" button on GitHub.

>Branches will not be deleted after they are completed.


**Unit Tests Blackbox** 
  > Unit Tests will be conducted on a task while the task is in the "ready for test" phase on Taiga (Waiting to be merged into develop)
  > All unit tests will be placed in the src.test.java folder.
  > Each member will conduct at least 4 unit tests per sprint. (on your code or other people's code)
  > Whe committing a User Test to a task, you will commit it via 'US# Task# Unit Test: ...'
  > Unit tests will be setup to produce jacoco reports
  > Unit tests will be focused on areas that are no related to UI elements.

 **Unit Tests Whitebox**
  > Unit Tests will be conducted on a task while the task is in the "ready for test" phase on Taiga (Waiting to be merged into develop)
  > All unit tests will be placed in the src.test.java folder.
  > Each member will conduct at least 4 unit tests per sprint. (on your code or other people's code)
  > Whe committing a User Test to a task, you will commit it via 'US# Task# Unit Test: ...'
  > Unit tests will be setup to produce jacoco reports
  > Unit tests will be focused on areas that are no related to UI elements.
  

**Code Review** 
  > Your Code Review policy   

  > Include a checklist/questions list which every developer will need to fill out/answe when creating a Pull Request to the Dev branch. 

  > Include a checklist/question list which every reviewer will need to fill out/anser when conducting a review, this checklist (and the answers of course) need to be put into the Pull Request review.

**Static Analysis**  (due start Sprint 3)
  > Your Static Analysis policy   

**Continuous Integration**  (due start Sprint 3)
  > Your Continuous Integration policy
