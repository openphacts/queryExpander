FROM tomcat
ENV URL https://github.com/openphacts/queryExpander/releases/download/2.0.2/query.expander.ws.server-2.0.2.war
RUN rm -rf /usr/local/tomcat/webapps/*
ADD $URL /usr/local/tomcat/webapps/ROOT.war
RUN mkdir -p /usr/local/tomcat/conf/BridgeDb
ADD local.properties /usr/local/tomcat/conf/BridgeDb/

