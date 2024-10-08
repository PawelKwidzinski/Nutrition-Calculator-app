# Nutrition-Calculator
## General
Web application to search food. Base on the provided name of ingredient and its weight to calculate nutrition in created meals consumed by the user. Data is from [remote API](https://developer.edamam.com/edamam-docs-nutrition-api) and storaged in remote MySQL Database. The user can also create own ingredients and add them to created meals.
## Features:
* Search ingrediend from API
* CRUD for ingrdients and meals
* Filter created meals from/to date
* Security layer -> login page
## Technologies
* Java 11
* Maven
* Spring Boot 2.6.4
* Hibernate
* MySql
* Thymeleaf
* Bootstrap
* HTML, CSS
### Design patterns
* Model-view-controller (MVC)
## Enpoints
```
https://localhost:xxxx/login
https://localhost:xxxx/logout
https://localhost:xxxx/register
https://localhost:xxxx/ingredients/search
https://localhost:xxxx/ingredients/add
https://localhost:xxxx/ingredients/list
https://localhost:xxxx/ingredients/edit/{id}
https://localhost:xxxx/ingredients/delete/{foodId}/{weight}
https://localhost:xxxx/meals/add
https://localhost:xxxx/meals/list
https://localhost:xxxx/meals/edit/{id}
https://localhost:xxxx/meals/delete/{id}
https://localhost:xxxx/meals/find/date

```
## Configuration
application.properties file:
```
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=
spring.jpa.hibernate.ddl-auto=update
server.port=

#API PARAMS
base_url=https://api.edamam.com/api/food-database/v2/parser
app_id_param=app_id
app_id=
app_key_param=app_key
app_key=
ingr_param=ingr
```
## Screenshots
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/1_ingredients.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/2_founded_ingredients.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/3_add_ingredient.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/4_saved_ingredients.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/5_create_meal.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/6_assign_to_meal.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/7_meal.png)
![alt text](https://github.com/PawelKwidzinski/Nutrition-Calculator-app/blob/master/screens/8_saved_meals.png)
