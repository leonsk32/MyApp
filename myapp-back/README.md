# login sample

## with username and password
`curl -i -X POST -d username=user -d password=userpass -c /tmp/cookies.txt http://localhost:8080/api/login`

`curl -i -X POST -d username=admin -d password=adminpass -c /tmp/cookies.txt http://localhost:8080/api/login`


## with cookie
`curl -i --header "Accept:application/json" -X GET -b /tmp/cookies.txt /tmp/cookies.txt http://localhost:8080/api/sec/foo`

`curl -i --header "Accept:application/json" -X GET -b /tmp/cookies.txt /tmp/cookies.txt http://localhost:8080/api/sec/admin/hoge`

# hagetaka sample
`curl -H 'Content-Type:application/json' -X POST -d '{"name": "user", "value": 3}' http://localhost:8080/api/v1/hagetaka`