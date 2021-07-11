# Spring boot Elastic search Example

## Elastic search query
* To have the list of index
> curl "http://localhost:9200/_cat/indices?v"

* Search all books
> curl "http://localhost:9200/twb/books/_search?pretty=true"
* Search a specific book id
> curl "http://localhost:9200/twb/books/_search?q=_id:1003&pretty=true"

## API Rest Query
* Get all title match 
> curl "http://localhost:8080/books/all/title?title=apache"
* Full text search API
> curl "http://localhost:8080/books/_search?query=apache"
* Full text search specifying attribute
> curl "http://localhost:8080/books/_search?query=_id:1003"


## Reference
* https://mkyong.com/spring-boot/spring-boot-spring-data-elasticsearch-example/
* https://codecurated.com/blog/how-to-connect-java-with-elasticsearch/