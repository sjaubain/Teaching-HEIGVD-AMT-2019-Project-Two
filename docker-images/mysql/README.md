To link the mysql docker container locally with payara, run the server and then go to JDBC Connection Pools and add a new one (affinitiesNetwork_pool) with the following advanced properties :

![](../../img/jdbc_conf.png)

and then run the following command :

```
docker build .
```

```
docker run -d -p 3306:3306 -e MYSQL_ROOT_PASSWORD=adminpw <image_name>
```

to map the ports and give root password to the container. Add a JDBC resource linked to the connection pool (jdbc/affinitiesNetwork) and ping should succeed (make sure that the jdbc driver .jar is under /lib payara folder).

Note : Use this manipulation during project development to help testing database connection (click on script).
