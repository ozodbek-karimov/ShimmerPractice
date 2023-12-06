package pl.ozodbek.shimmerpractice.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import coil.request.CachePolicy
import coil.size.ViewSizeResolver
import pl.ozodbek.shimmerpractice.R

inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
    crossinline bindingInflater: (LayoutInflater) -> T) =
    lazy(LazyThreadSafetyMode.NONE) {
        bindingInflater.invoke(layoutInflater)
    }


inline fun <reified T : ViewBinding> ViewGroup.viewBinding(
    crossinline inflate: (LayoutInflater, ViewGroup, Boolean) -> T,
): T {
    return inflate(LayoutInflater.from(context), this, false)
}

fun <T> ImageView.loadImage(image: T?) {
    this.load(image.takeIf { it?.toString()?.isNotBlank() == true } ?: R.drawable.ic_error_placeholder) {
        crossfade(true)
        placeholder(R.drawable.ic_error_placeholder)
        error(R.drawable.ic_error_placeholder)
        size(ViewSizeResolver(this@loadImage))
        memoryCachePolicy(CachePolicy.ENABLED)
        diskCachePolicy(CachePolicy.ENABLED)
    }


    /** IMAGE LOADING CACHE

    val imageLoader = ImageLoader.Builder(context)
    .respectCacheHeaders(false)
    .build()
    Coil.setImageLoader(imageLoader)


     */
}


fun View.onClick(clickListener: (View) -> Unit) {
    setOnClickListener(clickListener)
}

fun <VH : RecyclerView.ViewHolder> RecyclerView.setNullableAdapter(
    adapter: RecyclerView.Adapter<VH>,
) {
    this.adapter = adapter
    this.clearReference()
}


internal fun RecyclerView.clearReference() {
    addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

        override fun onViewAttachedToWindow(v: View) {
        }

        override fun onViewDetachedFromWindow(v: View) {
            this@clearReference.adapter = null
        }
    })
}
