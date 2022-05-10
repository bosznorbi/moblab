package hu.bme.aut.pokedb.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.pokedb.databinding.ItemBinding
import hu.bme.aut.pokedb.model.PokemonDto

class MyListAdapter(private val onClick: (pokemonId: Int) -> Unit) : ListAdapter<PokemonDto, MyListAdapter.PokemonViewHolder>(MyDiffUtil()) {

    class PokemonViewHolder(
        private val binding: ItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        val root = binding.root

        fun bind(pokemonDto: PokemonDto) {
            binding.itemIdTextView.text = pokemonDto.id.toString()
            binding.itemPokemonTextView.text = pokemonDto.name
            binding.itemType1TextView.text = pokemonDto.type1.displayName
            binding.itemType2TextView.text = pokemonDto.type2.displayName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PokemonViewHolder(ItemBinding.inflate(layoutInflater))
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.root.setOnClickListener { onClick(getItem(position).id) }
    }
}

class MyDiffUtil : DiffUtil.ItemCallback<PokemonDto>() {

    override fun areItemsTheSame(oldItem: PokemonDto, newItem: PokemonDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonDto, newItem: PokemonDto): Boolean {
        return oldItem == newItem
    }
}