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
    var timestamp: Instant,
    var responsesTimeInMillis: Map<Instant,Long>
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
    fun addTimeStamp(timestamp: Instant, responseTimeInMillis: Long){
        var newResponseMap = responsesTimeInMillis + mapOf(timestamp to responseTimeInMillis)
        // TODO: make count of entry configurable
        var takeLastNElements = 10
        responsesTimeInMillis = newResponseMap.toSortedMap(compareByDescending(Instant::toEpochMilli)).asIterable().take(takeLastNElements).associate { it.toPair() }
        println(responsesTimeInMillis.size)
    }
}

enum class Status(val status: String) {
    UP("UP"),
    DOWN("DOWN"),
    UNKNOWN("UNKNOWN")
}
