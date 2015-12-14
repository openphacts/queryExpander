FROM tomcat:7-jre7

## source

ENV WAR_URL https://github.com/openphacts/queryExpander/releases/download/2.0.4/query.expander.ws.server-2.0.4.war
#ENV WAR_URL http://repository.mygrid.org.uk/artifactory/ops/uk/ac/manchester/cs/openphacts/queryexpander/query.expander.ws.server/2.0.4/query.expander.ws.server-2.0.4.war
ENV WAR_SHA1 1c35338ae32bf2c546dde1a6bb06e0d1e845eb29

ENV LOADER_URL https://github.com/openphacts/IdentityMappingService/releases/download/2.0.3/loader-2.0.3-onejar.jar
#ENV LOADER_URL http://repository.mygrid.org.uk/artifactory/ops/uk/ac/manchester/cs/openphacts/ims/loader/2.0.3/loader-2.0.3-onejar.jar
ENV LOADER_SHA1 9ce81d8921228b728cb82dccc5a395c8f5b85276

# clean
RUN rm -rf webapps/*

# download
ADD $WAR_URL webapps/QueryExpander.war
ADD $LOADER_URL /usr/local/lib/ims-loader.jar

# checksums
RUN echo $WAR_SHA1 webapps/QueryExpander.war >> /tmp/checksums
RUN echo $LOADER_SHA1 /usr/local/lib/ims-loader.jar >> /tmp/checksums
RUN sha1sum -c /tmp/checksums

# permissions
RUN chmod 755 webapps/QueryExpander.war

# scripts and properties
ADD loader /usr/local/bin/loader
RUN chmod 755 /usr/local/bin/loader
RUN mkdir -p /usr/local/tomcat/conf/BridgeDb
ADD local.properties /usr/local/tomcat/conf/BridgeDb/

