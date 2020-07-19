# StudentManagement

##API
* GET: /api/students :get all student
* POST: /api/students :create new student
* GET: /api/students/{id} :get student by id
* PUT: /api/students/{id} :update student by id
* DELETE: /api/students/{id} :delete student by id 


##Database
* spring.data.mongodb.host=localhost
* spring.data.mongodb.port=27017
* spring.data.mongodb.database=StudentManagement
* access "studentmanagement\src\main\resources\StudentManagement_Student.json" to get mongo data

##Account and authorization
* admin/123: STUDENT_MODULE_ACCESS, STUDENT_CREATE, STUDENT_READ, STUDENT_UPDATE, STUDENT_DELETE
* user/123: STUDENT_MODULE_ACCESS, STUDENT_READ