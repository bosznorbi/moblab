import hu.bme.aut.pokedb.model.PokemonDto
import hu.bme.aut.pokedb.model.PokemonType

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonResponse (

	val abilities : List<Abilities>,
	val base_experience : Int,
	val forms : List<Forms>,
	val game_indices : List<Game_indices>,
	val height : Int,
	val held_items : List<Held_items>,
	val id : Int,
	val is_default : Boolean,
	val location_area_encounters : String,
	val moves : List<Moves>,
	val name : String,
	val order : Int,
	val past_types : List<String>,
	val species : Species,
	val sprites : Sprites,
	val stats : List<Stats>,
	val types : List<Types>,
	val weight : Int
) {
	fun toDto(): PokemonDto {
		val slot1TypeName = types[0].type.name
		val slot2TypeName = PokemonType.values()[(0..18).random()].displayName // TODO: null checkkel lekerni rendesen

		return PokemonDto(
		id = id,
		name = name,
		type1 = PokemonType.fromName(slot1TypeName)!!,
		type2 = PokemonType.fromName(slot2TypeName)!!,
		imageUrl = sprites.front_default)
	}
}