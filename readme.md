mvn package -Dmaven.test.skip=true

java -jar target/day16-lecture-0.0.1-SNAPSHOT.jar 

## two manually add dependency
        <!--https://mvnrepository.com/artifact/redis.clients/jedis-->
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
			<!-- <version>3.9.0</version> -->
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.glassfish/jakarta.json -->
		<dependency>
			<groupId>org.glassfish</groupId>
			<artifactId>jakarta.json</artifactId>
			<version>2.0.1</version>
		</dependency>