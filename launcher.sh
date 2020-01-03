# first clean and build spring projects
cd swagger
cd auth-server
mvn clean install

# then start the docker-compose containing
# the two back-ends, two databases, phpmyadmin
# and traefik as a reverse proxy
cd ..
cd ..
cd docker-topology
docker-compose down
docker-compose up --build
echo 'creating mysql containers and populate movies database'

# then start all tests (after docker-compose
# because the databases and the spring projects
# have to run for the tests)
cd ..
cd swagger
cd auth-specs
mvn clean test