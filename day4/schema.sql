drop database if exists mysocialmedia;
create database mysocialmedia;
use mysocialmedia;

create table post (
                      post_id int not null auto_increment,
    -- https://www.blazemeter.com/blog/performance-testing-blob-from-a-mysql-database-with-jmeter
                      photo mediumblob,
    -- https://chartio.com/resources/tutorials/understanding-strorage-sizes-for-mysql-text-data-types/
                      comment mediumtext,
                      poster varchar(64),
                      mediatype varchar(256),

                      primary key(post_id)
);