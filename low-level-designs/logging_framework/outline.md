1. functional requirements
   - the logging interface should be able to pull logs from an ephemeral storage like a message broker hosted memory, via batch reads (cron be setup for every 2 seconds)    
   - the architecture for the main application can grow over time, either we can setup batch reads from multiple fronts, which can grow over time (some queue processing algorithm like mfu, mru, round robin, weighted round robin for batch reads from different fronts), or we can setup a single queue for all the logs, and then have a single batch read from that queue
   - the maximum logNodes which should be available as per the logging interface should be around 100, separate service should write the incoming logs to some blob storage for persistence
   - topics and subtopics, more can be added later, read processes will carry this information to filter out logs from the queue
   - search functionality should be available, which can be used to search logs based on topics, subtopics, and time range
   - the logs can only be viewed by an authenticated user
   - the logs in the interface should be available for a max possible period of 7 days, the blob can have the compressed logs for 6 months followed by a cleanup policy

2. non-functional requirements
   - the system should be highly available
   - the system should be scalable
   - the system should be secure, with all the logs encrypted at rest
   - the system should be performant
   - the system should be reliable, with no data loss
   - the system should be cost-effective
   - the system should be monitored, with a monitoring system in place to monitor the health of the system
   - the system should be maintainable, with a CI/CD pipeline in place to deploy changes to the system
   - the system should be extensible, with the ability to add new features to the system

3. good to have 
   - we can have some statistics and analysis service setup, which can analyze the logs and provide some technical insights
   - we can have a notification system setup, which alerts the prospective users based on the log level or logs from a particular service (hooks can be updated by the user)