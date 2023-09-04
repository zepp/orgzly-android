package com.orgzly.android.repos.file

import android.net.Uri
import android.os.Build
import com.orgzly.android.repos.RepoObserver
import com.orgzly.android.repos.SyncRepo

class DirectoryRepoObserver (repo: SyncRepo) : RepoObserver{
    private val repoUri = repo.uri
    private var observer: DirectoryObserver = StubObserver

    override fun start(block: (repoUri: Uri, rookUri: Uri) -> Unit) {
        observer.stopWatching()
        observer = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            CompositeFileObserver(repoUri){_, uri ->  block(repoUri, uri)}
        } else {
            StubObserver
        }
        observer.startWatching()
    }

    override fun stop() {
        observer.stopWatching()
    }
}