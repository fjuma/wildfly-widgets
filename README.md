### Invoking secured EJB methods via a servlet

1. Take a look at the ```configure-elytron.cli``` file that creates an Elytron filesystem-based security realm and adds the
configuration necessary to secure our application using Elytron. Notice that our security realms has two users, ```alice```
and ```bob```, with passwords ```alice123+``` and ```bob123+```, respectively. Also notice that alice has both the ```employee```
and ```admin``` roles but bob only has the ```employee``` role.

```
$WILDFLY_HOME/bin/jboss-cli.sh --connect --file=configure-elytron.cli
```

2. This example consists of two servlets that an invoke an EJB (see ```example.InventoryServlet``` and ```example.AddServlet```).
Notice that ```InventoryServlet``` invokes an unsecured EJB method called ```getProducts```. ```AddServlet``` invokes an
EJB method, ```addProduct```, that requires ```admin``` role.

3. Build and deploy the application:

```
mvn clean install wildfly:deploy
```

4. First access the application as ```bob```. Try viewing the inventory. Then try to add a product. Since adding a product
requires "admin" role, you'll see an error message. Then access the application as ```alice```. Since ```alice``` has ```admin```
role, you'll be able to add a product successfully.

```
http://localhost:8080/wildfly-widgets
```

