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

Add screenshot here

## Signup Screen

Add screenshot here

## Quiz Screen

Add screenshot here

## Result Screen

Add screenshot here

## Dark Mode

Add screenshot here

---

# Demo Video

App Walkthrough:
Add video link here

Architecture Explanation:
Add architecture video link here

---

# APK Download

Add APK link here

---

# Setup Instructions

## Clone Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_LINK
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
https://github.com/YOUR_USERNAME
