A modern Flutter music player with background playback, lock-screen controls, and a polished Teal/Dark UI.

---

## Project Structure

```
aura_music/
├── pubspec.yaml
├── codemagic.yaml
├── android/
│ ├── build.gradle.kts ← root Gradle (Gradle 8+ syntax)
│ ├── settings.gradle.kts
│ └── app/
│ ├── build.gradle.kts ← namespace, compileSdk 35, Java 17, multiDex
│ └── src/main/
│ └── AndroidManifest.xml
└── lib/
    ├── main.dart ← app entry + AudioService.init
    ├── models/
    │ └── song.dart ← Song model + mock catalogue
    ├── services/
    │ └── audio_handler.dart ← AuraAudioHandler (just_audio + audio_service)
    ├── providers/
    │ └── player_provider.dart ← PlayerProvider (ChangeNotifier)
    ├── theme/
    │ └── app_theme.dart ← Teal/Dark ThemeData
    ├── widgets/
    │ ├── album_art.dart ← gradient album art placeholder
    │ └── mini_player.dart ← persistent bottom mini-player
    └── screens/
        ├── home_screen.dart ← bottom nav scaffold
        ├── library_screen.dart ← song list + now-playing banner
        └── now_playing_screen.dart ← full player UI
```

---

## Stack

| Concern | Package |
|------------------|----------------------------------|
| Audio playback | `just_audio ^0.9.40` |
| Audio session | `audio_session ^0.1.21` |
| Background audio | `audio_service ^0.18.15` |
| State mgmt | `provider ^6.1.2` |
| Build system | Kotlin DSL (.kts) + Gradle 8 |
| Min Java | 17 |
| compileSdk | 35 |

---

## Quick Start

```bash
flutter pub get
flutter run
```

### Build release APK

```bash
flutter build apk --release
```

### Codemagic CI

Push to your repo → Codemagic picks up `codemagic.yaml` automatically.
Make sure your workspace uses **Java 17** and **Flutter 3.22.x stable**.

---

## Adding Real Songs

Replace the `audioUrl` values in `lib/models/song.dart` with:
- HTTP/HTTPS stream URLs, OR
- `assets/audio/my_song.mp3` (register the asset in `pubspec.yaml`)

No permission changes required for network streams.  
For local device files, add `READ_MEDIA_AUDIO` permission and a file picker.

---

## Roadmap

- [ ] Search & filter
- [ ] Playlist management
- [ ] Equalizer
- [ ] Lyrics view
- [ ] iOS lock-screen controls (already wired via audio_service)
