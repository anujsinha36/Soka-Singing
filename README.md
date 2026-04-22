Soka Singing

Android app built for Bharat Soka Gakkai (BSG) members to access songs + lyrics in one place during meetings.

Problem
In BSG meetings, songs are often:
played from YouTube
lyrics shared separately via WhatsApp or printed sheets

This creates friction—members switch between sources, lyrics are sometimes missing, and the experience feels fragmented.

Soka Singing solves this by combining audio + lyrics in a single, focused interface, making meetings smoother and distraction-free.

What the app does
Browse a list of Soka Gakkai songs
View full lyrics for each song
Play songs directly inside the app
Seek within audio with real-time progress tracking

Tech Stack
Kotlin
Jetpack Compose (UI)
MVVM (Clean Architecture)
Presentation
Domain
Data
ExoPlayer for media playback
Firebase Realtime Database (songs metadata + URLs + lyrics)
Coroutines + Flow / StateFlow for async + state handling
Hilt for dependency injection
Navigation Compose

Architecture Notes
UI state is driven via StateFlow from ViewModel
Player is controlled through ViewModel to avoid tight coupling with composables
Firebase acts as the single source of truth with built-in caching enabled


Limitations
No offline playback support yet
No background playback or notification controls
Firebase default caching only (no custom offline-first strategy)
Limited device testing

Real-world usage
Used in small group settings to streamline song playback during meetings, reducing dependency on multiple sources like YouTube and WhatsApp.

What I’d improve next
Offline-first support (local caching + playback)
Background playback with media notification controls
Lyrics synchronization (highlight current line with playback)
Preloading audio to reduce initial buffering delay
UI overhaul for faster navigation during live meetings

Setup
Clone the repo
Open in Android Studio
Build & run on device/emulator
