REM base de desenvolvimento
liquibase --changeLogFile=db.changelog-master.xml --driver=com.mysql.jdbc.Driver --classpath=mysql-connector-java-5.1.25.jar --url="jdbc:mysql://localhost:3306/sge" --username=root --password=root update
pause