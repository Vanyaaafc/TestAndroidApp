package com.example.testandroidapp.domain.response

import com.example.testandroidapp.domain.models.UserModel

data class UserResponse (
    var page: Int,
    var per_page: Int,
    var total: Int,
    var total_pages: Int,
    var data: List<UserModel>,
)