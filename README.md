# Transversal project API

## Prérequis

Pour utiliser ce projet il faut déjà avoir Java, Gradle et Docker 

### Installation de Java

Pour l'installation de java sur Windows il est conseillé de suivre le [tutoriel suivant](https://java.tutorials24x7.com/blog/how-to-install-java-15-on-windows-10)
Pour l'installaiton de java sur GNU/Linux, on pourra suivre le [tutoriel suivant](https://www.javahelps.com/2020/09/install-oracle-jdk-15-on-linux.html) 

#### Installation de Docker
```sh
sudo apt install docker-compose
```

## Pour commencer

Builder le projet
```sh
gradlew.bat build
```

Monter l'image Docker
```sh
docker-compose up --build
```

## Comment l'utiliser

Requête POST exemple pour simuler une réception de donnée via UART

> Cela correspond à insérer si besoin l'incident en base et envoyer l'incident en MQTT

http://localhost:8082/incident/data

Body :
`
{
    "data" : "F/1,1,1&I/2,5,9"
}
`

## Ports serial 
- manager : /dev/ttyACM1
- simulator : /dev/ttyACM0
