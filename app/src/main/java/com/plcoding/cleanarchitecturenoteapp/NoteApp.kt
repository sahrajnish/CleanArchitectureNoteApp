package com.plcoding.cleanarchitecturenoteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/*
Dependency Injection is just a fancy way to say, "Instead of making things by yourself,
ask someone to give them to you."

For example, let's say your NoteViewModel needs a NoteRepository to work.
Instead of creating a new NoteRepository inside NoteViewModel, you can ask Hilt to provide it.

Without DI:

class NoteViewModel {
    val repository = NoteRepository() // You create it yourself
}
With DI (using Hilt):

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteRepository // Hilt provides it for you
) {
    // Now you can use repository
}
*/
@HiltAndroidApp //You put this on your Application class to tell Hilt to start helping your app.
class NoteApp: Application() {
}