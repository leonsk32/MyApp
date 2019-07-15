# login sample

## with username and password
`curl -i -X POST -d username=user -d password=userpass -c /tmp/cookies.txt http://localhost:8080/login`

`curl -i -X POST -d username=admin -d password=adminpass -c /tmp/cookies.txt http://localhost:8080/login`


## with cookie
`curl -i --header "Accept:application/json" -X GET -b /tmp/cookies.txt /tmp/cookies.txt http://localhost:8080/api/foo`

`curl -i --header "Accept:application/json" -X GET -b /tmp/cookies.txt /tmp/cookies.txt http://localhost:8080/api/admin/hoge`
