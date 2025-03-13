# Utiliser une image OpenJDK comme base
FROM openjdk:17-slim

# Installer les bibliothèques nécessaires pour le support graphique
RUN apt-get update && apt-get install -y \
    libxrender1 libxtst6 libxi6 libxext6 libfreetype6 \
    libgl1-mesa-glx libglib2.0-0 x11-xserver-utils \
    libgtk-3-0 libcanberra-gtk-module libcanberra-gtk3-module \
    && rm -rf /var/lib/apt/lists/*

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier source dans le container
COPY Morpion.java /app/Morpion.java

# Compiler le fichier Java
RUN javac Morpion.java

# Exécuter le programme (avec le support du serveur graphique X11)
CMD ["java", "Morpion"]
