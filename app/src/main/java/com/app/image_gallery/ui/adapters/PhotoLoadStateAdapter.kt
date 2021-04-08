package com.app.image_gallery.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.image_gallery.databinding.PhotoLoadStateFooterBinding

class PhotoLoadStateAdapter(private val retry:() -> Unit) : LoadStateAdapter<PhotoLoadStateAdapter.LoadStateViewHolder>() {

    inner class LoadStateViewHolder(private val binding: PhotoLoadStateFooterBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState){
            binding.apply{
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewError.isVisible = loadState !is LoadState.Loading
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = PhotoLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false);

        return LoadStateViewHolder(binding);
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}