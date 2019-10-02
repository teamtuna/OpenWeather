package karrel.com.mvvmsample.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class AutoClearedDisposable(
    private val activity: AppCompatActivity,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
) : LifecycleObserver {

    fun add(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    open fun detachSelf() {
        println("ON_DESTROY !!")
        compositeDisposable.clear()
        activity.lifecycle.removeObserver(this)

    }
}

