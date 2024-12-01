1. functional requirements
   - user should be able to create a task
   - user should be able to complete the tasks one at a time
   - user should be able to delete the tasks multiple at a time
   - user should be able to add notes to the task, ordered in the most recent descending
   - user should be able to update a task
   - a set of tasks will exist in a task catalog; user should be able to create task catalogs
   - user should be able to move tasks between catalogs
   - user should be able to delete a catalog, provided the catalog is empty or all the tasks are completed

2. non-functional requirements:
    - the system should be highly <available; reliable; scalable; secure; maintainable>
3. any action related to tasks can only be performed by an authenticated user and all the task details are isolated to the user
 
4. a task can have the following states:
    - new
    - active
    - completed
    - suspended (leads to active)

5. a task can have
   - title
   - description
   - creationTime
   - set of tags
   - due date
   - priority
   - theme
   - status
   - set of appended notes
   - a nullable reference to a parent task and a set of child tasks 

6. the behaviour of a parent task is such that
    - completing a parent task will complete all the child tasks (even if a child task is _suspended_)
    - deleting a parent task will trigger a confirmation dialog in case some ancestor task is not completed
    - the nullable reference to the parent task for a task can be updated
    
7. many further tweaks and granular details can be implemented here, but in the current context, we will keep the architecture basic