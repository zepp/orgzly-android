package com.orgzly.android.repos.content

import android.content.Context
import android.net.Uri
import com.orgzly.android.repos.RepoObserver
import com.orgzly.android.repos.SyncRepo

class ContentRepoObserver (repo: SyncRepo, val context:Context) : RepoObserver{
    private val repoUri = repo.uri
    private lateinit var observer: CompositeContentObserver
    override fun start(block: (repoUri: Uri, rookUri: Uri) -> Unit) {
        observer = CompositeContentObserver(context){_, uri ->  block(repoUri, uri)}
        context.contentResolver.registerContentObserver(repoUri, false, observer)
    }

    override fun stop() {
        context.contentResolver.unregisterContentObserver(observer)
    }
}