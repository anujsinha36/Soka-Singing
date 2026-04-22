Soka Singing
Android app built for Bharat Soka Gakkai (BSG) members to access songs + lyrics in one place during meetings.
________________________________________
Problem
In BSG meetings, songs are often:

1. played from YouTube 
2. lyrics shared separately via WhatsApp or printed sheets 
This creates friction—members switch between sources, lyrics are sometimes missing, and the experience feels fragmented.
Soka Singing solves this by combining audio + lyrics in a single, focused interface, making meetings smoother and distraction-free.
________________________________________
What the app does
1. Browse a list of Soka Gakkai songs 
2. View full lyrics for each song 
3. Play songs directly inside the app 
4. Seek within audio with real-time progress tracking 
________________________________________
Tech Stack
1. Kotlin 
2. Jetpack Compose (UI) 
3. MVVM (Clean Architecture) 
4. Presentation 
5. Domain 
6. Data 
7. ExoPlayer for media playback 
8. Firebase Realtime Database (songs metadata + URLs + lyrics) 
9. Coroutines + Flow / StateFlow for async + state handling 
10. Hilt for dependency injection 
11. Navigation Compose 
________________________________________
Architecture Notes
1. UI state is driven via StateFlow from ViewModel 
2. Player is controlled through ViewModel to avoid tight coupling with composables 
3. Firebase acts as the single source of truth with built-in caching enabled 
________________________________________
Limitations
1. No offline playback support yet 
2. No background playback or notification controls 
3. Firebase default caching only (no custom offline-first strategy) 
4. Limited device testing 
________________________________________
Real-world usage
Used in small group settings to streamline song playback during meetings, reducing dependency on multiple sources like YouTube and WhatsApp.
________________________________________
What I’d improve next
1. Offline-first support (local caching + playback) 
2. Background playback with media notification controls 
3. Lyrics synchronization (highlight current line with playback) 
4. Preloading audio to reduce initial buffering delay 
5. UI overhaul for faster navigation during live meetings 
________________________________________
Setup
1.	Clone the repo 
2.	Open in Android Studio 
3.	Build & run on device/emulator

