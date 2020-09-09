package me.hechenberger.healthy.domain.health

import lombok.Data
import java.time.Instant

@Data
data class Health(
    var name: String,
    var status: Status,
    var url: String,
    var upHttpCode: List<Int>,
    var downHttpCode: List<Int>,
    var responseTimeInMillis: Long,
    var timestamp: Instant
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
