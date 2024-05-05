# SHOP MANAGEMENT APP
[click here to see other versions on the google drive](https://drive.google.com/drive/folders/1UU1nwldt5cMmciTcMZXeVPy9rHn1wrYe?usp=sharing)

## INSTRUCTION
[![Git clone shop_app](https://img.youtube.com/vi/64qBbOJ6r5w/maxresdefault.jpg)](https://youtu.be/64qBbOJ6r5w)

## GUIDE
- setup java > [setup jdk and maven](https://youtu.be/vm3W-KsFnrE)<br>
- setup java > [setup jdk and maven](https://youtu.be/vm3W-KsFnrE)<br>

## REQUIREMENTS
- DOWLOAD > [JDK 1.8](https://drive.google.com/file/d/1uT6gHuqjFmxXPYKYI_HA76geQDDgMZPT/view?usp=sharing)<br/>
- DOWLOAD > [MAVEN v3](https://dlcdn.apache.org/maven/maven-3/3.9.2/binaries/apache-maven-3.9.2-bin.zip)<br/>
- DOWLOAD > [MSSQL 2019](https://azure.microsoft.com/en-au/products/azure-sql/database/)

## CONFIGURATION
- EXECUTE FILE DATABASE (SQL SERVER 2019) > [EXEC FILE DATABASE](./db_mssql_SHOP.sql)<br/>
- CONFIGURATION APP > [CONFIGURATION](./config.properties)
   - CONFIGURE EMAIL CONNECTION
      - [config> mail.username](./config.properties#L38) 
      - [config> mail.password](./config.properties#L39) 
   - CONFIGURE MSSQL CONNECTION
      - [config> mssql.username](./config.properties#L46): 
      - [config> mssql.password](./config.properties#L47):
<hr/>

> FOR **WINDOWS OPERATING** SYSTEM

check package(project-dependency): "C:/Users/[_YOUR_USER_]/.m2/repository/shop"<br>
point to project: cd [PATH TO THE PROJECT]/Shop_App<br>
OPEN [MAIN_APP.java](./src/main/java/shop/Main_App.java#L22) RUN WITH JAVA
<hr>

## QUICK START WITH MAVEN
<h5>Open <em>command prompt</em> and <em>run as administrator</em></h5>

```
cd /d [...project location]/Shop_App
```
```
sqlcmd -f 65001 -i db_mssql_SHOP.sql
```
```
mvn dependency:get
```
```
mvn clean | mvn package
```
```
mvn exec:java
```
<hr>

## DEPLOY PROJECT AND BUILD SOURCE

> CHECK REQUIREMENTS:
<table border>
<tr>
   <th rowspan="2">check maven version</th>
   <th rowspan="2">check java version</th>
   <th style="text-align: center" colspan="2" rowspan="1">check path java-home</th>
</tr>
<tr>
   <th>Comman line</th>
   <th>Powershell</th>
</tr>
<tr style="color: #ff9600;">
   <td>mvn -v</td>
   <td>java -version</td>
   <td>echo %JAVA_HOME%</td>
   <td style="color: #18b200;">$env:JAVA_HOME</td>
</tr>
</table>

> DOWNLOAD LIBRARIES AND RUN PROJECT

<table border>
<tr>
   <th style="text-align: center">Point to project</th>
   <td style="text-align: center; color: #ff9600;">cd [...]/Shop_App</td>
</tr>

<tr>
   <th style="text-align: center" colspan="2">DEPENDENCIES</th>
</tr>
<tr>
   <th style="text-align: center">download users dependencies</th>
   <th style="text-align: center">copy dependencies in pom.xml to target/dependencies</th>
</tr>
<tr style="color: #ff9600;">
   <td>mvn dependency:get -Dartifact=groupId:artifactId:version</td>
   <td>mvn dependency:copy-dependencies</td>
</tr>

<tr>
   <th style="text-align: center" colspan="2">BUILD JAR FROM PROJECT</th>
</tr>
<tr>
   <th style="text-align: center">Build jar with all dependencies and deploy file jar</th>
   <td style="color: #ff9600;">
      <ul>
         <li>mvn clean compile assembly:single</li>
         <li>java -jar target/Shop_App-1.0-SNAPSHOT-jar-with-dependencies.jar</li>
      </ul>
   </td>
</tr>
<tr>
   <th style="text-align: center">build jar in this project source</th>
   <th style="text-align: center">Build source to LOCAL(.../m2/repository)</th>
</tr>
<tr>
   <td style="color: #ff9600;">mvn clean | mvn package</td>
   <td style="color: #ff9600;">mvn clean | mvn install</td>
</tr>

<tr>
   <th style="text-align: center" colspan="2">RUN SOURCE CODE</th>
</tr>
<tr>
   <th style="text-align: center">run with maven (<em style='color: #ff0000'>!WARNING</em>)</th>
   <th style="text-align: center">Build source to LOCAL(.../m2/repository)</th>
</tr>
<tr style="color:#ff9600;">
   <td>mvn compile exec:java</td>
   <td>
   - B1: Open file <a href='./src/main/java/shop/Main_App.java'>Main_App.java</a><br>
   - B2: Java Program <b>[run | debug]</b>
   </td>
</tr>
</table>
