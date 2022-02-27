drop database if exists todos;

create database todos;

create table tasks (
    username VARCHAR(64),
    task_id INT AUTO_INCREMENT NOT NULL,
    task_name VARCHAR(256),
    priority ENUM('low','medium','high') DEFAULT 'low',
    due_date DATE,
    PRIMARY KEY (task_id)
);