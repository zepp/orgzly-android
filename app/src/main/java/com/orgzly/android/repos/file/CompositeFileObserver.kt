package com.orgzly.android.repos.file

import android.net.Uri
import android.os.Build
import android.os.FileObserver
import androidx.annotation.RequiresApi
import androidx.core.net.toFile
import com.orgzly.BuildConfig
import com.orgzly.android.util.LogUtils
import java.io.File

@RequiresApi(Build.VERSION_CODES.Q)
internal class CompositeFileObserver(
    val uri: Uri,
    var block: (event: Int, uri: Uri) -> Unit = {_,_ -> }
) :
    FileObserver(uri.toFile(), DEFAULT_MASK), DirectoryObserver {
    private val root = uri.toFile()

    override fun startWatching() {
        super.startWatching()
        if (BuildConfig.LOG_DEBUG) {
            LogUtils.d(TAG, "start watching: $uri");
        }
    }

    override fun onEvent(event: Int, path: String?) {
        if (path == null) return
        val file = File(path)
        if (root != file.parentFile) return
        if (BuildConfig.LOG_DEBUG) {
            LogUtils.d(TAG, "event: $event $file");
        }
        block(event, Uri.fromFile(file))
    }

    companion object {
        private const val DEFAULT_MASK = MODIFY or
                CREATE or DELETE or MOVED_FROM or MOVED_TO

        private val TAG = CompositeFileObserver::class.simpleName!!
    }
}