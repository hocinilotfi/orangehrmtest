# Démarrer le Hub Selenium
docker run -d -p 4444:4444 --name selenium-hub selenium/hub:latest

# Démarrer un nœud Chrome connecté au Hub
docker run -d --link selenium-hub:hub -v /dev/shm:/dev/shm selenium/standalone-chrome:latest
