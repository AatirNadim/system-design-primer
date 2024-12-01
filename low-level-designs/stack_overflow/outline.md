1. functional requirements:
   - user can ask a question
   - every isolated content instance has a question and the answerNode(s) as array
   - every question can have tags associated with it
   - every answer has a datetime, the writer info and the comments added sorted in most recent at the top, any authenticated user can add comments
   - authenticated users can upvote or downvote an answer
   - the user who put up the question can mark an answer as accepted
   - on the front page, people can search for a question and see a list of questions, which can be sorted in various ways (most recent, most answered, most upvoted and so forth)
   - users can upvote another user
   - user can only add a single answer to a question
   - every user has a profile page that shows their information, their questions, answers, and votes

2. without authentication, users can view questions and answers but they cannot pose a question or add an answer or vote or comment on an answer

3. good to have a related questions pane, a trending questions pane

4. non-functional requirements:
   - the system should be highly <available; reliable; scalable; secure; maintainable>