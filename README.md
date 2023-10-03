# onlineBankingTeam5

If you want to use a separate column for storing the account numbers and not use the `AUTO_INCREMENT` property for the primary key, you can create a custom account number generation mechanism. Here's how you can modify your Spring Boot application to achieve this:

1. **Database Table Setup:**
   In your MariaDB database, create the `user_accounts` table without using `AUTO_INCREMENT` for the account numbers:

   ```sql
   CREATE TABLE user_accounts (
       id INT AUTO_INCREMENT PRIMARY KEY,
       username VARCHAR(255) NOT NULL,
       password VARCHAR(255) NOT NULL,
       account_number VARCHAR(10) UNIQUE
   );
   ```

   In this table structure, we've added an `account_number` column, which will store the custom account numbers.

2. **Entity Class:**
   In your Spring Boot entity class, map the `account_number` field:

   ```java
   @Entity
   public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       private String username;
       private String password;
       @Column(unique = true)
       private String accountNumber; // Store the custom account number here

       // Getters and setters...
   }
   ```

3. **Service Layer:**
   In your service layer, when creating a new user account, you'll need to generate and set the custom account number. You can do this by querying the database for the maximum account number and incrementing it for each new user. Here's a modified `createUser` method:

   ```java
   @Service
   public class UserService {
       @Autowired
       private UserRepository userRepository;
       
       @Autowired
       private EntityManager entityManager;

       public User createUser(String username, String password) {
           User user = new User();
           user.setUsername(username);
           user.setPassword(password);

           // Generate a custom account number
           user.setAccountNumber(generateAccountNumber());

           return userRepository.save(user);
       }

       private String generateAccountNumber() {
           String query = "SELECT MAX(account_number) FROM user_accounts";
           Query maxQuery = entityManager.createNativeQuery(query);
           String maxAccountNumber = (String) maxQuery.getSingleResult();
           int nextNumber = 1;

           if (maxAccountNumber != null) {
               try {
                   nextNumber = Integer.parseInt(maxAccountNumber.substring(2)) + 1;
               } catch (NumberFormatException e) {
                   // Handle parsing error as needed
               }
           }

           return String.format("AC%04d", nextNumber);
       }

       // Other service methods...
   }
   ```

In the `generateAccountNumber` method, we query the database to find the maximum account number, increment it by 1, and format it as "ACXXXX" to ensure uniqueness and sequential ordering.

With these modifications, each new user account created using the `createUser` method will receive a unique and sequentially generated custom account number based on the maximum existing account number in the database.
