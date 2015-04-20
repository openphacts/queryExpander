# IdentityMappingService docker image

Docker image of the [Open PHACTS](http://www.openphacts.org/) service
[queryExpander](https://github.com/openphacts/queryExpander), which includes
the
[IdentityMappingService](https://github.com/openphacts/IdentityMappingService)
(IMS) and the [VoID Validator](https://github.com/openphacts/Validator).

This document will call this service "IMS" even though it includes all of the above.

## Usage

To run the IMS, and expose it on port `8081`, use:

    docker run --name ims --link mysql-for-ims:mysql -p 8081:8080 -d openphacts/ops/identitymappingservice

You can check the Tomcat logs using:

    docker logs ims

The server is initialized when the logs say something along the line of:

    20-Apr-2015 10:41:44.590 INFO [localhost-startStop-1] org.apache.catalina.startup.HostConfig.deployWAR Deployment of web application archive /usr/local/tomcat/webapps/ROOT.war has finished in 29,575 ms
    20-Apr-2015 10:41:44.611 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["http-nio-8080"]
    20-Apr-2015 10:41:44.645 INFO [main] org.apache.coyote.AbstractProtocol.start Starting ProtocolHandler ["ajp-nio-8009"]
    20-Apr-2015 10:41:44.649 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in 29838 ms

At this point you can access the IMS service at [http://localhost:8081/](http://localhost:8081/) (or equivalent host/port).

## Data loading

This docker image assumes there will be a [mySQL
server](https://www.mysql.com/) on its virtual hostname `mysql`, using the
database `ims` with username `ims` and password `ims`.
(You can customize these by modifying [local.properties](local.properties)
and rebuilding this image.)

The simplest way to achieve this is to `--link` to a [mysql docker image](https://registry.hub.docker.com/_/mysql/),
which we will call `mysql-for-ims` and ask it to create the `ims` database for us. (You can replace `$(pwgen)` with 
your desired mySQL root password, e.g. `-e MYSQL_ROOT_PASSWORD=fish`).

    docker run --name mysql-for-ims -e MYSQL_ROOT_PASSWORD=$(pwgen) \
      -e MYSQL_DATABASE=ims -e MYSQL_USER=ims -e MYSQL_PASSWORD=ims -d mysql

Next you need to load it either with the latest [Open PHACTS IMS mysql
dump](http://data.openphacts.org/1.5/ims/)
or load it manually from configured linksets. Below we'll use the mySQL dump as it 
requires the least setup (although it is less flexible.)

    docker run -it --link mysql-for-ims:mysql --rm mysql sh -c \
      'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
