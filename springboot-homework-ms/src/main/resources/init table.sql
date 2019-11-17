create table user(

     user_id varchar(50) primary key,

                user_code varchar(20),

                user_name varchar(50),

                password varchar(50),

                user_type varchar(2),

                age int,

                sex varchar(2),

                email varchar(20),

                institute_id varchar(50),

                create_date date,

                phone varchar(20)

);

 

 

create table teacher(

                teacher_id varchar(50) primary key,

                user_id varchar(50)

);

 

 

create table student(

                student_id varchar(50) primary key,

                user_id varchar(50)

);

 

 

create table institute(

                institute_id varchar(50) primary key,

                institute_name varchar(100),

                create_date  date

);

 

create table term(

                term_id varchar(50) primary key,

                term_name varchar(50),

                term_value varchar(50),

                create_date date

);

 

create table tblClass(

                class_id varchar(50) primary key,

                class_name varchar(50),

                create_date date,

                institute_id varchar(50),

                grade varchar(10)

);

 

 

create table course(

                course_id varchar(50) primary key,

                course_name varchar(50),

                course_desc varchar(1000) ,

                create_date date

);

 

create table homework(

                homework_id varchar(50) primary key,

                homework_name varchar(100),

                homework_desc varchar(1000),

                homework_type char(2),

                create_date date,

                Due_date date,

                publish_date date

);

 

create table studentClass(

                student_class_id varchar(50) primary key,

                student_id varchar(50),

                class_id varchar(50)

);

 

create table teachercourse(

                teacher_course_id varchar(50) primary key,

                teacher_id varchar(50),

                course_id varchar(50)

);

 

create table studentcourse(

                student_course_id varchar(50) primary key,

                student_id varchar(50),

                course_id varchar(50)

);

 

create table courseTerm(

                course_term_id varchar(50) primary key,

                term_id varchar(50),

                course_id varchar(50)

);

 

create table courseHomework(

                course_homework_id varchar(50) primary key,

                homework_id varchar(50),

                course_id varchar(50),

                create_date date

);

 

create table teacherHomeworkFile(

                teacher_homework_file_id varchar(50) primary key,

                homework_id varchar(50),

                teacher_id varchar(50),

                file_id varchar(50)

);

 

create table studentHomeworkFile(

                student_homework_file_id varchar(50),

                homework_id varchar(50),

                student_id varchar(50),

                file_id varchar(50),

                score int,

                comment varchar(1000) ,

                create_date date,

                creator varchar(50),

                updator varchar(50),

                update_date date

);

 

create TABLE classInstitute(

                class_institute_id varchar(50) primary key,

                class_id varchar(50),

                institute_id varchar(50)

);

 

 

create TABLE courseInstitute (

                course_institute_id varchar(50) primary key,

                course_id varchar(50),

                institute_id varchar(50)

);

 

create TABLE courseClass (

                course_class_id varchar(50) primary key,

                course_id varchar(50),

                class_id varchar(50)

);

 

create TABLE studenthomeworkscore (

   student_homework_score_id varchar(50) primary key,

    student_id varchar(50) ,

    homework_id varchar(50) ,

    score int  ,

    creator varchar(50) ,

    comment varchar(500) ,

    create_date date,

    updater varchar(50),

    update_date date

);

 

create table attachedfile(

     file_id varchar(50) primary key ,

     file_name varchar(100),

     file_real_name varchar(100),

     file_path varchar(500),

     file_real_path varchar(500),

     file_type varchar(10),

     create_time date,

     file_uploader varchar(50)

);

insert into user (user_id,user_code, user_name, password, user_type) values ('1','admin','admin','admin','0');

 