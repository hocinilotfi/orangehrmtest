# Utiliser une image de base avec Maven et OpenJDK préinstallés
FROM maven:3-openjdk-17-slim

# Installer Git
RUN apt-get update && apt-get install -y git

# Définir le répertoire de travail
WORKDIR /app

# Cloner le dépôt GitHub contenant le projet
RUN git clone https://github.com/hocinilotfi/orangehrmtest.git .

# Construire le projet
RUN mvn clean install

# Définir le point d'entrée pour les tests
CMD ["mvn", "test"]
