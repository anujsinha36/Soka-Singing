Soka Singing
Android app built for Bharat Soka Gakkai (BSG) members to access songs + lyrics in one place during meetings.
________________________________________
Problem
In BSG meetings, songs are often:
•	played from YouTube 
•	lyrics shared separately via WhatsApp or printed sheets 
This creates friction—members switch between sources, lyrics are sometimes missing, and the experience feels fragmented.
Soka Singing solves this by combining audio + lyrics in a single, focused interface, making meetings smoother and distraction-free.
________________________________________
What the app does
•	Browse a list of Soka Gakkai songs 
•	View full lyrics for each song 
•	Play songs directly inside the app 
•	Seek within audio with real-time progress tracking 
________________________________________
Tech Stack
•	Kotlin 
•	Jetpack Compose (UI) 
•	MVVM (Clean Architecture) 
o	Presentation 
o	Domain 
o	Data 
•	ExoPlayer for media playback 
•	Firebase Realtime Database (songs metadata + URLs + lyrics) 
•	Coroutines + Flow / StateFlow for async + state handling 
•	Hilt for dependency injection 
•	Navigation Compose 
________________________________________
Architecture Notes
•	UI state is driven via StateFlow from ViewModel 
•	Player is controlled through ViewModel to avoid tight coupling with composables 
•	Firebase acts as the single source of truth with built-in caching enabled 
________________________________________
Limitations
•	No offline playback support yet 
•	No background playback or notification controls 
•	Firebase default caching only (no custom offline-first strategy) 
•	Limited device testing 
________________________________________
Real-world usage
Used in small group settings to streamline song playback during meetings, reducing dependency on multiple sources like YouTube and WhatsApp.
________________________________________
What I’d improve next
•	Offline-first support (local caching + playback) 
•	Background playback with media notification controls 
•	Lyrics synchronization (highlight current line with playback) 
•	Preloading audio to reduce initial buffering delay 
•	UI overhaul for faster navigation during live meetings 
________________________________________
Setup
1.	Clone the repo 
2.	Open in Android Studio 
3.	Build & run on device/emulator

