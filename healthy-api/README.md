**DISCLAIMER**: I used this little project to learn Kotlin and ReactJS. 
Probably there is a lot to improve and there are some bugs :) So please 
be careful if you want to use it and for sure feel free to contribute.

# Healthy-API

The Healthy-API is a application which queries the HTTP status
of a service in the background in a specified interval. The status
of the service is stored in memory.

### Why?

I haven't found a service that lets me easily specify endpoints which I want
to monitor. I just wanted to know if they are up and running.

### Todo's
- [X] read endpoints from a local file
- [X] make HTTP response configurable (ex. 200 -> healthy, 301 or 302 -> unhealthy)
- [X] create docker image
- [X] query endpoints in a specified time interval.
- [ ] implement basic http authentication
- [ ] make timeouts configurable
- [ ] follow redirects
- [ ] describe `healthy.yaml`
- [ ] implement pipeline
- [ ] measure response time of endpoint
- [ ] provide prometheus metric endpoint for scraper
- [ ] add IP response
- [ ] document the endpoint (swagger?)

## Development

* Build jar file with
```$xslt
# gradle bootJar
```
* Run jar with custom `healthy.yaml`
```$xslt
# java -Dhealthy.config.location=file:healthy.yaml -jar build/libs/Healthy-0.0.1-SNAPSHOT.jar
```
* Build docker image with name *healthy* (HINT: docker file uses own gradle to build the jar)
```$xslt
# docker build . -t healthy
```
* Run docker image
```$xslt
# docker run -p 80:8080 healthy
```
* Run docker image with custom healthy.yaml
```$xslt
# docker run -p 80:8080 -v $(pwd)/healthy.yaml:/healthy.yaml healthy
```