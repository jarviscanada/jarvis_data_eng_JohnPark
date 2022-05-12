# Linux Cluster Monitoring Agent
This project is under development. Since this project follows the GitFlow, the final work will be merged to the master branch after Team Code Team.

# Introduction
A Linux cluster is a connected array of Linux computers or nodes that operate together to function as a single system. Each of the nodes or computers is connected through a local area network. For the sake of managing multiple machines, each machine needs to share information and record the shared information in a database.

This project aims to build an automated software that can record the hardware specification of each node and monitor its resource usage of them. The software will store the retrieved data from each node in an RDBMS database. This software will help the users of a Linux cluster analyze the hardware usages of different nodes and plan to allocate the resource economically. 


# Quick Start
First navigate to root directory of this project. The root directory of this project is `linux_sql` directory

- Start a psql instance using psql_docker.sh
```
bash ./scripts/psql_docker.sh create [username] [password]
``` 

- [side] To start or stop the psql instance docker
```
bash ./scripts/psql_docker.sh [start|stop] jrvs-psql 
```

- Create tables using ddl.sql
```
psql -h localhost -U postgres -d host_agent -f sql/ddl.sql
```

- Insert hardware specs data into the DB using host_info.sh
```
bash ./scripts/host_info.sh [psql hostname] [psql port] [database name] [psql user] [psql password]
```

- Insert hardware usage data into the DB using host_usage.sh
```
bash ./scripts/host_usage.sh [psql hostname] [psql port] [database name] [psql user] [psql password]
```

- Crontab setup
```
crontab -e
```

- After directed to vim editor, input the task crontab will run <br/>
``` 
* * * * * bash ./scripts/host_usage.sh [psql hostname] [psql port] [database name] [psql user] [psql password]
```


# Implementation
In order to structure and implement this project, a simplified version of the Development Lifecycle (SDLC) was used. This project was divided into four main phases – design, implementation, test, and deployment/DevOps. 

In addition to dividing the project into different phases, agile development methodology was used to perform day-to-day software development. The frameworks generated out of Agile principles and utilized in this project are Scrum and Kanban.

The software is composed of two components – a database, and a monitoring agent. 

To establish a database in the system, a docker container which contains a PostgreSQL instance was used. 
-	The `psql_docker.sh` automates the generation of PostgreSQL instance and running in the background of the machine. 
-	The `ddl.sql` automates the database initialization and data tables set up.
  
The monitoring agent is composed of two scripts and a crontab. 
-	The two scripts, `host_info.sh` and `host_usage.sh` are used to gather data about host hardware specification and resource usage info. 
-	The crontab is put in operation to automatically run `host_info.sh` and `host_usage.sh` every minute to accumulate data.


## Architecture
Diagram showing overall architecture and design of LCA project.
<p align="center">
    <img  src="./assets/architecture_diagram.png" alt="">
</p>


## Scripts
- psql_docker.sh - Instantiates docker container containing PSQL database <br/> 
usage:
``
bash ./scripts/psql_docker.sh create|start|stop db_username db_password
``
- host_info.sh <br/>
usage:
```
bash ./scripts/host_info.sh psql_host psql_port db_name psql_user psql_password
```
- host_usage.sh <br/>
usage:
```
bash ./scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
- crontab <br/>
usage:
```
crontab -e 
* * * * * bash /pwd/scripts/host_usage.sh psql_host psql_port db_name psql_user psql_password
```
- queries.sql <br/>
usage:
```
psql -h host_name -p psql_port -U psql_user  -p psql_password -d host_agent -f /sql/queries.sql
```



## Database Modeling
- `host_info`

| Column Name      | Data Type | Constraint            |
|------------------|-----------|-----------------------|
| id               | serial    | not null, primary key |
| hostname         | varchar   | not null, unique      |
| cpu_number       | integer   | not null              |
| cpu_architecture | varchar   | not null              |
| cpu_model        | varchar   | not null              |
| cpu_mhz          | real      | not null              |
| l2_cache         | integer   | not null              |
| total_mem        | integer   | not null              |
| timestamp        | timestamp | not null              |


- `host_usage`

| Column Name    | Data Type | Constraint            |
|----------------|-----------|-----------------------|
| timestamp      | timestamp | not null              |
| host_id        | integer   | not null, foreign key |
| memory_free    | integer   | not null              |
| cpu_idle       | integer   | not null              |
| cpu_kernel     | integer   | not null              |
| disk_io        | integer   | not null              |
| disk_available | integer   | not null              |


# Test
How did you test your bash scripts and SQL queries? What was the result?

# Deployment
How did you deploy your app? (e.g. Github, crontab, docker)

# Improvements
- Although it is MVP, it is crucial that communication do happen across the network switch and how database can retrieve data from network switch. It could have been better if we had another host with network switch to simulate this situation   
- Need better test cases which examine how the app will behave in various cases
- Recording hardware usage every minute and storing in database may take up too much space in the database. It would be better to find a way to condense those information. For example, maybe software can average up the usage over 15 minutes
- Could use `/tmp/host_usage` to somehow inform the system if there was an error in inserting the usage data to the database
