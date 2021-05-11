package data.db.entities
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class Training(
  @PrimaryKey var training_id : Int? =null,
  var name: String? =null
){

}