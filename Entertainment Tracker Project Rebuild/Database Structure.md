# Database Structure


```
Database ---> DatabaseInterface (CRUD Ops)
MongoDB ---> NoSQLDatabase  ---> Database
							---> NoSQLInterface
MySQL   ---> SQLDatabase    ---> Database
							---> SQLInterface
```