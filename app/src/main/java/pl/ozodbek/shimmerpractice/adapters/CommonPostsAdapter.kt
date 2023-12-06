package pl.ozodbek.shimmerpractice.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.ozodbek.shimmerpractice.data.models.Data
import pl.ozodbek.shimmerpractice.databinding.ReqressinUsersRowItemBinding
import pl.ozodbek.shimmerpractice.util.loadImage
import pl.ozodbek.shimmerpractice.util.onClick
import pl.ozodbek.shimmerpractice.util.viewBinding

class CommonPostsAdapter :
    ListAdapter<Data, CommonPostsAdapter.AdapterViewHolder>(MyDiffCallback()) {

    private var itemClickListener: ((Data) -> Unit)? = null

    fun setItemClickListener(listener: (Data) -> Unit) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(parent.viewBinding(ReqressinUsersRowItemBinding::inflate))
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val remoteData = getItem(position)
        holder.bind(remoteData, itemClickListener)
    }


    class AdapterViewHolder(private val binding: ReqressinUsersRowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            remoteData: Data,
            clickListener: ((Data) -> Unit)?,
        ) {
            remoteData.let {
                binding.apply {

                    binding.imageView.loadImage(remoteData.avatar)

                    binding.textView.text = remoteData.firstName
                    binding.textView2.text = remoteData.lastName
                    binding.textView3.text = remoteData.email
                    binding.textView4.text = remoteData.id.toString()

                    root.onClick { clickListener?.invoke(remoteData) }
                }
            }
        }
    }

    private class MyDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
            oldItem == newItem
    }

}