FROM tomcat:7-jre7
#ENV WAR_URL https://github.com/openphacts/queryExpander/releases/download/2.0.3/query.expander.ws.server-2.0.3.war
ENV WAR_URL http://repository.mygrid.org.uk/artifactory/ops/uk/ac/manchester/cs/openphacts/queryexpander/query.expander.ws.server/2.0.4-SNAPSHOT/query.expander.ws.server-2.0.4-20151210.133232-46.war
#ENV LOADER_URL https://github.com/openphacts/IdentityMappingService/releases/download/2.0.2/loader-2.0.2.one-jar.jar
ENV LOADER_URL http://repository.mygrid.org.uk/artifactory/ops/uk/ac/manchester/cs/openphacts/ims/loader/2.0.3-SNAPSHOT/loader-2.0.3-20151210.134321-27-onejar.jar
RUN rm -rf webapps/*
ADD $WAR_URL webapps/QueryExpander.war
RUN chmod 755 webapps/QueryExpander.war
ADD $LOADER_URL /usr/local/lib/ims-loader.jar
ADD loader /usr/local/bin/loader
RUN chmod 755 /usr/local/bin/loader
RUN mkdir -p /usr/local/tomcat/conf/BridgeDb
ADD local.properties /usr/local/tomcat/conf/BridgeDb/

