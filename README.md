# RickyandMorty
An android app built using Kotlin that consumes [RickyadMorty API](https://rickandmortyapi.com/) to display characters.It has been built following Clean Architecture Principle, Repository Pattern, MVVM Architecture in the presentation layer as well as Jetpack components.

## Libraries.

- [Koin](https://github.com/google/hilt) - Dependency Injection library.
- [Jetpack](https://developer.android.com/jetpack)
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html) - Provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original Android [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
    - [View Binding](https://developer.android.com/topic/libraries/view-binding/) - A feature that allows you to more easily write code that interacts with views.
    - [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)-Component that allows easier implementation of navigation from simple button clicks to more complex patterns.

- [Retrofit](https://square.github.io/retrofit/) - Type-safe http client
  and supports coroutines out of the box.
- [GSON](https://github.com/square/gson) - JSON Parser,used to parse
  requests on the data layer for Entities and understands Kotlin non-nullable
  and default parameters.
- [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md) - Logs HTTP request and response data.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for coroutines.
- [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and can provide multiple values. A flow is conceptually a stream of data that can be computed asynchronously.
- [Expresso](https://developer.android.com/training/testing/espresso) - Library used to write concise, beautiful, and reliable Android UI tests.

## CI/CD
- [GitHub Actions](https://github.com/kibettheophilus/RickyandMorty/actions/workflows/firebase_distribute.yml) - GitHub actions is used in this project to run the continuous intergration and continuous deployment when a code is pushed to the master branch.
- [Fastlane](https://fastlane.tools/) - Fastlane is an open source platform aimed at simplifying Android and iOS deployment.
fastlane lets you automate every aspect of your development and release workflow. 
- [Firebase App Distribution](https://firebase.google.com/docs/app-distribution) - Makes distributing your apps to trusted testers painless.

<img src="https://user-images.githubusercontent.com/61080898/142217622-83242e2f-8926-46a1-887e-b5e5c17a8d2a.png"/>
<img src="https://user-images.githubusercontent.com/61080898/142217666-67791451-03e2-4f4e-b9e3-5b404b2a02b8.png"/>
<img src="https://user-images.githubusercontent.com/61080898/142217677-13b0e392-1417-43bd-a8ce-55ba366c1857.png"/>

## Screenshots
![Screenshot_20211117-163024](https://user-images.githubusercontent.com/61080898/142219390-bd7de126-be0e-4a32-9f5a-912df3976d9f.png)


