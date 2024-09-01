# Android CoreX

CoreX is a foundational Android project written in Kotlin, designed to provide a modular, scalable architecture for mobile applications. This repository serves as the core layer of an Android app, handling essential features such as data management, domain logic, and UI foundations.

## Overview

The **Android CoreX** repository is structured to support clean architecture principles, enabling developers to build maintainable and testable applications. The project is organized into distinct layers: Data, Domain, and UI/Base, each serving a specific role in the application's architecture.

## Features

- **Modular Architecture**: Separation of concerns across data, domain, and UI layers.
- **Kotlin Language**: Written entirely in Kotlin for modern Android development.
- **Scalability**: Designed to scale as your application grows.
- **Testability**: Architecture supports easy unit testing.

## Architecture

CoreX follows a clean architecture pattern:

- **Data Layer**: Handles data retrieval from local databases, remote APIs, etc.
- **Domain Layer**: Contains the business logic and use cases.
- **UI/Base Layer**: Provides base classes and common utilities for the UI.

## Modularization

CoreX is built with a modular structure to enhance reusability, maintainability, and scalability. The project is divided into several independent modules, each responsible for specific functionality. This allows developers to work on different modules simultaneously without affecting the rest of the project. Modules can be easily added, removed, or modified, making it easier to manage dependencies and reduce build times.

## Getting Started

### Prerequisites

- Android Studio (version 4.0 or higher)
- Kotlin (version 1.5 or higher)
- Gradle (version 7.0 or higher)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/doananhtuan22111996/android-core.git
