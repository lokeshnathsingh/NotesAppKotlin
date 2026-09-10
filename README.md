# NotesAppKotlin

A simple, offline-first notes app for Android — built with Kotlin and Jetpack Compose, using Room for local persistence.

## ✨ Features

- 📝 Create, edit, and delete notes
- 🔍 Search through saved notes
- 💾 Offline-first local storage with Room database
- 🎨 Clean, modern UI built with Jetpack Compose
- 🌓 Light/dark theme support

## 🛠️ Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Local Database:** Room
- **Async:** Coroutines & Flow
- **Architecture:** MVVM
- **Build System:** Gradle (Kotlin DSL)

## 📂 Project Structure

```
NotesAppKotlin/
├── app/                    # Main application module
│   ├── src/main/java/...   # Kotlin source (UI, ViewModel, Room DB, repository)
│   └── src/main/res/       # Compose themes, resources
├── gradle/                 # Gradle wrapper & version catalog
├── build.gradle.kts        # Top-level build config
└── settings.gradle.kts
```

## 🚀 Getting Started

### Prerequisites

- Android Studio (latest stable)
- JDK 17+

### Setup

```bash
git clone https://github.com/lokeshnathsingh/NotesAppKotlin.git
cd NotesAppKotlin
```

1. Open the project in Android Studio.
2. Sync Gradle.
3. Run on an emulator or physical device.

### CLI Build

```bash
./gradlew assembleDebug
```

## 🗺️ Roadmap

- [ ] Note categories/tags
- [ ] Pin/favorite notes
- [ ] Rich text formatting
- [ ] Cloud backup & sync

## 🤝 Contributing

Contributions, issues, and feature requests are welcome. Feel free to check the [issues page](https://github.com/lokeshnathsingh/NotesAppKotlin/issues).

## 📄 License

This project is open for educational use. Add a license file if you plan to distribute it publicly.

## 👤 Author

**Lokesh Nath Singh**
- GitHub: [@lokeshnathsingh](https://github.com/lokeshnathsingh)
- LinkedIn: [lokesh-nath-singh](https://linkedin.com/in/lokesh-nath-singh/)
