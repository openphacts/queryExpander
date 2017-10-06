This project holds the Stand alone Query Expander.

It may be moved into BridgeDB in which case this repository will be closed.

This project depends on the BridgeDb project.
BridgeDB: https://github.com/openphacts/BridgeDb
Validator https://github.com/openphacts/Validator
OpenPhacts Identity Mapping Service https://github.com/openphacts/IdentityMappingService

Instructions for configuration can be found in  
{$IdentityMappingService}README.md 
Please check that AND the other readme and property files that links too.
The Query Expander and Data loader will NOT work if these configurations are incorrect!

Query Expander Properties files
===============================
Properties File Location:
BridgeDB code used to locate Query Expander properties files so
See: {$bridgeDB}README.txt Properties File Location:

===
local.properties
BridgeDB code used to add local properties to Query Expander properties.
See: {$bridgeDB}README.txt local.properties
===

Data Loading

See {$IdentityMappingService}README.md Data Loading section
Remember this depends on the correct configurations! 

====

To run the query expander the easiest way is via the webservice:
	Drop QueryExpander.war into Tomcat

To run QueryExpander without a WS
	HardCodedGraphResolver resolver = new HardCodedGraphResolver();
	SQLAccess sqlAccess = SqlFactory.createSQLAccess();
	URLMapper urlMapper =new SQLUrlMapper(false, sqlAccess, new MySQLSpecific());
	IMSMapper imsMapper = new BridgeDBMapper (resolver.getAllowedNamespaces(), urlMapper);
	QueryExpander queryExpander = new QueryExpanderImpl(imsMapper);
	 
To run using the WS and a client see:
    QueryExpanderClientFactory
    returned QueryExpanderWSClient implements the same QueryExpander interface
