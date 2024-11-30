1. **requirements gathering**
   - **_functional requirements_**
   - **_non-functional requirements_** --> type of application, read/write ratio, security concerns, design specific restrictions
2. **capacity estimations** --> monthly active users, daily active users. sudden burst events, periodic burst events, latency estimate, constraints, content quality constraints and relaxations
3. **high level design** --> design patterns, basic classes, their inter-relations
4. **db schema design** --> data architecture, beneficial for storage, retrieval, based on the nature of data stored and nature of its consumption, normalization, orchestrated sharding, replication, consensus, acid compliance and implementation
5. **api design** --> service calls architecture --> security tiers (authentication & authorization), exception wrappers, rate limiting, cookie sessions, polling, long polling,


```
- be well versed with er design, uml diagram, robust and failure-ready api architecture
- S.O.L.I.D principles should be followed at every stage
- identify and use the design patterns wherever you deem applicable to ensure S.O.L.I.D principles are properly followed and the system architecture is scalable, robust and maintainable
```