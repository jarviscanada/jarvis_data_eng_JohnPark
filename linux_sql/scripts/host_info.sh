#! /bin/bash

# assign CLI arguments to variables (e.g. `psql_host=$1`) 
# parse host hardware specifications using bash cmds
# construct the INSERT statement
# execute the INSERT statement through psql CLI tool


#setup and validate arguments (again, don't copy comments)
psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5


#Check # of args
if [ $# -ne 5 ]; then 
  echo "Invalid number of arguments"
  exit 1
fi



#Save machine statistics in MB and current machine hostname to variables
specs=`lscpu`
hostname=$(hostname -f)

#Retrieve hardware specification variables
#xargs is a trick to trim leading and trailing white spaces
cpu_number=$(echo "$specs"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(echo "$specs"  | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(echo "$specs"  | egrep "^Model name:" | awk -F[":"] '{print $2}' | xargs)
cpu_mhz=$(echo "$specs"  | egrep "^CPU MHz:" | awk -F[":"] '{print $2}' | xargs)
l2_cache=$(echo "$specs"  | egrep "^L2 cache:" | awk -F[":"] '{print $2}' | xargs | sed 's/[^0-9]*//g')
total_mem=$(grep MemTotal /proc/meminfo | awk -F[":"] '{print $2}' | xargs | sed 's/[^0-9]*//g')

#Current time in `2019-11-26 14:40:19` UTC format
timestamp=$(vmstat -t | awk '{print $18,$19}' | tail -n 1 | xargs)

#PSQL command: Inserts hardware specification data into host_info table
#Note: be careful with double and single quotes
insert_stmt="INSERT INTO host_info
  (id, hostname, cpu_number, cpu_architecture, cpu_model, cpu_mhz, L2_cache, total_mem, timestamp) 
VALUES
  (DEFAULT, '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$l2_cache', '$total_mem', '$timestamp')"; 
             

#set up env var for pql cmd
export PGPASSWORD=$psql_password 
#Insert date into a database
psql -h $psql_host -p $psql_port -d $db_name -U $psql_user -c "$insert_stmt"
exit $?