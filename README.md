Prerequisites
==============
| **Tool** | **Required Version** | **How to check**  | **Comments** |
| --- | --- | --- | --- |
| Java | 1.8.x | java -version | |
| Maven | 3.2.3 or 3.2.5 | mvn -version | Newer versions should also work |
| Gradle | any (latest preferrable) | gradle --version | This is optional if maven is used |
| Git | any (latest preferrable) | git --version | |
| MySQL | 5.6.x (or newer) | mysql --version | |

Setup
======

Java
----------
Download and install latest JDK from `http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html`

Maven
----------
Download and install Maven from `https://maven.apache.org/download.cgi`

MySQL
-----------
Manual installation

  * Download and install latest MySQL community server from `http://dev.mysql.com/downloads/mysql/`
  * Login to mysql
  
  > *mysql -u root -p*
  
  * Create database 'city_parser': 

  * Grant permissions to created user for our database 
  
  > *GRANT ALL PRIVILEGES ON dinner.* TO 'city_parser'@'localhost';*
  > *flush privileges;*
  
Building/running a project
=================
