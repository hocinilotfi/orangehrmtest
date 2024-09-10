# Utiliser l'image de base Maven avec OpenJDK
FROM maven:3-openjdk-17-slim

# Installer Git
RUN apt-get update && apt-get install -y git

# Définir le répertoire de travail
WORKDIR /app

# Cloner le dépôt GitHub contenant le projet
RUN git clone https://github.com/hocinilotfi/orangehrmtest.git .

# Définir le répertoire de travail pour Maven
WORKDIR /app/orangehrmtest

# Copier le fichier POM et les sources dans le conteneur
COPY . /app/orangehrmtest

# Installer les dépendances Maven
RUN mvn dependency:go-offline

# Commande par défaut (peut être remplacée dans le pipeline Jenkins)
CMD ["mvn", "clean", "install"]
