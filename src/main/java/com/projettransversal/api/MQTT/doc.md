mosquitto -p 1883

mosquitto_pub -h localhost -t topic -m hello

mosquitto_sub -h localhost -t topic
