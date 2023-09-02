package com.orgzly.android.repos.file

/**
 * Generic interface to reference file system observers
 */
internal interface DirectoryObserver {
    /**
     * Start watching for events. The monitored directory must exist at this time, or else no events
     * will be reported (even if it appears later). If monitoring is already started, this call has
     * no effect.
     */
    fun startWatching()

    /**
     * Stop watching for events. Some events may be in process, so events may continue to be
     * reported even after this method completes. If monitoring is already stopped, this call
     * has no effect.
     */
    fun stopWatching()
}