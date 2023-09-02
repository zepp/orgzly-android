package com.orgzly.android.repos.file

internal object StubObserver : DirectoryObserver {
    override fun startWatching() = Unit

    override fun stopWatching() = Unit
}