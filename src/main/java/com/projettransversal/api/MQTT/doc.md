mosquitto -p 1883

mosquitto_pub -h localhost -t content -m hello

mosquitto_sub -h localhost -t content
