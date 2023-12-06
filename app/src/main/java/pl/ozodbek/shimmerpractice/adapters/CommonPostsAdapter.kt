package pl.ozodbek.shimmerpractice.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.ozodbek.shimmerpractice.data.models.CommonPosts
import pl.ozodbek.shimmerpractice.databinding.CommonPostsRowItemBinding
import pl.ozodbek.shimmerpractice.util.loadImage
import pl.ozodbek.shimmerpractice.util.onClick
import pl.ozodbek.shimmerpractice.util.viewBinding

class CommonPostsAdapter :
    ListAdapter<CommonPosts, CommonPostsAdapter.AdapterViewHolder>(MyDiffCallback()) {

    private var itemClickListener: ((CommonPosts) -> Unit)? = null

    fun setItemClickListener(listener: (CommonPosts) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(parent.viewBinding(CommonPostsRowItemBinding::inflate))
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val remoteData = getItem(position)
        holder.bind(remoteData, itemClickListener)
    }


    class AdapterViewHolder(private val binding: CommonPostsRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            remoteData: CommonPosts,
            clickListener: ((CommonPosts) -> Unit)?,
        ) {
            remoteData.let {
                binding.apply {

                    binding.imageView.loadImage(remoteData.imagePathModified)

                    binding.textView.text = remoteData.title
                    binding.textView2.text = remoteData.description
                    binding.textView3.text = remoteData.district
                    binding.textView4.text = remoteData.region

                    root.onClick { clickListener?.invoke(remoteData) }
                }
            }
        }
    }

    private class MyDiffCallback : DiffUtil.ItemCallback<CommonPosts>() {
        override fun areItemsTheSame(oldItem: CommonPosts, newItem: CommonPosts) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CommonPosts, newItem: CommonPosts) =
            oldItem == newItem
    }

}