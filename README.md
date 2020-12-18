# Transversal project API 
## Pour commencer

-- BUILD LE PROJET
gradlew.bat build

-- CREER IMAGE DOCKER
docker-compose up --build

## Comment l'utiliser

Requête POST example pour le simulateur (branche simulateur)

curl --header "Content-Type: application/json" -d '{"id":62,"mapItem":{"id":1,"posX":1,"posY":1,"ground":"BUILDING"},"intensity":1.0,"incidentType":"FIRE"}' -X POST http://localhost:8080/incident

## Ports serial 
- manager : /dev/ttyACM1
- simulator : /dev/ttyACM0
