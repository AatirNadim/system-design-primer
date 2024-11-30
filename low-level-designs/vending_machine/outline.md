1. functional requirements
   - user should be able to select an available product, and its count if it is possible w.r.t the available stock
   - user initiates the payment processing, putting in money in three different formats: cash, digital payment, and cards
   - upon payment success, the required product is dispensed from the machine in the required count
   - the vending machine updates the inventory

2. non-functional requirements
    - the system should be easy to use
    - the system should return the change if the user has put in more money than the product cost
    - the system should show upcoming inventory and upcoming count of existing stock if the upcoming inventory stats are available 

3. machine will be used by one customer at a time, so no concurrent requests to be addressed

4. in the event of failure at any point, the payment made by the customer is rolled back