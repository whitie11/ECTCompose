package net.whiteapps.ectmanager.data.dto

import kotlinx.serialization.Serializable


@Serializable
data class TokenRequestDTO(
    val password: String,
    val username: String
)
