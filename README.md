# Transversal project API 
## Get started 

-- BUILD LE PROJET
gradlew.bat build

-- CREER IMAGE DOCKER
docker-compose up --build

## How to use :

Example post data using curl HTTP request :

curl --header "Content-Type: application/json" -d '{"id":62,"mapItem":{"id":1,"posX":1,"posY":1,"ground":"BUILDING"},"intensity":1.0,"incidentType":"FIRE"}' -X POST http://localhost:8080/incident

For now the serial communication is in the "/dev/ttyACM0" for linux 
