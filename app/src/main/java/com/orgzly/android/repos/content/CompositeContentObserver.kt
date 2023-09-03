package com.orgzly.android.repos.content

import android.content.Context
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.orgzly.BuildConfig
import com.orgzly.android.util.LogUtils


internal class CompositeContentObserver(
    val context: Context,
    handler: Handler = Handler(Looper.getMainLooper()),
    val block: (flags: Int, uri: Uri) -> Unit
) :
    ContentObserver(handler) {
    override fun onChange(selfChange: Boolean, uri: Uri?, flags: Int) {
        super.onChange(selfChange, uri, flags)
        if (uri == null) return
        if (selfChange) return
        if (BuildConfig.LOG_DEBUG) {
            LogUtils.d(TAG, "event: $flags $uri");
        }
        block(flags, uri)
    }

    companion object {
        private val TAG = CompositeContentObserver::class.simpleName!!
    }
}