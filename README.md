to deploy test db on docker container: <br />
docker run --name tech_db -p 5431:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=postgres -d postgres<br />
<br />
to run app:<br />
mvn spring-boot:run<br />
<br />
to run app in docker container:<br />
1)create image: mvn spring-boot:build-image<br />
2)run: docker run --network="host"   -i -t 'ID'<br />
spring web+data+jpa hibernate+security 
