# 🧬 Mutant Detector API

![Java](https://img.shields.io/badge/Java-21-orange) ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-green) ![Coverage](https://img.shields.io/badge/Coverage->80%25-brightgreen)

Este proyecto es una API REST desarrollada para resolver el desafío técnico de **Mercado Libre**. El objetivo es detectar si un humano es un mutante basándose en su secuencia de ADN.

El programa verifica secuencias de ADN buscando más de una secuencia de cuatro letras iguales de forma oblicua, horizontal o vertical.

---

## 🚀 Demo en Vivo (Deploy)
La API está desplegada y accesible públicamente en **Render**:

🔗 **Base URL:** `https://mutante-api.onrender.com`

> **⚠️ Nota importante:** Al estar alojado en el plan gratuito de Render, el servicio entra en "modo suspensión" tras inactividad. La **primera petición puede tardar entre 30 y 50 segundos** en responder. Las siguientes serán instantáneas.

---

## 🛠️ Tecnologías Utilizadas
* **Lenguaje:** Java 21 (Eclipse Temurin)
* **Framework:** Spring Boot 3
* **Base de Datos:** H2 Database (En memoria, para alta velocidad y simplicidad)
* **Build Tool:** Gradle
* **Testing:** JUnit 5, Mockito & Spring Boot Test
* **Container:** Docker
* **Cloud:** Render

---

## 📋 Documentación de la API

### 1. Detectar Mutante
Verifica si una secuencia de ADN corresponde a un mutante.

* **Endpoint:** `POST /mutant/`
* **Content-Type:** `application/json`

#### Caso: Mutante (HTTP 200 OK)
```json
{
    "dna": [
        "ATGCGA",
        "CAGTGC",
        "TTATGT",
        "AGAAGG",
        "CCCCTA",
        "TCACTG"
    ]
}
```
#### Caso: Humano (HTTP 403 Forbidden)
```json
{
    "dna": [
        "AAAT",
        "AACC",
        "AAAC",
        "CGGG"
    ]
}
```
### 2. Estadísticas
Devuelve estadísticas de las verificaciones de ADN realizadas.

* **Endpoint:** `GET /stats`
* **Content-Type:** `application/json`

#### Respuesta Ejemplo:
```json
{
    "count_mutant_dna": 40,
    "count_human_dna": 100,
    "ratio": 0.4
}
```
>    **Nota importante:**  
> Repositorio de Github para el Trabajo Integrador Mutantes        
> Materia: Desarrollo de Software 3k10 - 2025  
> Alumno: Gabriel Villalobos
