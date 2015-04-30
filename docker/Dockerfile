FROM tomcat
ENV WAR_URL https://github.com/openphacts/queryExpander/releases/download/2.0.2/query.expander.ws.server-2.0.2.war
ENV LOADER_URL https://github.com/openphacts/IdentityMappingService/releases/download/2.0.1/loader-2.0.1.one-jar.jar
RUN rm -rf /usr/local/tomcat/webapps/*
ADD $WAR_URL /usr/local/tomcat/webapps/QueryExpander.war
ADD $LOADER_URL /usr/local/lib/ims-loader.jar
ADD loader /usr/local/bin/loader
RUN chmod 755 /usr/local/bin/loader
RUN mkdir -p /usr/local/tomcat/conf/BridgeDb
ADD local.properties /usr/local/tomcat/conf/BridgeDb/

