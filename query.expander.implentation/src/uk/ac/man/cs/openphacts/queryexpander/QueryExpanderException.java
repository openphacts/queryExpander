package uk.ac.man.cs.openphacts.queryexpander;

import org.apache.log4j.Logger;

/**
 *
 */
public class QueryExpanderException extends QueryExpansionException {

    static final Logger logger = Logger.getLogger(QueryExpanderException.class);

    public QueryExpanderException(String message) {
        super(message);
        logger.error(message, this);
    }
    
    public QueryExpanderException(String message, Exception ex) {
        super(message, ex);
        logger.error(message, ex);
    }
}
