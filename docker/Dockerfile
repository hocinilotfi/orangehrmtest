# Utiliser une image Maven avec OpenJDK 17
FROM maven:3-openjdk-17-slim

# Installer les dépendances nécessaires pour Chrome et Firefox
RUN apt-get update && \
    apt-get install -y wget unzip curl gnupg \
    # Installer les dépendances nécessaires pour Chrome
    && curl -fsSL https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" | tee /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update && apt-get install -y google-chrome-stable \
    # Installer les dépendances nécessaires pour Firefox
    && apt-get install -y firefox-esr \
    # Installer ChromeDriver
    && wget https://chromedriver.storage.googleapis.com/114.0.5735.90/chromedriver_linux64.zip \
    && unzip chromedriver_linux64.zip \
    && mv chromedriver /usr/local/bin/chromedriver \
    && chmod +x /usr/local/bin/chromedriver \
    # Installer GeckoDriver
    && wget https://github.com/mozilla/geckodriver/releases/download/v0.33.0/geckodriver-v0.33.0-linux64.tar.gz \
    && tar -xzf geckodriver-v0.33.0-linux64.tar.gz \
    && mv geckodriver /usr/local/bin/geckodriver \
    && chmod +x /usr/local/bin/geckodriver \
    # Nettoyer les fichiers temporaires
    && rm chromedriver_linux64.zip geckodriver-v0.33.0-linux64.tar.gz \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Copier les fichiers du projet dans le conteneur
COPY . /app

# Construire le projet Maven
RUN mvn clean install

# Construire le projet
#RUN mvn clean install

# Définir le point d'entrée pour les tests
#CMD ["mvn", "test"]
