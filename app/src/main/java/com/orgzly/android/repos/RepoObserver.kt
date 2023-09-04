package com.orgzly.android.repos

import android.net.Uri

/**
 * Generic interface to work with repository observer implementation
 */
interface RepoObserver {
    /**
     * this function starts observing repository for changes
     * @param block - lambda to handle observer event
     */
    fun start(block: (repoUri:Uri, rookUri:Uri) -> Unit)

    /**
     * this function stops observing repository for changes
     */
    fun stop()
}