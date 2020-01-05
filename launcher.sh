# first clean and build spring projects
cd swagger
cd auth-server
echo 'building auth-server'
mvn clean install
cd ..
cd movies-server
echo 'building movies-server'
mvn clean install

# then start the docker-compose containing
# the two back-ends, two databases, phpmyadmin
# and traefik as a reverse proxy
echo 'creating mysql containers and populate movies database...'
echo 'creating the two spring backends containers...'
cd ..
cd ..
cd docker-topology

# copy the spring .jar files to the context root of the dockerfiles
cp ../swagger/auth-server/target/swagger-spring-1.0.0.jar ../docker-images/spring-boot/authentication/
cp ../swagger/movies-server/target/swagger-spring-1.0.0.jar ../docker-images/spring-boot/movies/

echo 'starting docker-compose topology...'
docker-compose down
docker-compose up --build

# then start all tests (after docker-compose
# because the databases and the spring projects
# have to run for the tests)
#echo 'starting tests...'
#cd ..
#cd swagger
#cd auth-specs
#mvn clean test