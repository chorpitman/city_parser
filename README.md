Prerequisites
==============
| **Tool** | **Required Version** | **How to check**  | **Comments** |
| --- | --- | --- | --- |
| Java | 1.8.x | java -version | |
| Maven | 3.2.3 or 3.2.5 | mvn -version | Newer versions should also work |
| Git | any (latest preferable) | git --version | |
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
  
GOOGLE API
-----------
 * Create and activate GoogleKeyAPI. Additional info: `https://support.google.com/googleapi/answer/6158857?hl=ru`
 * Go to https://console.developers.google.com
 * Enable `Google Places API Web Service`
 * Enable `Google Maps Geocoding API`
  
Building/running a project
=================
1. Insert GoogleKeyAPI into following files: **sprockets.xml**, **GoogleMapsSearchImpl.class**;
2. Go to Main.class and run project