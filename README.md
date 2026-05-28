# QuizAura

A modern Android Quiz Application built using Kotlin and Jetpack Compose following MVVM architecture principles.

QuizAura demonstrates modern Android development practices including Firebase Authentication, Room offline caching, Retrofit API integration, reactive state management, Material 3 Expressive UI, animations, and dark mode support.

---

# Features

## Authentication

* Firebase Login
* Firebase Signup
* Session Persistence
* Logout Support

## Quiz System

* Multiple Choice Questions
* 30-second Timer
* Previous / Next Navigation
* Progress Tracking
* Score Calculation
* Motivational Progress Messages

## Result Screen

* Final Score
* Correct / Wrong Answers
* Percentage Calculation
* Play Again Support

## UI / UX

* Jetpack Compose UI
* Material 3 Expressive Design
* Animated Question Transitions
* Animated Option Selection
* Smooth Navigation Transitions
* Dark Mode Support
* Responsive Layouts

## Offline Support

* Room Database Caching
* Offline-first Question Handling
* Local Persistence

---

# Tech Stack

| Category             | Technology             |
| -------------------- | ---------------------- |
| Language             | Kotlin                 |
| UI                   | Jetpack Compose        |
| Architecture         | MVVM                   |
| Dependency Injection | Koin                   |
| Networking           | Retrofit + Gson        |
| Local Database       | Room                   |
| Authentication       | Firebase Auth          |
| Async                | Coroutines + StateFlow |
| Design System        | Material 3 Expressive  |

---

# Architecture

The app follows MVVM (Model-View-ViewModel) architecture with clear separation of concerns.

## Layers

### Presentation Layer

Contains:

* Compose Screens
* ViewModels
* UI State Management

### Data Layer

Contains:

* Repository
* Retrofit API
* Room Database
* Firebase Services

### Dependency Injection

Koin is used for dependency management and object creation.

---

# Why MVVM?

I used MVVM architecture because it provides clear separation between UI, business logic, and data handling.

* ViewModels manage UI state and survive configuration changes
* Repository centralizes data operations
* Compose UI remains reactive and stateless
* Room and Retrofit integration becomes easier to scale

This structure improves maintainability, readability, and scalability of the project.

---

# Project Structure

```text
com.example.quizaura/
│
├── data/
│   ├── local/
│   │   ├── QuestionDao.kt
│   │   ├── QuestionEntity.kt
│   │   └── QuizDatabase.kt
│   │
│   ├── AuthService.kt
│   ├── Question.kt
│   ├── QuizRepository.kt
│   ├── QuizResult.kt
│   └── TriviaApi.kt
│
├── di/
│   └── AppModule.kt
│
├── navigation/
│   └── NavGraph.kt
│
├── presentation/
│   ├── auth/
│   │   ├── AuthUiState.kt
│   │   ├── AuthViewModel.kt
│   │   ├── LoginScreen.kt
│   │   └── SignupScreen.kt
│   │
│   ├── quiz/
│   │   ├── QuizUiState.kt
│   │   ├── QuizViewModel.kt
│   │   └── QuizScreen.kt
│   │
│   └── result/
│       └── ResultScreen.kt
│
├── ui.theme/
│
├── MainActivity.kt
└── QuizApp.kt
```

---

# Data Flow

```text
App Launch
    │
    ▼
NavGraph checks FirebaseAuth.currentUser
    │
    ├── Not logged in → LoginScreen
    │
    └── Logged in → QuizScreen
            │
            ▼
        QuizViewModel loads questions
            │
            ├── Room cache exists → Serve local questions
            │
            └── Cache empty → Fetch from OpenTriviaDB API
                    │
                    ▼
                Store questions in Room
                    │
                    ▼
                UI observes StateFlow updates
                    │
                    ▼
                User answers questions
                    │
                    ▼
                ResultScreen displays score
```

---

# Offline Support

Questions are cached locally using Room Database.

* First launch → Questions fetched from API and stored locally
* Next launches → Questions served directly from Room cache

This improves:

* app performance
* reduced network dependency
* offline support

---

# API Used

Open Trivia Database

Base URL:

```text
https://opentdb.com/
```

Endpoint:

```text
GET /api.php?amount=10&category=18&type=multiple
```

---

# Screenshots

## Login Screen

<img width="390" height="887" alt="image" src="https://github.com/user-attachments/assets/d90417f7-ffc2-486e-ba1d-714d8912ff8e" />


## Signup Screen
<img width="403" height="887" alt="image" src="https://github.com/user-attachments/assets/f66e5f25-b753-4522-9325-39c2a02d709b" />


## Quiz Screen
<img width="395" height="888" alt="image" src="https://github.com/user-attachments/assets/cb4ea13f-3828-4915-b0a5-369a308183c6" />



## Result Screen

<img width="398" height="892" alt="image" src="https://github.com/user-attachments/assets/2ae1c180-3f94-4243-a4a9-74b07f9a3ce7" />

<img width="395" height="882" alt="image" src="https://github.com/user-attachments/assets/eb422a9c-793f-43fe-9943-3c4f525ff7b7" />





---

# Demo Video

App Walkthrough:https://drive.google.com/file/d/1uVc0tLVGkY7BBnIBWDNJurx8TOwkuS7W/view?usp=sharing


Architecture Explanation:
[Add architecture video link here](https://drive.google.com/file/d/1t0qvCym4OntSFxRZZ1rTMbP7akmacQ3H/view?usp=drive_link)

---

# Setup Instructions

## Clone Repository

```bash
git clone https://github.com/anuj990/QuizAura.git
```

## Open in Android Studio

Open the project in Android Studio Hedgehog or newer.

## Firebase Setup

1. Create Firebase Project
2. Enable Email/Password Authentication
3. Download `google-services.json`
4. Place it inside `app/`

## Run Application

```bash
./gradlew assembleDebug
```

Or run directly using Android Studio.

---

# Future Improvements

* AI-powered quiz hints
* Adaptive difficulty system
* Leaderboards
* Multiplayer quiz mode
* Category-based quizzes

---

# Developed By

Anuj

GitHub:
https://github.com/anuj990
