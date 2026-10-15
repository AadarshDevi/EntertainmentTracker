# Database Structure


```
Database ---> DatabaseInterface (CRUD Ops)
MongoDB ---> NoSQLDatabase  ---> Database
							---> NoSQLInterface
							---> ? WeblinkInterface
MySQL   ---> SQLDatabase    ---> Database
							---> SQLInterface
							---> ? WeblinkInterface
```