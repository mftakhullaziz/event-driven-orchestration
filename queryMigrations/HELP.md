    If the ID using the UUID check with query :

    SELECT uuid_generate_v4();
    
    or

    SELECT uuid_generate_v1();
    
    if in postgres do not have a UUID so 
    add extension with the query :
    
    CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
