package ar.edu.unsam.algo3.dto
import ar.edu.unsam.algo3.*

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class UserToEditDTO @JsonCreator constructor(
    @JsonProperty("id") val id: Int,
    val fullName: String
)

fun User.toEditDTO() = UserToEditDTO(id = id, fullName = this.displayName())
