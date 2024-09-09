# Utiliser une image de base avec Maven et OpenJDK préinstallés
FROM maven:3.8.6-openjdk-17

# Installer Git
RUN apt-get update && apt-get install -y git

# Définir le répertoire de travail
WORKDIR /app

# Cloner le dépôt GitHub contenant le projet
RUN git clone https://github.com/username/repo.git .

# Construire le projet
RUN mvn clean install

# Définir le point d'entrée pour les tests
CMD ["mvn", "test"]
