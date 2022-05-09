/*
# ddl.sql pseudocode/steps
# you can assume database is already created for you

# Execute ddl.sql script on the host_agent database againse the psql instance
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
*/

/*
1. (optional) switch to `host_agent`
*/
\c host_agent


/*
2. create `host_info` table if not exist
    - add more columns 
    - primary key constraint
    - unique hostname constraint
*/
CREATE TABLE IF NOT EXISTS PUBLIC.host_info 
( 
    id SERIAL NOT NULL, 
    hostname VARCHAR UNIQUE NOT NULL, 
    cpu_number INTEGER NOT NULL,
    cpu_architecture VARCHAR NOT NULL,
    cpu_model VARCHAR NOT NULL,
    cpu_mhz REAL NOT NULL,
    L2_cache INTEGER NOT NULL,
    total_mem INTEGER NOT NULL,
    timestamp TIMESTAMP NOT NULL, 
    PRIMARY KEY (id)
); 


/*
3. create `host_usage` table if not exist
    - add more columns
    - add foreign key constraint
*/
CREATE TABLE IF NOT EXISTS PUBLIC.host_usage
(
    "timestamp" TIMESTAMP NOT NULL,
    host_id SERIAL NOT NULL,
    memory_free INTEGER NOT NULL,
    cpu_idle INTEGER NOT NULL,
    cpu_kernel INTEGER NOT NULL,
    disk_io INTEGER NOT NULL,
    disk_available INTEGER NOT NULL,
    FOREIGN KEY(host_id) REFERENCES host_info(id)
);

