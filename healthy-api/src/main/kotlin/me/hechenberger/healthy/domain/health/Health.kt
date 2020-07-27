package me.hechenberger.healthy.domain.health

data class Health(
    val name: String,
    var status: Status,
    val url: String,
    val upHttpCode: List<Int>,
    val downHttpCode: List<Int>
) {
    fun updateStatus(statusCode: Int) {
        if(upHttpCode.contains(statusCode)){
            status = Status.UP
        }else if(downHttpCode.contains(statusCode)){
            status = Status.DOWN
        }else{
            status = Status.UNKNOWN
        }
    }
}

enum class Status(val status: String) {
    UP("UP"),
    DOWN("DOWN"),
    UNKNOWN("UNKNOWN")
}
