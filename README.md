# FitNut

FitNut is a fitness and nutrition assistant for Android. It tracks daily steps and calories burned through the Google Fit API, looks up nutrition info for meals with the Nutritionix API, and keeps a personal profile with goals for calories, water intake, and body measurements. Built as a course project by Team 10: Fouzan Ahmed Mir and Hinal Pujara.

## Features

- **Fitness tracking**: reads daily step count and calories burned from Google Fit (requires the Activity Recognition permission)
- **Nutrition lookup**: searches foods and shows calorie info powered by the Nutritionix API
- **Sign in**: email/password and Google sign-in through Firebase Authentication
- **Profile and goals**: stores name, height, weight, daily/weekly calorie goals, and water intake goal in Firestore
- **Offline storage**: Room database for locally cached user progress
- **Welcome and auth flow**: Compose-based welcome, sign-in, and sign-up screens with a home screen, bottom navigation, and an about/contact page

## Tech stack

- Kotlin 1.9.0, Jetpack Compose (Material 3) with Navigation Compose
- Android Gradle Plugin 8.6.1, compileSdk/targetSdk 35, minSdk 24
- Firebase Authentication and Cloud Firestore (BoM 33.6.0)
- Google Fit (play-services-fitness 21.1.0) and Google Sign-In (play-services-auth 20.5.0)
- Room 2.6.0, Retrofit 2.9.0 with Gson, Lottie 5.0.3 for animations

## Prerequisites

- JDK 17
- Android SDK with platform 35 and the Android SDK Build-Tools installed
- An Android device or emulator running API 24 or higher

## Setup

1. Clone the repo and open the `Fitnut` directory (the Gradle project root) in Android Studio, or build from the command line.

2. Add your API credentials to a `gradle.properties` file that Gradle can see, either the project-local one (gitignored) or `~/.gradle/gradle.properties`. Copy the template in `Fitnut/gradle.properties.example`:

   ```properties
   NUTRITIONIX_APP_ID=your_app_id
   NUTRITIONIX_APP_KEY=your_app_key
   GOOGLE_OAUTH_CLIENT_ID=your_client_id.apps.googleusercontent.com
   ```

   Get Nutritionix credentials at https://www.nutritionix.com/business/api and the OAuth client ID from the Google Cloud Console. These are injected into `BuildConfig` at build time, so no keys live in source code. The app still builds without them, but sign-in and meal search will not work.

3. Firebase: the app ships with a `google-services.json` under `Fitnut/app/`. To point it at your own Firebase project, replace that file with the one generated for your project in the Firebase console (same `com.example.fitnut` package, or update `applicationId` in `app/build.gradle.kts` to match).

## Build and run

From the `Fitnut` directory:

```bash
./gradlew assembleDebug      # build the debug APK
./gradlew installDebug        # install it on a connected device or emulator
```

The debug APK lands in `Fitnut/app/build/outputs/apk/debug/`.

## Project links

- Idea Proposal: https://docs.google.com/document/d/1m28GIr9DtxMs9TV3PsY5s-ygB_GqTzkq4Ovbv4sajXg/edit?usp=sharing
- Figma Design: https://www.figma.com/design/tNRCrFvlwKl2KJQs44ib5o/MAD?node-id=0-1&t=7reh7I75XQvEqKvO-1
- Presentation: https://docs.google.com/presentation/d/1Nv0EAptpdxqsaKLbbloPmxa5eT23b7Nq/edit?usp=sharing&ouid=116151538130942000168&rtpof=true&sd=true
