# Start all tests (after docker-compose
# because the databases and the spring projects
# have to run for the tests)
echo 'starting tests...'
cd swagger
cd auth-specs
mvn clean test

cd ..
cd movies-specs
mvn clean test