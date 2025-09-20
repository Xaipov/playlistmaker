package com.example.playlistmaker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TrackAdapter : RecyclerView.Adapter<TrackAdapter.TrackViewHolder>() {

    private val tracks = mutableListOf<Track>()

    fun setTracks(newTracks: List<Track>) {
        tracks.clear()
        tracks.addAll(newTracks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(tracks[position])
    }

    override fun getItemCount(): Int = tracks.size

    inner class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val trackImage: ImageView = itemView.findViewById(R.id.trackImage)
        private val trackTitle: TextView = itemView.findViewById(R.id.trackTitle)
        private val trackArtist: TextView = itemView.findViewById(R.id.trackArtist)

        fun bind(track: Track) {
            trackTitle.text = track.title
            trackArtist.text = "${track.artist} · ${track.duration}"

            Glide.with(itemView.context)
                .load(track.coverUrl)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(trackImage)
        }
    }
}
