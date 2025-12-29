***CQRS & Event Sourcing – Activité Pratique***

**Présentation du projet**

Ce projet a pour objectif de mettre en œuvre une architecture microservices moderne basée sur les patterns CQRS (Command Query Responsibility Segregation) et Event Sourcing, largement utilisés dans les systèmes distribués à forte scalabilité et traçabilité.

L’implémentation repose principalement sur :

-Spring Boot pour le développement des microservices

-Axon Framework pour la gestion des commandes, événements et projections

-Axon Server comme Event Store et Message Broker

Ce travail est inspiré et approfondit la démonstration présentée par le Pr. Mohamed Youssfi intitulée :
 « CQRS avec Event Sourcing (Spring, Axon) »

**Objectifs du projet**

Les principaux objectifs pédagogiques et techniques de ce projet sont :

Comprendre et appliquer le pattern CQRS en séparant clairement :

le Command Model (écriture, logique métier)

le Query Model (lecture, consultation)

Mettre en œuvre le pattern Event Sourcing, où l’état du système est reconstruit à partir d’événements immuables

Utiliser Axon Framework pour :

la gestion des commandes

la publication et le stockage des événements

la synchronisation des projections

Appliquer ces concepts à un cas d’usage bancaire simple (gestion de comptes)

**Structure du projet**

Le projet est organisé selon une séparation stricte Command / Query, conformément au pattern CQRS, tout en intégrant le Event Sourcing avec Axon Framework.

<img width="1811" height="907" alt="Image" src="https://github.com/user-attachments/assets/2f79d12f-a038-417c-b07c-e1c8ee2783c2" />

Le projet suit une architecture CQRS avec Event Sourcing en séparant clairement :

🔹 Partie Command (Write Model)

aggregates : logique métier et gestion des événements

commands : intentions métier (création, crédit, débit)

events : événements persistés dans l’Event Store

controllers : API REST pour l’envoi des commandes

dto / enums : transport et typage des données

🔹 Partie Query (Read Model)

entities : modèles JPA pour la lecture

handlers : projections mises à jour par les événements

queries : requêtes Axon

repository : accès aux données

controllers / dtos : API REST de consultation

🔹 Application

Classe principale Spring Boot pour démarrer l’application
