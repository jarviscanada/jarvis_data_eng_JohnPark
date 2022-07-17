-- 1. Group hosts by hardware info
-- Group hosts by CPU number and sort by their memory size in descending order(within each cpu_number group) 

SELECT
    cpu_number AS cpu_number,
    id AS host_id,
    total_mem AS total_mem
FROM host_info
ORDER BY
    cpu_number,
    total_mem DESC;


-- 2. Average memory usage
-- Average used memory in percentage over 5 mins interval for each host. (used memory = total memory - free memory).

-- e.g. 
INSERT INTO host_usage 
    ("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES 
    ('2019-05-29 15:00:00.000', 1, 300000, 90, 4, 2, 3); 
    
INSERT INTO host_usage 
    ("timestamp", host_id, memory_free, cpu_idle, cpu_kernel, disk_io, disk_available)
VALUES 
    ('2019-05-29 15:01:00.000', 1, 200000, 90, 4, 2, 3);


-- round current ts every 5 mins
SELECT date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min'
FROM host_usage;


-- you can also create a function for convenience purposes so your qeury looks cleaner
CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;


CREATE FUNCTION used_memory_percentage(total_mem INTEGER, free_memory REAL) RETURNS REAL AS
$$
BEGIN
    RETURN ((1-(free_memory/total_mem)) * 100);
END
$$
    LANGUAGE PLPGSQL;

-- verify rount5 function
SELECT host_id, timestamp, round5(timestamp)
FROM host_usage;

-- Solution for #2:
SELECT
    host_id,
    hostname,
    round5(host_usage.timestamp) AS "timestamp",
    AVG(used_memory_percentage(total_mem, memory_free)) AS avg_used_memory
FROM
    host_usage join host_info on host_info.id = host_usage.host_id
GROUP BY
    round5(host_usage.timestamp),
    host_id,
    hostname
ORDER BY
    "timestamp";

-- 3. Detect host failure
-- The cron job is supposed to insert a new entry to the host_usage table every minute when the server is healthy.
-- We can assume that a server is failed when it inserts less than three data points within 5-min interval.
-- Please write a query to detect host failures.

SELECT
    host_id,
    round5(host_usage.timestamp) AS "ts",
    COUNT(*) AS num_data_points
FROM
    host_usage
GROUP BY
    round5(host_usage.timestamp),
    host_id
HAVING
    COUNT(*) < 3
ORDER BY
    "ts";