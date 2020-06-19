# Github Workflow

I would like to add a feature, e.g
* to add a dropdown menu to the ui
* to add a function to a microservice
* to debug something
* to ...

I need to
* Branch from develop branch
* Develop **and test** the feature
* Push to GitHub
* Create a pull request in Github


## 1. Branch

```bash
git checkout develop               # Go to develop branch
git fetch origin                   # Fetch any changes of develop branch from origin remote
git reset --hard origin/develop    # Ensure my develop branch is up to date  

git checkout -b <new-feature>      # Create a new branch based on develop and checkout the new branch
# Or equivalently
git branch -b <new-feature>
git checkout <new-feature>
```

**Notes**
* Branch names should be descriptive, e.g profile\_language\_setting, recipe\_elasticsearch\_setup,...
* Features should be small enough for a single developer to complete in no more than a day
* **Never commit changes to develop directly**


## 2. Develop
* code  
* **write tests**
* ``git add .``
* ``git commit .    # Commit messages should be detailed:  what, how, why``
* repeat

**Notes**  
* **Always check you're on the right branch before doing ANYTHING!!!**
* You can ``git push`` already if you want others to see your code but.
* You can also create a pull request early if you'd like help with your code, and you can tag specific people in the pull request.
* Beware of pulling other peoples feature branches, it can easily result in annoying merge conflicts, e.g:
    * Mathias pulls Deniz's feature branch: 'fat\_print\_statement', he changes some code, then pushes.  The next time Deniz pushes he might have a merge conflict to resolve if he has changed the same lines that Mathias has.  
      * I would therefore advise against working on the same exact feature, the features should be small enough for a single developper (i.e stories/tasks/items on the taskboard)


## 3. Pull Request
* ```git push -u origin <new-feature>      # '-u <new-feature>' necessary for the initial push, then 'git push' can be used from then on in the branch```
* Create a pull request for the **feature branch** to the **develop**: **make sure to specify the correct base/compare**
    * **base: develop, compare: \<new-feature\>**
* Solve merge conflicts (on local and push)
    * There will without a doubt be some merge conflicts at some point, tough shit...

**Notes**  
Pull request when finished coding the feature **and** the tests pass  
**or**  
If you'd like help with your code


## 4. Merge
* Only after **discussion** with at least one other team member **and** successful tests, accept the pull request to merge to develop branch on Github  
* **Make sure to delete** the branch **once the feature has been merged**:

```bash
git checkout develop                    # You can't delete the feature branch if you are currently checking it out
git branch -d <new-feature>             # To delete on local, -D if you haven't pulled the updated develop                 
git push origin --delete <new-feature>  # To delete on remote
```


## Note on testing
You can run the full application locally with docker-compose or kubernetes, c.f LOCAL_DEPLOY.md

## Note on your branches
You should only really have the **master**, **develop** and your **\<feature\>** branches on your local machine.  
Avoid ``git pull --all`` and pulling other people feature branches **unless**: they ask you for help, you communicate to avoid merge conflicts, and you stay up to date with eachother with regular pulls/pushes

## Notes on deploying: i.e Merging to master
* The master branch is the source of truth and will contain a functioning application at any given time  
* Merging to master will be a team decision, usually upon successful implementation of a large feature, e.g a new web page, etc...
* All tests must be passing and no bugs present before merging to master
* Merging to master automatically deploys the application in Kubernetes on the VM using Travis and launches the Sonarcloud testing

## Useful resources
**git workflows**  
https://guides.github.com/introduction/flow/  
https://nvie.com/posts/a-successful-git-branching-model/  
https://www.atlassian.com/git/tutorials/comparing-workflows/feature-branch-workflow  

**pull requests**  
https://www.atlassian.com/git/tutorials/making-a-pull-request  

**merge conflicts**  
https://www.atlassian.com/git/tutorials/using-branches/merge-conflicts  
