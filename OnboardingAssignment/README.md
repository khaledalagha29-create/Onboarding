# Mobile App Development 2 — Assignment 1: Onboarding Screens

Android Studio / Java application implementing the assignment's three-fragment onboarding flow.

## Run

1. Open the `OnboardingAssignment` folder as a project in Android Studio.
2. Ensure JDK 17 and Android SDK 35 are available. Allow Gradle sync to download dependencies.
3. Run on an Android emulator or Android phone (Android 6.0 or later).

The starter GitHub project was not accessible from this environment, so this is a **standalone, equivalent implementation** and not a modification of the instructor's starter files.

## Feature checklist

- Fragment1 collects user name and sends it to MainActivity via interface.
- MainActivity creates Fragment2 using `newInstance()` and a `Bundle`.
- Fragment2 collects email, gender, and optional university, and sends name + details to MainActivity via interface.
- MainActivity receives the name and opens Fragment3, passing the name in a Bundle.
- Fragment3 displays the received name.
- Confirmation CheckBox begins unchecked; button begins disabled and reads `Continue`.
- `setOnCheckedChangeListener` notifies MainActivity through the `OnConfirmationChangedListener` interface.
- MainActivity calls `Fragment3.updateContinueButton(checked)` to enable/disable the button and change text to `Finish`/`Continue`.
- No action is required when Finish is tapped.

## Suggested 3–5 minute video

1. (0:00–0:40) Introduce app and the three fragments.
2. (0:40–1:20) Demonstrate name entry and Fragment1->MainActivity callback.
3. (1:20–2:10) Enter email/gender/university in Fragment2 and explain interface.
4. (2:10–3:00) Show name arriving in Fragment3 through the Activity and Bundle.
5. (3:00–4:00) Show disabled Continue; check checkbox to enable Finish; uncheck to disable Continue; explain listener/interface.
6. (4:00–4:20) Show code files and conclude.

## Submission

Upload source to a public GitHub repo and demo video to Google Drive; submit their two links. Do not upload `build/`, `.gradle/`, or local SDK settings.
