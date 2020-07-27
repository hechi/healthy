package me.hechenberger.healthy.application.configuration

data class Endpoint constructor(
    var name: String,
    var url: String,
    var upHttpCode: List<Int>,
    var downHttpCode: List<Int>
)

data class Endpoints constructor(var endpoints: List<Endpoint>)