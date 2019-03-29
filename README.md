
Hello there!
If you want to try to run the "web application", please, you have to go to branch "homework_20_02_2019", then click on "Maven" (there is a tab on the right side of IntelliJ IDEA") -> "clean - install" -> "Plugins" -> "tomcat7" -> "tomcat7:run"
And also check in that you have plugin "tomcat7-maven-plugin" into "pom.xml":

<plugin>                <groupId>org.apache.tomcat.maven</groupId>
                <artifactId>tomcat7-maven-plugin</artifactId>
                <version>2.2</version>
                <configuration>
                    <port>8080</port>
                    <path>/</path>
                </configuration>
</plugin>

Have a fun time!)